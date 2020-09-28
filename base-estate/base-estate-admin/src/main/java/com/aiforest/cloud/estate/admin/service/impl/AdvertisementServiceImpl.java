/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import com.aiforest.cloud.estate.common.dto.AdvertisementDTO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.estate.common.entity.Advertisement;
import com.aiforest.cloud.estate.admin.mapper.AdvertisementMapper;
import com.aiforest.cloud.estate.admin.service.AdvertisementService;
import org.springframework.stereotype.Service;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-01 16:51:04
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {
	@Override
	public Advertisement getOne1(AdvertisementDTO advertisementDTO) {
		return baseMapper.getOne2(advertisementDTO);
	}
}
