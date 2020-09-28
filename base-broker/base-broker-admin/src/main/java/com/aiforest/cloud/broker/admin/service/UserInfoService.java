/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.service;

import com.aiforest.cloud.common.core.util.R;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.broker.common.entity.UserInfo;

import java.util.List;

/**
 * 经纪人用户
 *
 * @author aiforest
 * @date 2020-08-31 08:43:29
 */
public interface UserInfoService extends IService<UserInfo> {

	List<UserInfo> list1(String affiliationId, String tenantId);
	UserInfo getById1(String id, String tenantId);
	R<Boolean> getSMSVerificationCode(String phone);
	UserInfo loginBr(String phone, String captcha);
	String findIdByRoleCode(String code);
}
