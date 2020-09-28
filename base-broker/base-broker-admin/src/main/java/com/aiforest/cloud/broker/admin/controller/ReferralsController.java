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
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.aiforest.cloud.broker.admin.service.ReferralsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 推荐表
 *
 * @author aiforest
 * @date 2020-09-22 16:07:51
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/referrals")
@Api(value = "referrals", tags = "推荐表管理")
public class ReferralsController {

    private final ReferralsService referralsService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param referrals 推荐表
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:referrals:index')")
    public R getPage(Page page, Referrals referrals) {
        return R.ok(referralsService.page(page, Wrappers.query(referrals)));
    }

    /**
     * 推荐表查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "推荐表查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:referrals:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(referralsService.getById(id));
    }

    /**
     * 推荐表新增
     * @param referrals 推荐表
     * @return R
     */
    @ApiOperation(value = "推荐表新增")
    @SysLog("新增推荐表")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:referrals:add')")
    public R save(@RequestBody Referrals referrals) {
        return R.ok(referralsService.save(referrals));
    }

    /**
     * 推荐表修改
     * @param referrals 推荐表
     * @return R
     */
    @ApiOperation(value = "推荐表修改")
    @SysLog("修改推荐表")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:referrals:edit')")
    public R updateById(@RequestBody Referrals referrals) {
        return R.ok(referralsService.updateById(referrals));
    }

    /**
     * 推荐表删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "推荐表删除")
    @SysLog("删除推荐表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:referrals:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(referralsService.removeById(id));
    }

}
