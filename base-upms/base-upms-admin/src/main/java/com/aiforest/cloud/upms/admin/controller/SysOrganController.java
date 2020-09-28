package com.aiforest.cloud.upms.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.upms.admin.service.SysOrganService;
import com.aiforest.cloud.upms.common.dto.OrganTree;
import com.aiforest.cloud.upms.common.entity.SysOrgan;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.upms.common.entity.SysTenant;
import com.aiforest.cloud.upms.common.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author JL
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/organ")
@Api(value = "organ", tags = "机构管理模块")
public class SysOrganController {
	private final SysOrganService sysOrganService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysOrgan
	 */
	@ApiOperation(value = "通过ID查询")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:organ:get')")
	public R getById(@PathVariable String id) {
		return R.ok(sysOrganService.getById(id));
	}

	/**
	 * list查询
	 * @param sysOrgan
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public R getList(SysOrgan sysOrgan) {
		return R.ok(sysOrganService.list(Wrappers.query(sysOrgan)));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@ApiOperation(value = "返回树形菜单集合")
	@GetMapping(value = "/tree")
	public R getTree() {
		return R.ok(sysOrganService.selectTree());
	}

	/**
	 * 添加
	 *
	 * @param sysOrgan 实体
	 * @return ok/false
	 */
	@ApiOperation(value = "添加")
	@SysLog("添加机构")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('sys:organ:add')")
	public R save(@Valid @RequestBody SysOrgan sysOrgan) {
		try {
			if(CommonConstants.PARENT_ID.equals(sysOrgan.getParentId())){
				throw new Exception("父级节点不能为0");
			}
			return R.ok(sysOrganService.saveOrgan(sysOrgan));
		} catch (DuplicateKeyException e){
			if(e.getMessage().contains("uk_tenant_id_code")){
				return R.failed("机构编码已存在");
			}else{
				return R.failed(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return ok/false
	 */
	@ApiOperation(value = "删除")
	@SysLog("删除机构")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:organ:del')")
	public R removeById(@PathVariable String id) {
		SysOrgan sysOrgan2 = sysOrganService.getById(id);
		if(CommonConstants.PARENT_ID.equals(sysOrgan2.getParentId())){
			return R.failed("总机构（租户机构）不能删除");
		}
		return R.ok(sysOrganService.removeOrganById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysOrgan 实体
	 * @return ok/false
	 */
	@ApiOperation(value = "编辑")
	@SysLog("编辑机构")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('sys:organ:edit')")
	public R update(@Valid @RequestBody SysOrgan sysOrgan) {
		try {
			SysOrgan sysOrgan2 = sysOrganService.getById(sysOrgan.getId());
			if(CommonConstants.PARENT_ID.equals(sysOrgan2.getParentId())){
				sysOrgan.setParentId(CommonConstants.PARENT_ID);
			}
			return R.ok(sysOrganService.updateOrganById(sysOrgan));
		} catch (DuplicateKeyException e){
			if(e.getMessage().contains("uk_tenant_id_code")){
				return R.failed("机构编码已存在");
			}else{
				return R.failed(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}
}
