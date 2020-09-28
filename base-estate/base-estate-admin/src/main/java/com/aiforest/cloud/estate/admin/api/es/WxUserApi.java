/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.estate.common.feign.FeignWxUserService;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信用户
 *
 * @author way
 * @date 2020-04-03 16:00:00
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/wxuser")
@Api(value = "userinfo", tags = "微信用户API")
public class WxUserApi {

	private final FeignWxUserService feignWxUserService;
	private final UserInfoService userInfoService;

	/**
	 * 获取用户信息
	 * @param
	 * @return
	 */
	@GetMapping
	public R getById(){
//		WxUser wxUser = new WxUser();
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(wxUser, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return feignWxUserService.getById(ThirdSessionHolder.getThirdSession().getWxUserId(), SecurityConstants.FROM_IN);
	}

	/**
	 * 保存用户信息
	 * @param wxOpenDataDTO
	 * @return
	 */
	@PostMapping
	public R save(HttpServletRequest request, @RequestBody WxOpenDataDTO wxOpenDataDTO){
//		WxUser wxUser = new WxUser();
//		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(wxUser, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		wxOpenDataDTO.setAppId(wxUser.getAppId());
//		wxOpenDataDTO.setUserId(wxUser.getId());
//		//
//		wxOpenDataDTO.setSessionKey(wxUser.getSessionKey());
//		return userInfoService.saveByWxUser(wxOpenDataDTO);

		wxOpenDataDTO.setAppId(ThirdSessionHolder.getThirdSession().getAppId());
		wxOpenDataDTO.setUserId(ThirdSessionHolder.getThirdSession().getWxUserId());
		wxOpenDataDTO.setSessionKey(ThirdSessionHolder.getThirdSession().getSessionKey());
		return userInfoService.saveByWxUser(wxOpenDataDTO);
	}
}
