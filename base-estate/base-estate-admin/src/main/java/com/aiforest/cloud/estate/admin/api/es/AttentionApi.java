/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.AttentionService;
import com.aiforest.cloud.estate.common.entity.Attention;
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
@RequestMapping("/api/es/attention")
@Api(value = "attention", tags = "房产关注")
public class AttentionApi {

	private final AttentionService attentionService;

	/**
	 * 分页列表
	 * @param attention 房产关注
	 * @return
	 */
	@GetMapping("/list")
	public R getList(Attention attention) {
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(attentionService.list1(attention));
	}

	@GetMapping("/list2")
	public R getList2(Attention attention) {
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(attentionService.list2(attention));
	}
	/**
	 * 新增关注
	 * @param
	 * @return R
	 */
	@PostMapping
	public R save(@RequestBody Attention attention){
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(attention, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		awesomeShootingService.save(awesomeShooting);
		return R.ok(attentionService.save(attention));
	}

	/**
	 * 房产关注查询
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(HttpServletRequest request, @PathVariable("id") String id) {
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(attentionService.getById(id));
	}

	/**
	 * 房产关注
	 * @param param
	 * @return R
	 */
	@PostMapping("/concern")
	public R concern(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		String attentionType = param.get("attentionType");
		String affiliationId = param.get("affiliationId");

		Attention attention = new Attention();
		if (attentionType.equals("6")) {
			attention.setAttentionId(id);
		}
		attention.setAttentionType(attentionType);
		attention.setAffiliationId(affiliationId);
		attention.setUserId(ThirdSessionHolder.getEstateUserId());

		Attention attention2 = attentionService.getOne(Wrappers.query(attention));
		if(attention2 == null){
			attention.setAttentionStatus("0");
			return R.ok(attentionService.saveOrUpdate(attention));
		}else{
			attention2.setAttentionStatus(attention2.getAttentionStatus().equals("0") ? "1" : "0");
			return R.ok(attentionService.saveOrUpdate(attention2));
		}
	}


}
