package com.aiforest.cloud.upms.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.upms.admin.service.SysRoleMenuService;
import com.aiforest.cloud.upms.admin.service.SysRoleService;
import com.aiforest.cloud.upms.common.entity.SysRole;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.upms.common.entity.SysRoleMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理模块")
public class SysRoleController {
	private final SysRoleService sysRoleService;
	private final SysRoleMenuService sysRoleMenuService;

	/**
	 * 通过ID查询角色信息
	 *
	 * @param id ID
	 * @return 角色信息
	 */
	@ApiOperation(value = "通过ID查询角色信息")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:role:get')")
	public R getById(@PathVariable String id) {
		return R.ok(sysRoleService.getById(id));
	}

	/**
	 * 添加角色
	 *
	 * @param sysRole 角色信息
	 * @return ok、false
	 */
	@ApiOperation(value = "添加角色")
	@SysLog("添加角色")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('sys:role:add')")
	public R save(@Valid @RequestBody SysRole sysRole) {
		return R.ok(sysRoleService.save(sysRole));
	}

	/**
	 * 修改角色
	 *
	 * @param sysRole 角色信息
	 * @return ok/false
	 */
	@ApiOperation(value = "修改角色")
	@SysLog("修改角色")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('sys:role:edit')")
	public R update(@Valid @RequestBody SysRole sysRole) {
		if(!this.judeAdmin(sysRole.getId())){
			return R.failed("管理员角色不能操作");
		}
		return R.ok(sysRoleService.updateById(sysRole));
	}

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除角色")
	@SysLog("删除角色")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:role:del')")
	public R removeById(@PathVariable String id) {
		if(!this.judeAdmin(id)){
			return R.failed("管理员角色不能操作");
		}
		return R.ok(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 *
	 * @return 角色列表
	 */
	@ApiOperation(value = "获取角色列表")
	@GetMapping("/list")
	public List<SysRole> getList(SysRole sysRole) {
		return sysRoleService.list(Wrappers.query(sysRole));
	}

	/**
	 * 分页查询角色信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@ApiOperation(value = "分页查询角色信息")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('sys:role:index')")
	public R getRolePage(Page page) {
		return R.ok(sysRoleService.page(page, Wrappers.emptyWrapper()));
	}

	/**
	 * 更新角色菜单
	 *
	 * @param sysRoleMenu roleId  角色ID
	 * @param sysRoleMenu menuId 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return ok、false
	 */
	@ApiOperation(value = "更新角色菜单")
	@SysLog("更新角色菜单")
	@PutMapping("/menu")
	@PreAuthorize("@ato.hasAuthority('sys:role:perm','sys:tenant:edit')")
	public R saveRoleMenus(@RequestBody SysRoleMenu sysRoleMenu) {
		String roleId = sysRoleMenu.getRoleId();
		String menuIds = sysRoleMenu.getMenuId();
		if(StrUtil.isBlank(roleId) || StrUtil.isBlank(menuIds)){
			return R.ok();
		}
		if(!this.judeAdmin(roleId)){
			return R.failed("管理员角色不能操作");
		}
		SysRole sysRole = sysRoleService.getById(roleId);
		return R.ok(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleId, menuIds));
	}

	/**
	 * 校验是否是管理员角色
	 * @param roleId
	 * @return
	 */
	boolean judeAdmin(String roleId){
		SysRole sysRole = sysRoleService.getById(roleId);
		if(CommonConstants.ROLE_CODE_ADMIN.equals(sysRole.getRoleCode())){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 更新租户管理员角色菜单
	 *
	 * @param sysRoleMenu roleId  角色ID
	 * @param sysRoleMenu menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return ok、false
	 */
	@ApiOperation(value = "更新租户管理员角色菜单")
	@SysLog("更新租户管理员角色菜单")
	@PutMapping("/menu/tenant")
	@PreAuthorize("@ato.hasAuthority('sys:tenant:edit')")
	public R saveRoleMenusTenant(@RequestBody SysRoleMenu sysRoleMenu) {
		String tenantId = sysRoleMenu.getTenantId();
		String roleId = sysRoleMenu.getRoleId();
		String menuIds = sysRoleMenu.getMenuId();
		if(StrUtil.isBlank(tenantId) || StrUtil.isBlank(menuIds) || StrUtil.isBlank(menuIds)){
			return R.ok();
		}
		TenantContextHolder.setTenantId(tenantId);
		SysRole sysRole = sysRoleService.getById(roleId);
		return R.ok(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleId, menuIds));
	}
}
