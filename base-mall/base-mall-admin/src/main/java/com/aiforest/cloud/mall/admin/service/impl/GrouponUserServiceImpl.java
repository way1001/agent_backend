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
import com.aiforest.cloud.mall.common.entity.GrouponUser;
import com.aiforest.cloud.mall.admin.mapper.GrouponUserMapper;
import com.aiforest.cloud.mall.admin.service.GrouponUserService;
import org.springframework.stereotype.Service;

/**
 * 拼团记录
 *
 * @author JL
 * @date 2020-03-17 12:01:53
 */
@Service
public class GrouponUserServiceImpl extends ServiceImpl<GrouponUserMapper, GrouponUser> implements GrouponUserService {

	@Override
	public IPage<GrouponUser> page1(IPage<GrouponUser> page, GrouponUser grouponUser) {
		return baseMapper.selectPage1(page, grouponUser);
	}

	@Override
	public IPage<GrouponUser> page2(IPage<GrouponUser> page, GrouponUser grouponUser) {
		return baseMapper.selectPage2(page, grouponUser);
	}

	@Override
	public IPage<GrouponUser> getPageGrouponing(IPage<GrouponUser> page, GrouponUser grouponUser) {
		return baseMapper.selectPageGrouponing(page, grouponUser);
	}

	@Override
	public Integer selectCountGrouponing(String groupId) {
		return baseMapper.selectCountGrouponing(groupId);
	}
}
