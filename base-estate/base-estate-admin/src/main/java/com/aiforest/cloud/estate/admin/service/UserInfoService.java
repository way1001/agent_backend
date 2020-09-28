/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.estate.common.entity.UnitReview;
import com.aiforest.cloud.estate.common.entity.UserInfo;

/**
 * 房产用户
 *
 * @author way
 * @date 2020-04-03 15:43:34
 */
public interface UserInfoService extends IService<UserInfo> {
	/**
	 * 通过微信用户增加商城用户
	 * @param wxOpenDataDTO
	 * @return
	 */
	R saveByWxUser(WxOpenDataDTO wxOpenDataDTO);

	/**
	 * 新增商城用户（供服务间调用）
	 * @param userInfo
	 * @return
	 */
	boolean saveInside(UserInfo userInfo);

	UserInfo getRandSalesman();

	IPage<UserInfo> page1(IPage<UserInfo> page, UserInfo userInfo);

}
