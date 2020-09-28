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
import com.aiforest.cloud.estate.common.entity.Advertisement;
import com.aiforest.cloud.estate.admin.service.AdvertisementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-01 16:51:04
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/advertisement")
@Api(value = "advertisement", tags = "房产布告管理")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param advertisement 房产布告
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_advertisement_index')")
    public R getPage(Page page, Advertisement advertisement) {
        return R.ok(advertisementService.page(page, Wrappers.query(advertisement)));
    }

    /**
     * 房产布告查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_advertisement_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(advertisementService.getById(id));
    }

    /**
     * 房产布告新增
     * @param advertisement 房产布告
     * @return R
     */
    @SysLog("新增房产布告")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_advertisement_add')")
    public R save(@RequestBody Advertisement advertisement) {
        return R.ok(advertisementService.save(advertisement));
    }

    /**
     * 房产布告修改
     * @param advertisement 房产布告
     * @return R
     */
    @SysLog("修改房产布告")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_advertisement_edit')")
    public R updateById(@RequestBody Advertisement advertisement) {
        return R.ok(advertisementService.updateById(advertisement));
    }

    /**
     * 房产布告删除
     * @param id
     * @return R
     */
    @SysLog("删除房产布告")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_advertisement_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(advertisementService.removeById(id));
    }

}
