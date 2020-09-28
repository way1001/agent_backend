/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.estate.common.entity.TopicReply;
import com.aiforest.cloud.estate.admin.service.TopicReplyService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产总回复
 *
 * @author way
 * @date 2020-04-09 09:05:43
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/topicreply")
@Api(value = "topicreply", tags = "房产总回复管理")
public class TopicReplyController {

    private final TopicReplyService topicReplyService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param topicReply 房产总回复
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_topicreply_index')")
    public R getPage(Page page, TopicReply topicReply) {
        return R.ok(topicReplyService.page1(page, topicReply));
    }

    /**
     * 房产总回复查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_topicreply_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(topicReplyService.getById(id));
    }

    /**
     * 房产总回复新增
     * @param topicReply 房产总回复
     * @return R
     */
    @SysLog("新增房产总回复")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_topicreply_add')")
    public R save(@RequestBody TopicReply topicReply) {
        return R.ok(topicReplyService.save(topicReply));
    }

    /**
     * 房产总回复修改
     * @param topicReply 房产总回复
     * @return R
     */
    @SysLog("修改房产总回复")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_topicreply_edit')")
    public R updateById(@RequestBody TopicReply topicReply) {
        return R.ok(topicReplyService.updateById(topicReply));
    }

    /**
     * 房产总回复删除
     * @param id
     * @return R
     */
    @SysLog("删除房产总回复")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_topicreply_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(topicReplyService.removeById(id));
    }

	/**
	 * 回复审核操作
	 * @param audit
	 * @param ids
	 * @return R
	 */
	@ApiOperation(value = "回复审核操作")
	@SysLog("回复审核架操作")
	@PutMapping("/audit")
	@PreAuthorize("@ato.hasAuthority('estate_topicreply_edit')")
	public R updateById(@RequestParam(value = "audit") String audit, @RequestParam(value = "ids") String ids){
		TopicReply topicReply = new TopicReply();
		topicReply.setAuditStatus(audit);
		return R.ok(topicReplyService.update(topicReply,Wrappers.<TopicReply>lambdaQuery()
				.in(TopicReply::getId, Convert.toList(ids))));
	}

}
