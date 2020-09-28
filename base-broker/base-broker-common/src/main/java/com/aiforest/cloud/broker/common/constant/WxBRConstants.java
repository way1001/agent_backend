package com.aiforest.cloud.broker.common.constant;

import com.aiforest.cloud.common.core.constant.CommonConstants;

/**
 * @author
 */
public interface WxBRConstants extends CommonConstants {

	/**
	 * redis中3rd_session过期时间(单位：小时)
	 */
	long TIME_OUT_SESSION = 360;
	/**
	 * redis中3rd_session拼接前缀
	 */
	String THIRD_SESSION_BEGIN = "wx:br:3rd_session";
}
