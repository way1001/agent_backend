/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.mall.admin.mapper.GoodsSpecValueMapper;
import com.aiforest.cloud.mall.common.entity.GoodsSpec;
import com.aiforest.cloud.mall.admin.mapper.GoodsSpecMapper;
import com.aiforest.cloud.mall.admin.service.GoodsSpecService;
import com.aiforest.cloud.mall.common.entity.GoodsSpecValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 规格
 *
 * @author JL
 * @date 2019-08-13 16:10:54
 */
@Service
@AllArgsConstructor
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec> implements GoodsSpecService {

	private final GoodsSpecValueMapper goodsSpecValueMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean removeById(Serializable id) {
		goodsSpecValueMapper.delete(Wrappers.<GoodsSpecValue>update().lambda()
				.eq(GoodsSpecValue::getSpecId, id));
		return super.removeById(id);
	}
}
