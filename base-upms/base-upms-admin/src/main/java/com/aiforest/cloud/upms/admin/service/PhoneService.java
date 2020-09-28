package com.aiforest.cloud.upms.admin.service;

import com.aliyuncs.exceptions.ClientException;
import com.aiforest.cloud.common.core.util.R;

/**
 * @author
 */
public interface PhoneService {
	/**
	 * 发送手机验证码
	 *
	 * @param phone phone
	 * @param type 操作类型（1、登录；2、绑定手机号）
	 * @return code
	 */
	R<Boolean> sendSmsCode(String phone, String type) throws ClientException;
}
