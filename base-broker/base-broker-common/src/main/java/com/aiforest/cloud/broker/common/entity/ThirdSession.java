package com.aiforest.cloud.broker.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThirdSession implements Serializable {
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户编码
	 */
	private Integer userCode;
	/**
	 * 微信sessionKey
	 */
	private String sessionKey;

}
