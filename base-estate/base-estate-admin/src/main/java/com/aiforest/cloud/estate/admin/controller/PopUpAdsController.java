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
import com.aiforest.cloud.estate.common.entity.PopUpAds;
import com.aiforest.cloud.estate.admin.service.PopUpAdsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 弹窗广告
 *
 * @author way
 * @date 2020-06-25 12:56:34
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/popupads")
@Api(value = "popupads", tags = "弹窗广告管理")
public class PopUpAdsController {

    private final PopUpAdsService popUpAdsService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param popUpAds 弹窗广告
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:popupads:index')")
    public R getPage(Page page, PopUpAds popUpAds) {
        return R.ok(popUpAdsService.page(page, Wrappers.query(popUpAds)));
    }

    /**
     * 弹窗广告查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "弹窗广告查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:popupads:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(popUpAdsService.getById(id));
    }

    /**
     * 弹窗广告新增
     * @param popUpAds 弹窗广告
     * @return R
     */
    @ApiOperation(value = "弹窗广告新增")
    @SysLog("新增弹窗广告")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:popupads:add')")
    public R save(@RequestBody PopUpAds popUpAds) {
        return R.ok(popUpAdsService.save(popUpAds));
    }

    /**
     * 弹窗广告修改
     * @param popUpAds 弹窗广告
     * @return R
     */
    @ApiOperation(value = "弹窗广告修改")
    @SysLog("修改弹窗广告")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:popupads:edit')")
    public R updateById(@RequestBody PopUpAds popUpAds) {
        return R.ok(popUpAdsService.saveOrUpdate(popUpAds));
    }

    /**
     * 弹窗广告删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "弹窗广告删除")
    @SysLog("删除弹窗广告")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:popupads:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(popUpAdsService.removeById(id));
    }

	/**
	 * 查询弹窗广告
	 * @return R
	 */
	@ApiOperation(value = "查询弹窗广告")
	@GetMapping()
	public R get() {
		return R.ok(popUpAdsService.getOne(Wrappers.emptyWrapper()));
	}

}
