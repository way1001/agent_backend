/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.mall.common.entity.GoodsAppraises;

import java.util.List;

/**
 * 商品评价
 *
 * @author JL
 * @date 2019-09-23 15:51:01
 */
public interface GoodsAppraisesService extends IService<GoodsAppraises> {

	/**
	 * 发表评价
	 * @param listGoodsAppraises
	 */
	void doAppraises(List<GoodsAppraises> listGoodsAppraises);

	IPage<GoodsAppraises> page1(IPage<GoodsAppraises> page, GoodsAppraises godsAppraises);
}
