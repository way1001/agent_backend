/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import com.aiforest.cloud.weixin.admin.config.open.WxOpenConfiguration;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发平台API
 * @author JL
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/open/api")
@Api(value = "openapi", tags = "开发平台api")
public class WxOpenApiController {

	/**
	 * getAuthorizerInfo
	 * @param appId
	 * @return
	 */
    @GetMapping("/authorizer-info/{appId}")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
    public R getAuthorizerInfo(@PathVariable("appId") String appId){
        try {
            WxOpenAuthorizerInfoResult wxOpenAuthorizerInfoResult = WxOpenConfiguration.getOpenService().getWxOpenComponentService().getAuthorizerInfo(appId);
//			WxOpenAuthorizerInfo wxOpenAuthorizerInfo = wxOpenAuthorizerInfoResult.getAuthorizerInfo();
			return R.ok(wxOpenAuthorizerInfoResult);
        } catch (WxErrorException e) {
			e.printStackTrace();
			log.error("getAuthorizerInfo：", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
        }
    }
}
