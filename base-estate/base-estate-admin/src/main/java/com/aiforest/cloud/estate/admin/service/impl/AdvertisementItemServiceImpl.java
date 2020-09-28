/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.estate.admin.mapper.AdvertisementMapper;
import com.aiforest.cloud.estate.common.entity.Advertisement;
import com.aiforest.cloud.estate.common.entity.AdvertisementItem;
import com.aiforest.cloud.estate.admin.mapper.AdvertisementItemMapper;
import com.aiforest.cloud.estate.admin.service.AdvertisementItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 房产布告详情
 *
 * @author way
 * @date 2020-04-01 17:21:07
 */
@Service
@AllArgsConstructor
public class AdvertisementItemServiceImpl extends ServiceImpl<AdvertisementItemMapper, AdvertisementItem> implements AdvertisementItemService {
	private final AdvertisementMapper advertisementMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(AdvertisementItem entity) {
		Advertisement advertisement = new Advertisement();
//		advertisement.setAppId(entity.getAppId());
		advertisement.setType(entity.getAdvertisementType());
		Advertisement advertisement2 = advertisementMapper.selectOne(Wrappers.query(advertisement));
		if(advertisement2 == null){
			advertisement.setEnable(CommonConstants.YES);
			advertisementMapper.insert(advertisement);
			entity.setAdvertisementId(advertisement.getId());
		}else{
			entity.setAdvertisementId(advertisement2.getId());
		}
		return super.save(entity);
	}
}
