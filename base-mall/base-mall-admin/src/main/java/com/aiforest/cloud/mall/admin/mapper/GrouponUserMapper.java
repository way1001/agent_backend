/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.aiforest.cloud.mall.common.entity.GrouponUser;
import org.apache.ibatis.annotations.Param;

/**
 * 拼团记录
 *
 * @author JL
 * @date 2020-03-17 12:01:53
 */
public interface GrouponUserMapper extends BaseMapper<GrouponUser> {

	IPage<GrouponUser> selectPage1(IPage<GrouponUser> page, @Param("query") GrouponUser grouponUser);

	IPage<GrouponUser> selectPage2(IPage<GrouponUser> page, @Param("query") GrouponUser grouponUser);

	IPage<GrouponUser> selectPageGrouponing(IPage<GrouponUser> page, @Param("query") GrouponUser grouponUser);

	Integer selectCountGrouponing(String groupId);
}
