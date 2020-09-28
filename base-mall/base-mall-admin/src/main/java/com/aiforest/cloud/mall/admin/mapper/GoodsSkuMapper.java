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
import com.aiforest.cloud.mall.common.entity.GoodsSku;

import java.util.List;

/**
 * 商品sku
 *
 * @author JL
 * @date 2019-08-13 10:18:34
 */
public interface GoodsSkuMapper extends BaseMapper<GoodsSku> {

	List<GoodsSku> listGoodsSkuBySpuId(String spuId);

	GoodsSku selectOneByShoppingCart(String id);

	GoodsSku getGoodsSkuById(String id);
}
