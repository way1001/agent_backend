/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.mall.common.entity.GoodsSpu;
import com.aiforest.cloud.mall.admin.service.GoodsSpuService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import java.util.List;

/**
 * spu商品
 *
 * @author JL
 * @date 2019-08-12 16:25:10
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/goodsspu")
@Api(value = "goodsspu", tags = "spu商品管理")
public class GoodsSpuController {

    private final GoodsSpuService goodsSpuService;

    /**
    * 分页查询
    * @param page 分页对象
    * @param goodsSpu spu商品
    * @return
    */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:goodsspu:index')")
    public R getGoodsSpuPage(Page page, GoodsSpu goodsSpu) {
		return R.ok(goodsSpuService.page1(page, goodsSpu));
    }

	/**
	 * list查询
	 * @param goodsSpu
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public List<GoodsSpu> getList(GoodsSpu goodsSpu) {
		return goodsSpuService.list(Wrappers.query(goodsSpu).lambda()
						.select(GoodsSpu::getId,
								GoodsSpu::getName)
				);
	}

	/**
	 * 查询数量
	 * @param goodsSpu
	 * @return
	 */
	@ApiOperation(value = "查询数量")
	@GetMapping("/count")
	public R getCount(GoodsSpu goodsSpu) {
		return R.ok(goodsSpuService.count(Wrappers.query(goodsSpu)));
	}

    /**
    * 通过id查询spu商品
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询spu商品")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodsspu:get')")
    public R getById(@PathVariable("id") String id){
        return R.ok(goodsSpuService.getById1(id));
    }

    /**
    * 新增spu商品
    * @param goodsSpu spu商品
    * @return R
    */
	@ApiOperation(value = "新增spu商品")
    @SysLog("新增spu商品")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodsspu:add')")
    public R save(@RequestBody GoodsSpu goodsSpu){
        return R.ok(goodsSpuService.save1(goodsSpu));
    }

    /**
    * 修改spu商品
    * @param goodsSpu spu商品
    * @return R
    */
	@ApiOperation(value = "修改spu商品")
    @SysLog("修改spu商品")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodsspu:edit')")
    public R updateById(@RequestBody GoodsSpu goodsSpu){
        return R.ok(goodsSpuService.updateById1(goodsSpu));
    }

	/**
	 * 商品上下架操作
	 * @param shelf
	 * @param ids
	 * @return R
	 */
	@ApiOperation(value = "商品上下架操作")
	@SysLog("商品上下架操作")
	@PutMapping("/shelf")
	@PreAuthorize("@ato.hasAuthority('mall:goodsspu:edit')")
	public R updateById(@RequestParam(value = "shelf") String shelf, @RequestParam(value = "ids") String ids){
		GoodsSpu goodsSpu = new GoodsSpu();
		goodsSpu.setShelf(shelf);
		return R.ok(goodsSpuService.update(goodsSpu,Wrappers.<GoodsSpu>lambdaQuery()
				.in(GoodsSpu::getId, Convert.toList(ids))));
	}

    /**
    * 通过id删除spu商品
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id删除spu商品")
    @SysLog("删除spu商品")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodsspu:del')")
    public R removeById(@PathVariable String id){
        return R.ok(goodsSpuService.removeById(id));
    }

}
