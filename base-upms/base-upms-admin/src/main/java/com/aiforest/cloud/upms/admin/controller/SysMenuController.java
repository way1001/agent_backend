package com.aiforest.cloud.upms.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.upms.admin.service.SysMenuService;
import com.aiforest.cloud.upms.admin.service.SysRoleService;
import com.aiforest.cloud.upms.common.dto.MenuTree;
import com.aiforest.cloud.upms.common.entity.SysMenu;
import com.aiforest.cloud.upms.common.entity.SysRole;
import com.aiforest.cloud.upms.common.vo.MenuVO;
import com.aiforest.cloud.upms.common.util.TreeUtil;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JL
 * @date 2019/07/11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class SysMenuController {
	private final SysMenuService sysMenuService;
	private final SysRoleService sysRoleService;

	/**
	 * 获取所有菜单和权限
	 *
	 * @return 树形菜单
	 */
	@ApiOperation(value = "返回树形菜单集合")
	@GetMapping(value = "/all/tree")
	public R getAllTree() {
		List<MenuTree> menuTreeList = TreeUtil.buildTree(sysMenuService.list(Wrappers.<SysMenu>lambdaQuery()
				.orderByAsc(SysMenu::getSort)).stream()
				.collect(Collectors.toList()),CommonConstants.PARENT_ID);
		return R.ok(menuTreeList);
	}

	/**
	 * 获取当前用户的菜单
	 *
	 * @return 当前用户的树形菜单
	 */
	@ApiOperation(value = "返回当前用户的树形菜单集合")
	@GetMapping
	public R getUserMenu() {
		// 获取符合条件的菜单
		Set<MenuVO> all = new HashSet<>();
		SecurityUtils.getRoles()
				.forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		List<MenuTree> menuTreeList = all.stream()
				.filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
				.map(MenuTree::new)
				.sorted(Comparator.comparingInt(MenuTree::getSort))
				.collect(Collectors.toList());
		return R.ok(TreeUtil.build(menuTreeList, CommonConstants.PARENT_ID));
	}

	/**
	 * 获取当前用户的菜单和权限
	 *
	 * @return 树形菜单
	 */
	@ApiOperation(value = "返回树形菜单集合")
	@GetMapping(value = "/tree")
	public R getTree() {
		Set<MenuVO> all = new HashSet<>();
		SecurityUtils.getRoles()
				.forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		List<MenuTree> menuTreeList = all.stream()
				.map(MenuTree::new)
				.collect(Collectors.toList());
		return R.ok(TreeUtil.build(menuTreeList, CommonConstants.PARENT_ID));
	}

	/**
	 * 返回角色的菜单集合
	 *
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@ApiOperation(value = "返回角色的菜单集合")
	@GetMapping("/tree/{roleId}")
	public R getRoleTree(@PathVariable String roleId) {
		return R.ok(sysMenuService.findMenuByRoleId(roleId)
				.stream()
				.map(MenuVO::getId)
				.collect(Collectors.toList()));
	}

	/**
	 * 通过ID查询菜单的详细信息
	 *
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@ApiOperation(value = "通过ID查询菜单的详细信息")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:menu:get')")
	public R getById(@PathVariable String id) {
		return R.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 *
	 * @param sysMenu 菜单信息
	 * @return ok/false
	 */
	@ApiOperation(value = "新增菜单")
	@SysLog("新增菜单")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('sys:menu:add')")
	public R save(@Valid @RequestBody SysMenu sysMenu) {
		sysMenuService.saveMenu(sysMenu);
		return R.ok();
	}

	/**
	 * 删除菜单
	 *
	 * @param id 菜单ID
	 * @return ok/false
	 */
	@ApiOperation(value = "删除菜单")
	@SysLog("删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:menu:del')")
	public R removeById(@PathVariable String id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 *
	 * @param sysMenu
	 * @return
	 */
	@ApiOperation(value = "更新菜单")
	@SysLog("更新菜单")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('sys:menu:edit')")
	public R update(@Valid @RequestBody SysMenu sysMenu) {
		sysMenu.setUpdateTime(LocalDateTime.now());
		return R.ok(sysMenuService.updateMenuById(sysMenu));
	}

	/**
	 * 返回租户管理员角色的菜单集合
	 *
	 * @param tenantId 租户ID
	 * @return 属性集合
	 */
	@ApiOperation(value = "返回租户管理员角色的菜单集合")
	@GetMapping("/tree/tenant/{tenantId}")
	@PreAuthorize("@ato.hasAuthority('sys:tenant:edit')")
	public R getRoleTreeTenant(@PathVariable String tenantId) {
		TenantContextHolder.setTenantId(tenantId);
		//找出指定租户的管理员角色
		SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getRoleCode,CommonConstants.ROLE_CODE_ADMIN));
		List listMenuVO = sysMenuService.findMenuByRoleId(sysRole.getId())
				.stream()
				.map(MenuVO::getId)
				.collect(Collectors.toList());
		Map<String,Object> map = new HashMap<>();
		map.put("sysRole",sysRole);
		map.put("listMenuVO",listMenuVO);
		//菜单集合
		return R.ok(map);
	}
}
