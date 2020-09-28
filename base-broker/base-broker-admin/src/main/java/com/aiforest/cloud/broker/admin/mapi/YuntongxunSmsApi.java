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
import com.aiforest.cloud.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 云通讯短信
 *
 * @author way
 * @date 2020-05-09 15:52:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mapi/br/yuntongxun")
@Api(value = "yuntongxun", tags = "云通讯短信")
public class YuntongxunSmsApi {

	private final UserInfoService userInfoService;

	@PutMapping("/mac")
	public R sendInvitationSms(@RequestParam("phone") String phone){
		return userInfoService.getSMSVerificationCode(phone);
	}
}
