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
import com.aiforest.cloud.broker.common.entity.VariableDefinition;
import com.aiforest.cloud.broker.admin.service.VariableDefinitionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 流程变量定义
 *
 * @author aiforest
 * @date 2020-09-23 10:12:08
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/variabledefinition")
@Api(value = "variabledefinition", tags = "流程变量定义管理")
public class VariableDefinitionController {

    private final VariableDefinitionService variableDefinitionService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param variableDefinition 流程变量定义
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:variabledefinition:index')")
    public R getPage(Page page, VariableDefinition variableDefinition) {
        return R.ok(variableDefinitionService.page(page, Wrappers.query(variableDefinition)));
    }

    /**
     * 流程变量定义查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "流程变量定义查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:variabledefinition:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(variableDefinitionService.getById(id));
    }

    /**
     * 流程变量定义新增
     * @param variableDefinition 流程变量定义
     * @return R
     */
    @ApiOperation(value = "流程变量定义新增")
    @SysLog("新增流程变量定义")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:variabledefinition:add')")
    public R save(@RequestBody VariableDefinition variableDefinition) {
        return R.ok(variableDefinitionService.save(variableDefinition));
    }

	/**
	 * 流程变量定义批量新增
	 * @param variableDefinitionList 流程变量定义
	 * @return R
	 */
	@ApiOperation(value = "流程变量定义批量新增")
	@SysLog("批量新增流程变量定义")
	@PostMapping("/batch")
	@PreAuthorize("@ato.hasAuthority('broker:variabledefinition:add')")
	public R saveBatch(@RequestBody List<VariableDefinition> variableDefinitionList) {
		return R.ok(variableDefinitionService.saveBatch(variableDefinitionList));
	}

    /**
     * 流程变量定义修改
     * @param variableDefinition 流程变量定义
     * @return R
     */
    @ApiOperation(value = "流程变量定义修改")
    @SysLog("修改流程变量定义")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:variabledefinition:edit')")
    public R updateById(@RequestBody VariableDefinition variableDefinition) {
        return R.ok(variableDefinitionService.updateById(variableDefinition));
    }

    /**
     * 流程变量定义删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "流程变量定义删除")
    @SysLog("删除流程变量定义")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:variabledefinition:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(variableDefinitionService.removeById(id));
    }

	/**
	 * 流程变量定义批量删除
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "流程变量定义批量删除")
	@SysLog("删除流程变量批量定义")
	@DeleteMapping("/batch/{id}")
	public R removeByBatch(@PathVariable String id) {
		return R.ok(variableDefinitionService.remove(Wrappers.<VariableDefinition>query()
				.lambda().eq(VariableDefinition::getDefinitionId, id)));
	}

}
