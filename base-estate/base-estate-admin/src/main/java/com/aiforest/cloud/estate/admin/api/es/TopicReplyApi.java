/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.TopicReplyService;
import com.aiforest.cloud.estate.common.entity.TopicReply;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

/**
 * 房产回复
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/topicreply")
@Api(value = "topicreply", tags = "房产回复")
public class TopicReplyApi {

	private final TopicReplyService topicReplyService;

	@PostMapping
	public R save(@RequestBody TopicReply topicReply) {
//		R checkThirdSession = BaseApi.checkThirdSession(topicReply, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		topicReply.setCreateId(ThirdSessionHolder.getEstateUserId());
		return R.ok(topicReplyService.save(topicReply));
	}

	@PostMapping("/thumbup")
	public R likes(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		String userId = param.get("userId");
		return R.ok(topicReplyService.thumbUp(id, userId));
	}

	@GetMapping("/page")
	public R getPage(Page page, TopicReply topicReply) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		topicReply.setCreateId(ThirdSessionHolder.getEstateUserId());
		return R.ok(topicReplyService.page2(page, topicReply));
	}

	@PutMapping
	public R updateById(@RequestBody TopicReply topicReply) {
		return R.ok(topicReplyService.updateById(topicReply));
	}

	@GetMapping("/page2")
	public R getPage2(Page page, TopicReply topicReply) {
		return R.ok(topicReplyService.page1(page, topicReply));
	}

}
