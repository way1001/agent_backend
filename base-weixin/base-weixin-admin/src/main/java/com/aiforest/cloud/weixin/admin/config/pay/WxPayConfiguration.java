/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.config.pay;

import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.aiforest.cloud.weixin.admin.service.WxAppService;
import com.aiforest.cloud.weixin.common.entity.WxApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付Configuration
 * @author JL
 *
 */
@Slf4j
@Configuration
public class WxPayConfiguration {

	private static WxAppService wxAppService;

	@Autowired
	public WxPayConfiguration( WxAppService wxAppService) {
		this.wxAppService = wxAppService;
	}

	/**
	 *  获取WxMpService
	 * @param appId
	 * @return
	 */
	public static WxPayService getPayService(String appId) {
		WxPayService wxPayService = null;
		WxApp wxApp = wxAppService.findByAppId(appId);
		if(wxApp!=null) {
			if(StrUtil.isNotBlank(wxApp.getMchId()) && StrUtil.isNotBlank(wxApp.getMchKey())){
				WxPayConfig payConfig = new WxPayConfig();
				payConfig.setAppId(wxApp.getId());
				payConfig.setMchId(wxApp.getMchId());
				payConfig.setMchKey(wxApp.getMchKey());
				payConfig.setKeyPath(wxApp.getKeyPath());
				// 可以指定是否使用沙箱环境
				payConfig.setUseSandboxEnv(false);
				wxPayService = new WxPayServiceImpl();
				wxPayService.setConfig(payConfig);
			}
		}else{
			throw new RuntimeException("无此小程序：" + appId);
		}
		return wxPayService;
    }

}
