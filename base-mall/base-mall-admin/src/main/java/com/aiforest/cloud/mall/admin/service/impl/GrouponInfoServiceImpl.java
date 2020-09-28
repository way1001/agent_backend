/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.mall.common.entity.GrouponInfo;
import com.aiforest.cloud.mall.admin.mapper.GrouponInfoMapper;
import com.aiforest.cloud.mall.admin.service.GrouponInfoService;
import org.springframework.stereotype.Service;

/**
 * 拼团
 *
 * @author JL
 * @date 2020-03-17 11:55:32
 */
@Service
public class GrouponInfoServiceImpl extends ServiceImpl<GrouponInfoMapper, GrouponInfo> implements GrouponInfoService {

	@Override
	public IPage<GrouponInfo> page2(IPage<GrouponInfo> page, GrouponInfo grouponInfo) {
		return baseMapper.selectPage2(page, grouponInfo);
	}

	@Override
	public GrouponInfo getById2(String id) {
		return baseMapper.selectById2(id);
	}
}
