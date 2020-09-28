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
import com.aiforest.cloud.estate.admin.service.ReviewsService;
import com.aiforest.cloud.estate.common.dto.AuditDTO;
import com.aiforest.cloud.estate.common.entity.Reviews;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/reviews")
@Api(value = "reviews", tags = "房产点评")
public class ReviewsApi {

	private final ReviewsService reviewsService;

	@PostMapping
	public R save(@RequestBody Reviews reviews) {
//		R checkThirdSession = BaseApi.checkThirdSession(reviews, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		reviews.setCreateId(ThirdSessionHolder.getEstateUserId());
		return R.ok(reviewsService.save(reviews));
	}

//	@GetMapping("/page")
//	public R getPage(HttpServletRequest request, Page page, Reviews reviews) {
//		R checkThirdSession = BaseApi.checkThirdSession(reviews, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		return R.ok(reviewsService.page(page, Wrappers.query(reviews)));
//	}

	@GetMapping("/page")
	public R getPage(Page page, AuditDTO auditDTO) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(reviewsService.page1(page, auditDTO));
	}

	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(reviewsService.getById(id));
	}

	@PostMapping("/addviews")
	public R addViews(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		return R.ok(reviewsService.addViews(id));
	}

	@PostMapping("/thumbup")
	public R likes(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		String userId = param.get("userId");
		return R.ok(reviewsService.thumbUp(id, userId));
	}

	@PutMapping
	public R updateById(@RequestBody Reviews reviews) {
		return R.ok(reviewsService.updateById(reviews));
	}


}
