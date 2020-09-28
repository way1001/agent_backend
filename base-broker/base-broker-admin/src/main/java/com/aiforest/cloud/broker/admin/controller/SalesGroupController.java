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
import com.aiforest.cloud.broker.common.entity.SalesGroup;
import com.aiforest.cloud.broker.admin.service.SalesGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 销售分组
 *
 * @author aiforest
 * @date 2020-08-31 08:43:26
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/salesgroup")
@Api(value = "salesgroup", tags = "销售分组管理")
public class SalesGroupController {

    private final SalesGroupService salesGroupService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param salesGroup 销售分组
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:salesgroup:index')")
    public R getPage(Page page, SalesGroup salesGroup) {
        return R.ok(salesGroupService.page(page, Wrappers.query(salesGroup)));
    }

    /**
     * 销售分组查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "销售分组查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:salesgroup:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(salesGroupService.getById(id));
    }

    /**
     * 销售分组新增
     * @param salesGroup 销售分组
     * @return R
     */
    @ApiOperation(value = "销售分组新增")
    @SysLog("新增销售分组")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:salesgroup:add')")
    public R save(@RequestBody SalesGroup salesGroup) {
        return R.ok(salesGroupService.save(salesGroup));
    }

    /**
     * 销售分组修改
     * @param salesGroup 销售分组
     * @return R
     */
    @ApiOperation(value = "销售分组修改")
    @SysLog("修改销售分组")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:salesgroup:edit')")
    public R updateById(@RequestBody SalesGroup salesGroup) {
        return R.ok(salesGroupService.updateById(salesGroup));
    }

    /**
     * 销售分组删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "销售分组删除")
    @SysLog("删除销售分组")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:salesgroup:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(salesGroupService.removeById(id));
    }

}
