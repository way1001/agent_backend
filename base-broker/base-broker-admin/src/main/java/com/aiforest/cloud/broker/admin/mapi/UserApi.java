/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 * 注意：
 * 本软件为www.joolun.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.mapi;

import com.aiforest.cloud.broker.admin.service.UserInfoService;

import com.aiforest.cloud.broker.common.dto.LoginBrDTO;
import com.aiforest.cloud.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 微信用户
 *
 * @author JL
 * @date 2019-08-25 15:39:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mapi/br/userinfo")
@Api(value = "userinfo", tags = "用户API")
public class UserApi {

	private final UserInfoService userInfoService;
	/**
	 * 用户登录
	 * @param //loginBrDTO
	 * @return
	 */
	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
//	public R login(@RequestBody LoginBrDTO loginBrDTO){
//		try {
//			return R.ok(userInfoService.loginBr(loginBrDTO.getPhone(), loginBrDTO.getCaptcha()));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return R.failed(e.getMessage());
//		}
//	}
	public R login(@RequestParam("phone") String phone, @RequestParam("captcha") String captcha){
		try {
			return R.ok(userInfoService.loginBr(phone, captcha));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}



}
