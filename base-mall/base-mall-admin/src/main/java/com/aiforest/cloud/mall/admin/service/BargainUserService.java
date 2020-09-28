/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.mall.common.entity.BargainUser;

/**
 * 砍价记录
 *
 * @author JL
 * @date 2019-12-30 11:53:14
 */
public interface BargainUserService extends IService<BargainUser> {

	/**
	 * 发起砍价
	 * @param bargainUser
	 * @return
	 */
	boolean saveBargain(BargainUser bargainUser);

	IPage<BargainUser> page1(IPage<BargainUser> page, BargainUser bargainUser);

	IPage<BargainUser> page2(IPage<BargainUser> page, BargainUser bargainUser);
}
