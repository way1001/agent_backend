/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.ShoppingCartService;
import com.aiforest.cloud.mall.common.entity.ShoppingCart;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车
 *
 * @author JL
 * @date 2019-08-29 21:27:33
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/shoppingcart")
@Api(value = "shoppingcart", tags = "购物车API")
public class ShoppingCartApi{

    private final ShoppingCartService shoppingCartService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param shoppingCart 购物车
	 * @return
	 */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R getShoppingCartPage(Page page, ShoppingCart shoppingCart) {
		shoppingCart.setUserId(ThirdSessionHolder.getMallUserId());
		return R.ok(shoppingCartService.page2(page, shoppingCart));
    }

	/**
	 * 数量
	 * @param shoppingCart
	 * @return
	 */
	@ApiOperation(value = "查询数量")
	@GetMapping("/count")
	public R getShoppingCartCount(ShoppingCart shoppingCart) {
		shoppingCart.setUserId(ThirdSessionHolder.getMallUserId());
		return R.ok(shoppingCartService.count(Wrappers.query(shoppingCart)));
	}

	/**
	 * 加入购物车
	 * @param shoppingCart
	 * @return
	 */
	@ApiOperation(value = "加入购物车")
	@PostMapping
	public R save(@RequestBody ShoppingCart shoppingCart){
		shoppingCart.setUserId(ThirdSessionHolder.getMallUserId());
		return R.ok(shoppingCartService.save(shoppingCart));
	}

	/**
	 * 修改购物车商品
	 * @param shoppingCart
	 * @return
	 */
	@ApiOperation(value = "修改购物车商品")
	@PutMapping
	public R edit(@RequestBody ShoppingCart shoppingCart){
		shoppingCart.setUserId(ThirdSessionHolder.getMallUserId());
		return R.ok(shoppingCartService.updateById(shoppingCart));
	}

	/**
	 * 删除购物车商品数量
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除购物车商品数量")
	@PostMapping("/del")
	public R del(@RequestBody List<String> ids){
		return R.ok(shoppingCartService.removeByIds(ids));
	}
}
