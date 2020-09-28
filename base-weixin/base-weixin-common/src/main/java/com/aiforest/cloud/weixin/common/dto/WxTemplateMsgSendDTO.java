package com.aiforest.cloud.weixin.common.dto;

import lombok.Data;
import java.util.HashMap;

@Data
public class WxTemplateMsgSendDTO {
	/**
	 * 商城用户ID
	 */
	private String mallUserId;
	/**
	 * 用途
	 */
	private String useType;
	/**
	 * 点击模板卡片后的跳转页面，仅限本小程序内的页面.
	 */
	private String page;
	/**
	 * 模板内容
	 */
	private HashMap<String, HashMap<String, String>> data;
}
