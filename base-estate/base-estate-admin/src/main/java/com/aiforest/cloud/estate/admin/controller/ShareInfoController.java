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
import com.aiforest.cloud.estate.common.entity.ShareInfo;
import com.aiforest.cloud.estate.admin.service.ShareInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产分享
 *
 * @author way
 * @date 2020-04-23 14:59:12
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/shareinfo")
@Api(value = "shareinfo", tags = "房产分享管理")
public class ShareInfoController {

    private final ShareInfoService shareInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param shareInfo 房产分享
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_shareinfo_index')")
    public R getPage(Page page, ShareInfo shareInfo) {
        return R.ok(shareInfoService.page(page, Wrappers.query(shareInfo)));
    }

    /**
     * 房产分享查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_shareinfo_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(shareInfoService.getById(id));
    }

    /**
     * 房产分享新增
     * @param shareInfo 房产分享
     * @return R
     */
    @SysLog("新增房产分享")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_shareinfo_add')")
    public R save(@RequestBody ShareInfo shareInfo) {
        return R.ok(shareInfoService.save(shareInfo));
    }

    /**
     * 房产分享修改
     * @param shareInfo 房产分享
     * @return R
     */
    @SysLog("修改房产分享")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_shareinfo_edit')")
    public R updateById(@RequestBody ShareInfo shareInfo) {
        return R.ok(shareInfoService.updateById(shareInfo));
    }

    /**
     * 房产分享删除
     * @param id
     * @return R
     */
    @SysLog("删除房产分享")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_shareinfo_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(shareInfoService.removeById(id));
    }

}
