/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.estate.common.entity.Activities;
import com.aiforest.cloud.estate.admin.service.ActivitiesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 房产动态
 *
 * @author way
 * @date 2020-08-18 17:10:20
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/activities")
@Api(value = "activities", tags = "房产动态管理")
public class ActivitiesController {

    private final ActivitiesService activitiesService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param activities 房产动态
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:activities:index')")
    public R getPage(Page page, Activities activities) {
        return R.ok(activitiesService.page(page, Wrappers.query(activities)));
    }

    /**
     * 房产动态查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "房产动态查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:activities:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(activitiesService.getById(id));
    }

    /**
     * 房产动态新增
     * @param activities 房产动态
     * @return R
     */
    @ApiOperation(value = "房产动态新增")
    @SysLog("新增房产动态")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:activities:add')")
    public R save(@RequestBody Activities activities) {
        return R.ok(activitiesService.save(activities));
    }

    /**
     * 房产动态修改
     * @param activities 房产动态
     * @return R
     */
    @ApiOperation(value = "房产动态修改")
    @SysLog("修改房产动态")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:activities:edit')")
    public R updateById(@RequestBody Activities activities) {
        return R.ok(activitiesService.updateById(activities));
    }

    /**
     * 房产动态删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "房产动态删除")
    @SysLog("删除房产动态")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:activities:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(activitiesService.removeById(id));
    }

}
