/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 * 注意：
 * 本软件为www.joolun.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.api.es;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.admin.config.ma.WxMaConfiguration;
import com.aiforest.cloud.weixin.admin.config.mp.WxMpConfiguration;
import com.aiforest.cloud.weixin.admin.service.WxUserService;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import com.aiforest.cloud.weixin.common.dto.LoginMaDTO;
import com.aiforest.cloud.weixin.common.entity.WxApp;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
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
@RequestMapping("/api/es/wxuser")
@Api(value = "eswxuser", tags = "房产小程序用户API")
public class EsWxUserApi {

	private final WxUserService wxUserService;
	/**
	 * 小程序用户登录
	 * @param request
	 * @param loginMaDTO
	 * @return
	 */
	@ApiOperation(value = "小程序用户登录")
	@PostMapping("/login")
	public R login(HttpServletRequest request, @RequestBody LoginMaDTO loginMaDTO){
		try {
			WxApp wxApp = WxMaConfiguration.getApp(request);
			WxUser wxUser = wxUserService.loginEs(wxApp,loginMaDTO);
			return R.ok(wxUser);
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}
	@ApiOperation(value = "获取access-token")
	@GetMapping("/access-token")
	public R getAccessToken(WxApp wxApp) {
		try {
			WxMaService wxMaService = WxMaConfiguration.getMaService(wxApp.getId());
			return R.ok(wxMaService.getAccessToken());
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取access-token失败appID:" + wxApp.getId() + ":" + e.getMessage());
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}
}
