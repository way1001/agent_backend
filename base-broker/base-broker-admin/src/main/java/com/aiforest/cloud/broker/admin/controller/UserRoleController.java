/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.broker.common.entity.UserRole;
import com.aiforest.cloud.broker.admin.service.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 用户角色
 *
 * @author aiforest
 * @date 2020-09-02 16:36:16
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/userrole")
@Api(value = "userrole", tags = "用户角色管理")
public class UserRoleController {

    private final UserRoleService userRoleService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param userRole 用户角色
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:userrole:index')")
    public R getPage(Page page, UserRole userRole) {
        return R.ok(userRoleService.page(page, Wrappers.query(userRole)));
    }

	/**
	 * list查询
	 * @param userRole
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public List<UserRole> getList(UserRole userRole) {
		return userRoleService.list(Wrappers.query(userRole).lambda()
				.select(UserRole::getRoleCode,
						UserRole::getRoleName)
		);
	}

    /**
     * 用户角色查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "用户角色查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:userrole:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(userRoleService.getById(id));
    }

    /**
     * 用户角色新增
     * @param userRole 用户角色
     * @return R
     */
    @ApiOperation(value = "用户角色新增")
    @SysLog("新增用户角色")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:userrole:add')")
    public R save(@RequestBody UserRole userRole) {
        return R.ok(userRoleService.save(userRole));
    }

    /**
     * 用户角色修改
     * @param userRole 用户角色
     * @return R
     */
    @ApiOperation(value = "用户角色修改")
    @SysLog("修改用户角色")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:userrole:edit')")
    public R updateById(@RequestBody UserRole userRole) {
        return R.ok(userRoleService.updateById(userRole));
    }

    /**
     * 用户角色删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "用户角色删除")
    @SysLog("删除用户角色")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:userrole:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(userRoleService.removeById(id));
    }

}
