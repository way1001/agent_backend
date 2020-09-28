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
import com.aiforest.cloud.broker.common.entity.Housing;
import com.aiforest.cloud.broker.admin.service.HousingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 房源信息
 *
 * @author aiforest
 * @date 2020-08-29 16:56:16
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/housing")
@Api(value = "housing", tags = "房源信息管理")
public class HousingController {

    private final HousingService housingService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param housing 房源信息
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:housing:index')")
    public R getPage(Page page, Housing housing) {
        return R.ok(housingService.page(page, Wrappers.query(housing)));
    }

    /**
     * 房源信息查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "房源信息查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:housing:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(housingService.getById(id));
    }

    /**
     * 房源信息新增
     * @param housing 房源信息
     * @return R
     */
    @ApiOperation(value = "房源信息新增")
    @SysLog("新增房源信息")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:housing:add')")
    public R save(@RequestBody Housing housing) {
        return R.ok(housingService.save(housing));
    }

    /**
     * 房源信息修改
     * @param housing 房源信息
     * @return R
     */
    @ApiOperation(value = "房源信息修改")
    @SysLog("修改房源信息")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:housing:edit')")
    public R updateById(@RequestBody Housing housing) {
        return R.ok(housingService.updateById(housing));
    }

    /**
     * 房源信息删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "房源信息删除")
    @SysLog("删除房源信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:housing:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(housingService.removeById(id));
    }

}
