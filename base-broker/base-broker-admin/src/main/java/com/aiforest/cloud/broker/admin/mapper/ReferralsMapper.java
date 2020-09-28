/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 推荐表
 *
 * @author aiforest
 * @date 2020-09-22 16:07:51
 */
public interface ReferralsMapper extends BaseMapper<Referrals> {

	IPage<Referrals> selectPage1(IPage<Referrals> page, @Param("query") Referrals referrals);

}
