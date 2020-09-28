/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.UserInfoService;
import com.aiforest.cloud.mall.common.feign.FeignWxUserService;
import com.aiforest.cloud.mall.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信用户
 *
 * @author JL
 * @date 2019-08-25 15:39:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/wxuser")
@Api(value = "userinfo", tags = "微信用户API")
public class WxUserApi {

	private final FeignWxUserService feignWxUserService;
	private final UserInfoService userInfoService;

	/**
	 * 获取用户信息
	 * @param
	 * @return
	 */
	@ApiOperation(value = "获取用户信息")
	@GetMapping
	public R get(){
		return feignWxUserService.getById(ThirdSessionHolder.getThirdSession().getWxUserId(), SecurityConstants.FROM_IN);
	}

	/**
	 * 保存用户信息
	 * @param wxOpenDataDTO
	 * @return
	 */
	@ApiOperation(value = "保存用户信息")
	@PostMapping
	public R save(HttpServletRequest request, @RequestBody WxOpenDataDTO wxOpenDataDTO){
		wxOpenDataDTO.setAppId(ThirdSessionHolder.getThirdSession().getAppId());
		wxOpenDataDTO.setUserId(ThirdSessionHolder.getThirdSession().getWxUserId());
		wxOpenDataDTO.setSessionKey(ThirdSessionHolder.getThirdSession().getSessionKey());
		return userInfoService.saveByWxUser(wxOpenDataDTO);
	}
}
