/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.codegen.entity.GenTable;
import com.aiforest.cloud.codegen.service.GenTableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码生成配置表
 *
 * @author JL
 * @date 2020-04-07 19:14:52
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/gentable")
@Api(value = "gentable", tags = "代码生成配置表管理")
public class GenTableController {

    private final GenTableService genTableService;

    /**
     * 代码生成配置表查询
     * @param tableName
     * @return R
     */
    @ApiOperation(value = "代码生成配置表查询")
    @GetMapping("/{tableName}")
    public R getById(@PathVariable("tableName") String tableName) {
        return R.ok(genTableService.getOne(Wrappers.<GenTable>query().lambda()
				.eq(GenTable::getTableName,tableName)));
    }

    /**
     * 代码生成配置表修改
     * @param genTable 代码生成配置表
     * @return R
     */
    @ApiOperation(value = "代码生成配置表修改")
    @SysLog("修改代码生成配置表")
    @PutMapping
    public R updateById(@RequestBody GenTable genTable) {
        return R.ok(genTableService.saveOrUpdate(genTable));
    }

}
