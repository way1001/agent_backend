package com.aiforest.cloud.upms.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.upms.admin.service.SysOauthClientService;
import com.aiforest.cloud.upms.common.entity.SysOauthClient;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 终端控制器
 *
 * @author
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Api(value = "client", tags = "客户端管理模块")
public class SysOauthClientController {
	private final SysOauthClientService sysOauthClientService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysOauthClient
	 */
	@ApiOperation(value = "通过ID查询")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:client:get')")
	public R getById(@PathVariable String id) {
		return R.ok(sysOauthClientService.getById(id));
	}


	/**
	 * 简单分页查询
	 *
	 * @param page                  分页对象
	 * @param sysOauthClient 系统终端
	 * @return
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('sys:client:index')")
	public R getOauthClientDetailsPage(Page page, SysOauthClient sysOauthClient) {
		return R.ok(sysOauthClientService.page(page, Wrappers.query(sysOauthClient)));
	}

	/**
	 * 添加
	 *
	 * @param sysOauthClient 实体
	 * @return ok/false
	 */
	@ApiOperation(value = "添加")
	@SysLog("添加终端")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('sys:client:add')")
	public R add(@Valid @RequestBody SysOauthClient sysOauthClient) {
		return R.ok(sysOauthClientService.save(sysOauthClient));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return ok/false
	 */
	@ApiOperation(value = "删除")
	@SysLog("删除终端")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:client:del')")
	public R removeById(@PathVariable String id) {
		return R.ok(sysOauthClientService.removeClientDetailsById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysOauthClient 实体
	 * @return ok/false
	 */
	@ApiOperation(value = "编辑")
	@SysLog("编辑终端")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('sys:client:edit')")
	public R update(@Valid @RequestBody SysOauthClient sysOauthClient) {
		return R.ok(sysOauthClientService.updateClientDetailsById(sysOauthClient));
	}
}
