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
import com.aiforest.cloud.estate.common.entity.PlatformInfo;
import com.aiforest.cloud.estate.admin.service.PlatformInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 房产平台信息
 *
 * @author way
 * @date 2020-08-19 10:38:07
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/platforminfo")
@Api(value = "platforminfo", tags = "房产平台信息管理")
public class PlatformInfoController {

    private final PlatformInfoService platformInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param platformInfo 房产平台信息
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:platforminfo:index')")
    public R getPage(Page page, PlatformInfo platformInfo) {
        return R.ok(platformInfoService.page(page, Wrappers.query(platformInfo)));
    }

    /**
     * 房产平台信息查询
     *
     * @return R
     */
    @ApiOperation(value = "房产平台信息查询")
	@GetMapping()
    @PreAuthorize("@ato.hasAuthority('estate:platforminfo:get')")
	public R get() {
		return R.ok(platformInfoService.getOne(Wrappers.emptyWrapper()));
	}


	@ApiOperation(value = "房产平台信息查询")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('estate:platforminfo:get')")
	public R getById(@PathVariable("id") String id) {
		return R.ok(platformInfoService.getById(id));
	}

    /**
     * 房产平台信息新增
     * @param platformInfo 房产平台信息
     * @return R
     */
    @ApiOperation(value = "房产平台信息新增")
    @SysLog("新增房产平台信息")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:platforminfo:add')")
    public R save(@RequestBody PlatformInfo platformInfo) {
        return R.ok(platformInfoService.save(platformInfo));
    }

    /**
     * 房产平台信息修改
     * @param platformInfo 房产平台信息
     * @return R
     */
    @ApiOperation(value = "房产平台信息修改")
    @SysLog("修改房产平台信息")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:platforminfo:edit')")
    public R updateById(@RequestBody PlatformInfo platformInfo) {
        return R.ok(platformInfoService.saveOrUpdate(platformInfo));
    }

    /**
     * 房产平台信息删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "房产平台信息删除")
    @SysLog("删除房产平台信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:platforminfo:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(platformInfoService.removeById(id));
    }

}
