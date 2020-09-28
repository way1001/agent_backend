/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.common.yuntongxun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 短信模块配置
 *
 * @author
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "yuntongxun.templates")
public class YuntongxunTemplateProperties {

	/**
	 * #客户通知
	 */
	private String signName1 = "signName1";
	private String templateCode1 = "templateCode1";
	/**
	 * #绑定模块
	 */
	private String signName2 = "signName2";
	private String templateCode2 = "templateCode2";
	/**
	 * #解绑模块
	 */
	private String signName3 = "signName3";
	private String templateCode3 = "templateCode3";

}
