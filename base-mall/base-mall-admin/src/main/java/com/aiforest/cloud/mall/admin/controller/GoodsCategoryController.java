/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.mall.common.entity.GoodsCategory;
import com.aiforest.cloud.mall.common.entity.GoodsCategoryTree;
import com.aiforest.cloud.mall.admin.service.GoodsCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 商品类目
 *
 * @author JL
 * @date 2019-08-12 11:46:28
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/goodscategory")
@Api(value = "goodscategory", tags = "商品类目管理")
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    /**
    * 分页查询
    * @param page 分页对象
    * @param goodsCategory 商品类目
    * @return
    */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:goodscategory:index')")
    public R getGoodsCategoryPage(Page page, GoodsCategory goodsCategory) {
        return R.ok(goodsCategoryService.page(page,Wrappers.query(goodsCategory)));
    }

	/**
	 *  返回树形集合
	 * @return
	 */
	@ApiOperation(value = "返回树形集合")
	@GetMapping("/tree")
	@PreAuthorize("@ato.hasAuthority('mall:goodscategory:index')")
	public R getGoodsCategoryTree() {
		return R.ok(goodsCategoryService.selectTree(null));
	}

    /**
    * 通过id查询商品类目
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询商品类目")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodscategory:get')")
    public R getById(@PathVariable("id") String id){
        return R.ok(goodsCategoryService.getById(id));
    }

    /**
    * 新增商品类目
    * @param goodsCategory 商品类目
    * @return R
    */
	@ApiOperation(value = "新增商品类目")
    @SysLog("新增商品类目")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodscategory:add')")
    public R save(@RequestBody GoodsCategory goodsCategory){
        return R.ok(goodsCategoryService.save(goodsCategory));
    }

    /**
    * 修改商品类目
    * @param goodsCategory 商品类目
    * @return R
    */
	@ApiOperation(value = "修改商品类目")
    @SysLog("修改商品类目")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodscategory:edit')")
    public R updateById(@RequestBody GoodsCategory goodsCategory){
    	if(goodsCategory.getId().equals(goodsCategory.getParentId())){
			return R.failed("不能将本级设为父类");
		}
        return R.ok(goodsCategoryService.updateById(goodsCategory));
    }

    /**
    * 通过id删除商品类目
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id删除商品类目")
    @SysLog("删除商品类目")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodscategory:del')")
    public R removeById(@PathVariable String id){
        return R.ok(goodsCategoryService.removeById(id));
    }

}
