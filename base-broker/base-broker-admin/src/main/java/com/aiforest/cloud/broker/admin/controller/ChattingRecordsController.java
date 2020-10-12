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
import com.aiforest.cloud.broker.common.entity.ChattingRecords;
import com.aiforest.cloud.broker.admin.service.ChattingRecordsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 聊天记录
 *
 * @author aiforest
 * @date 2020-10-07 10:46:02
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/chattingrecords")
@Api(value = "chattingrecords", tags = "聊天记录管理")
public class ChattingRecordsController {

    private final ChattingRecordsService chattingRecordsService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param chattingRecords 聊天记录
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('broker:chattingrecords:index')")
    public R getPage(Page page, ChattingRecords chattingRecords) {
        return R.ok(chattingRecordsService.page(page, Wrappers.query(chattingRecords)));
    }

    /**
     * 聊天记录查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "聊天记录查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:chattingrecords:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(chattingRecordsService.getById(id));
    }

    /**
     * 聊天记录新增
     * @param chattingRecords 聊天记录
     * @return R
     */
    @ApiOperation(value = "聊天记录新增")
    @SysLog("新增聊天记录")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('broker:chattingrecords:add')")
    public R save(@RequestBody ChattingRecords chattingRecords) {
        return R.ok(chattingRecordsService.save(chattingRecords));
    }

    /**
     * 聊天记录修改
     * @param chattingRecords 聊天记录
     * @return R
     */
    @ApiOperation(value = "聊天记录修改")
    @SysLog("修改聊天记录")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('broker:chattingrecords:edit')")
    public R updateById(@RequestBody ChattingRecords chattingRecords) {
        return R.ok(chattingRecordsService.updateById(chattingRecords));
    }

    /**
     * 聊天记录删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "聊天记录删除")
    @SysLog("删除聊天记录")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('broker:chattingrecords:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(chattingRecordsService.removeById(id));
    }

}
