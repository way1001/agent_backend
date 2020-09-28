/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.upms.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.upms.common.entity.SysLogLogin;
import com.aiforest.cloud.upms.admin.service.SysLogLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登录日志
 *
 * @author JL
 * @date 2020-03-28 16:39:04
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/loglogin")
@Api(value = "loglogin", tags = "登录日志管理")
public class SysLogLoginController {

    private final SysLogLoginService sysLogLoginService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param sysLogLogin 登录日志
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
//    @PreAuthorize("@ato.hasAuthority('sys:loglogin:index')")
    public R getPage(Page page, SysLogLogin sysLogLogin) {
        return R.ok(sysLogLoginService.page(page, Wrappers.query(sysLogLogin)));
    }

    /**
     * 登录日志查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "登录日志查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('sys:loglogin:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(sysLogLoginService.getById(id));
    }

    /**
     * 登录日志新增
     * @param sysLogLogin 登录日志
     * @return R
     */
    @ApiOperation(value = "登录日志新增")
	@Inside
    @PostMapping("/save")
    public R save(@RequestBody SysLogLogin sysLogLogin) {
        return R.ok(sysLogLoginService.save(sysLogLogin));
    }

    /**
     * 登录日志修改
     * @param sysLogLogin 登录日志
     * @return R
     */
    @ApiOperation(value = "登录日志修改")
    @SysLog("修改登录日志")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('sys:loglogin:edit')")
    public R updateById(@RequestBody SysLogLogin sysLogLogin) {
        return R.ok(sysLogLoginService.updateById(sysLogLogin));
    }

    /**
     * 登录日志删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "登录日志删除")
    @SysLog("删除登录日志")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('sys:loglogin:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(sysLogLoginService.removeById(id));
    }

}
