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
import com.aiforest.cloud.estate.common.entity.UnitReview;
import com.aiforest.cloud.estate.admin.service.UnitReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产户型点评
 *
 * @author way
 * @date 2020-04-09 09:05:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/unitreview")
@Api(value = "unitreview", tags = "房产户型点评管理")
public class UnitReviewController {

    private final UnitReviewService unitReviewService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param unitReview 房产户型点评
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_unitreview_index')")
    public R getPage(Page page, UnitReview unitReview) {
        return R.ok(unitReviewService.page(page, Wrappers.query(unitReview)));
    }

    /**
     * 房产户型点评查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_unitreview_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(unitReviewService.getById(id));
    }

    /**
     * 房产户型点评新增
     * @param unitReview 房产户型点评
     * @return R
     */
    @SysLog("新增房产户型点评")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_unitreview_add')")
    public R save(@RequestBody UnitReview unitReview) {
        return R.ok(unitReviewService.save(unitReview));
    }

    /**
     * 房产户型点评修改
     * @param unitReview 房产户型点评
     * @return R
     */
    @SysLog("修改房产户型点评")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_unitreview_edit')")
    public R updateById(@RequestBody UnitReview unitReview) {
        return R.ok(unitReviewService.updateById(unitReview));
    }

    /**
     * 房产户型点评删除
     * @param id
     * @return R
     */
    @SysLog("删除房产户型点评")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_unitreview_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(unitReviewService.removeById(id));
    }

}
