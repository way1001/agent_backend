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
import com.aiforest.cloud.estate.common.entity.ApartmentLayout;
import com.aiforest.cloud.estate.admin.service.ApartmentLayoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产户型
 *
 * @author way
 * @date 2020-04-13 16:05:09
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/apartmentlayout")
@Api(value = "apartmentlayout", tags = "房产户型管理")
public class ApartmentLayoutController {

    private final ApartmentLayoutService apartmentLayoutService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param apartmentLayout 房产户型
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_apartmentlayout_index')")
    public R getPage(Page page, ApartmentLayout apartmentLayout) {
        return R.ok(apartmentLayoutService.page(page, Wrappers.query(apartmentLayout)));
    }

    /**
     * 房产户型查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_apartmentlayout_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(apartmentLayoutService.getById(id));
    }

    /**
     * 房产户型新增
     * @param apartmentLayout 房产户型
     * @return R
     */
    @SysLog("新增房产户型")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_apartmentlayout_add')")
    public R save(@RequestBody ApartmentLayout apartmentLayout) {
        return R.ok(apartmentLayoutService.save(apartmentLayout));
    }

    /**
     * 房产户型修改
     * @param apartmentLayout 房产户型
     * @return R
     */
    @SysLog("修改房产户型")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_apartmentlayout_edit')")
    public R updateById(@RequestBody ApartmentLayout apartmentLayout) {
        return R.ok(apartmentLayoutService.updateById(apartmentLayout));
    }

    /**
     * 房产户型删除
     * @param id
     * @return R
     */
    @SysLog("删除房产户型")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_apartmentlayout_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(apartmentLayoutService.removeById(id));
    }

}
