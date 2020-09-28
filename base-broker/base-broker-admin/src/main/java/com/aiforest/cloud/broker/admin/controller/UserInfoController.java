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
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.broker.admin.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经纪人用户
 *
 * @author aiforest
 * @date 2020-08-31 08:43:29
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/userinfo")
@Api(value = "userinfo", tags = "经纪人用户管理")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param userInfo 经纪人用户
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:userinfo:index')")
    public R getPage(Page page, UserInfo userInfo) {
        return R.ok(userInfoService.page(page, Wrappers.query(userInfo)));
    }

    /**
     * 经纪人用户查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "经纪人用户查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:userinfo:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(userInfoService.getById(id));
    }

    /**
     * 经纪人用户新增
     * @param userInfo 经纪人用户
     * @return R
     */
    @ApiOperation(value = "经纪人用户新增")
    @SysLog("新增经纪人用户")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:userinfo:add')")
    public R save(@RequestBody UserInfo userInfo) {
        return R.ok(userInfoService.save(userInfo));
    }

    /**
     * 经纪人用户修改
     * @param userInfo 经纪人用户
     * @return R
     */
    @ApiOperation(value = "经纪人用户修改")
    @SysLog("修改经纪人用户")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:userinfo:edit')")
    public R updateById(@RequestBody UserInfo userInfo) {
        return R.ok(userInfoService.updateById(userInfo));
    }

    /**
     * 经纪人用户删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "经纪人用户删除")
    @SysLog("删除经纪人用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:userinfo:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(userInfoService.removeById(id));
    }

}
