/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.estate.common.entity.ApartmentLayout;
import com.aiforest.cloud.estate.admin.mapper.ApartmentLayoutMapper;
import com.aiforest.cloud.estate.admin.service.ApartmentLayoutService;
import org.springframework.stereotype.Service;

/**
 * 房产户型
 *
 * @author way
 * @date 2020-04-13 16:05:09
 */
@Service
public class ApartmentLayoutServiceImpl extends ServiceImpl<ApartmentLayoutMapper, ApartmentLayout> implements ApartmentLayoutService {

	@Override
	public IPage<ApartmentLayout> page1(IPage<ApartmentLayout> page, ApartmentLayout apartmentLayout) {
		return baseMapper.selectPage1(page, apartmentLayout);
	}
}
