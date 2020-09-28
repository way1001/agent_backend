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
import com.aiforest.cloud.estate.common.entity.NewsTrends;
import com.aiforest.cloud.estate.admin.service.NewsTrendsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产动态
 *
 * @author way
 * @date 2020-04-09 09:05:49
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/newstrends")
@Api(value = "newstrends", tags = "房产动态管理")
public class NewsTrendsController {

    private final NewsTrendsService newsTrendsService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param newsTrends 房产动态
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:newstrends:index')")
    public R getPage(Page page, NewsTrends newsTrends) {
        return R.ok(newsTrendsService.page(page, Wrappers.query(newsTrends)));
    }

    /**
     * 房产动态查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:newstrends:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(newsTrendsService.getById(id));
    }

    /**
     * 房产动态新增
     * @param newsTrends 房产动态
     * @return R
     */
    @SysLog("新增房产动态")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:newstrends:add')")
    public R save(@RequestBody NewsTrends newsTrends) {
        return R.ok(newsTrendsService.save(newsTrends));
    }

    /**
     * 房产动态修改
     * @param newsTrends 房产动态
     * @return R
     */
    @SysLog("修改房产动态")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:newstrends:edit')")
    public R updateById(@RequestBody NewsTrends newsTrends) {
        return R.ok(newsTrendsService.updateById(newsTrends));
    }

    /**
     * 房产动态删除
     * @param id
     * @return R
     */
    @SysLog("删除房产动态")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:newstrends:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(newsTrendsService.removeById(id));
    }

}
