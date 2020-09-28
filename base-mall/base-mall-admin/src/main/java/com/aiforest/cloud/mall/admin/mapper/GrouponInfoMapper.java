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
import com.aiforest.cloud.mall.common.entity.GrouponInfo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * 拼团
 *
 * @author JL
 * @date 2020-03-17 11:55:32
 */
public interface GrouponInfoMapper extends BaseMapper<GrouponInfo> {

	IPage<GrouponInfo> selectPage2(IPage<GrouponInfo> page, @Param("query") GrouponInfo grouponInfo);

	GrouponInfo selectById2(Serializable id);
}
