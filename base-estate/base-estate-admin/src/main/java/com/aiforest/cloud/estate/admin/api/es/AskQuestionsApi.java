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
import com.aiforest.cloud.estate.admin.service.AskQuestionsService;
import com.aiforest.cloud.estate.admin.service.AwesomeShootingService;
import com.aiforest.cloud.estate.common.constant.MyReturnCode;
import com.aiforest.cloud.estate.common.dto.AuditDTO;
import com.aiforest.cloud.estate.common.entity.AskQuestions;
import com.aiforest.cloud.estate.common.entity.AwesomeShooting;
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
@RequestMapping("/api/es/askquestions")
@Api(value = "askquestions", tags = "房产提问")
public class AskQuestionsApi {

	private final AskQuestionsService askQuestionsService;

	/**
	 * 查询炫拍
	 *
	 * @return R
	 */
	@GetMapping("/page")
	public R getPage(Page page, AuditDTO auditDTO) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(askQuestionsService.page1(page, auditDTO));
	}


	/**
	 * 新增炫拍
	 * @param
	 * @return R
	 */
	@PostMapping
	public R save(@RequestBody AskQuestions askQuestions){
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(askQuestions, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		awesomeShootingService.save(awesomeShooting);
		askQuestions.setCreateId(ThirdSessionHolder.getEstateUserId());
		return R.ok(askQuestionsService.save(askQuestions));
	}

	@PutMapping
	public R updateById(@RequestBody AskQuestions askQuestions) {
		return R.ok(askQuestionsService.updateById(askQuestions));
	}

}
