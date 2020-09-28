/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.GoodsSpuSpecService;
import com.aiforest.cloud.mall.common.entity.GoodsSpuSpec;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * spu规格
 *
 * @author JL
 * @date 2019-08-13 16:56:46
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/goodsspuspec")
@Api(value = "goodsspuspec", tags = "spu规格接口API")
public class GoodsSpuSpecApi {

    private final GoodsSpuSpecService goodsSpuSpecService;


	/**
	 * 获取商品规格
	 * @param goodsSpuSpec
	 * @return
	 */
	@ApiOperation(value = "获取商品规格")
	@GetMapping("/tree")
	public R getGoodsSpuSpecTree(GoodsSpuSpec goodsSpuSpec) {
		return R.ok(goodsSpuSpecService.tree(goodsSpuSpec));
	}

}
