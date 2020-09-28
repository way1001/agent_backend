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
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import com.aiforest.cloud.estate.admin.service.SmsRecordsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 短信记录
 *
 * @author way
 * @date 2020-05-09 17:21:38
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/smsrecords")
@Api(value = "smsrecords", tags = "短信记录管理")
public class SmsRecordsController {

    private final SmsRecordsService smsRecordsService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param smsRecords 短信记录
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:smsrecords:index')")
    public R getPage(Page page, SmsRecords smsRecords) {
        return R.ok(smsRecordsService.page1(page, smsRecords));
    }

    /**
     * 短信记录查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "短信记录查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:smsrecords:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(smsRecordsService.getById(id));
    }

    /**
     * 短信记录新增
     * @param smsRecords 短信记录
     * @return R
     */
    @ApiOperation(value = "短信记录新增")
    @SysLog("新增短信记录")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:smsrecords:add')")
    public R save(@RequestBody SmsRecords smsRecords) {
        return R.ok(smsRecordsService.save(smsRecords));
    }

    /**
     * 短信记录修改
     * @param smsRecords 短信记录
     * @return R
     */
    @ApiOperation(value = "短信记录修改")
    @SysLog("修改短信记录")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:smsrecords:edit')")
    public R updateById(@RequestBody SmsRecords smsRecords) {
        return R.ok(smsRecordsService.updateById(smsRecords));
    }

    /**
     * 短信记录删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "短信记录删除")
    @SysLog("删除短信记录")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:smsrecords:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(smsRecordsService.removeById(id));
    }

}
