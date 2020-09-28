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
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.mall.common.entity.GoodsSkuSpecValue;
import com.aiforest.cloud.mall.admin.service.GoodsSkuSpecValueService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 商品sku规格值
 *
 * @author JL
 * @date 2019-08-13 10:19:09
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/goodsskuspecvalue")
@Api(value = "goodsskuspecvalue", tags = "商品sku规格值管理")
public class GoodsSkuSpecValueController {

    private final GoodsSkuSpecValueService goodsSkuSpecValueService;

    /**
    * 分页查询
    * @param page 分页对象
    * @param goodsSkuSpecValue 商品sku规格值
    * @return
    */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:goodsskuspecvalue:index')")
    public R getGoodsSkuSpecValuePage(Page page, GoodsSkuSpecValue goodsSkuSpecValue) {
        return R.ok(goodsSkuSpecValueService.page(page,Wrappers.query(goodsSkuSpecValue)));
    }


    /**
    * 通过id查询商品sku规格值
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询商品sku规格值")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodsskuspecvalue:get')")
    public R getById(@PathVariable("id") String id){
        return R.ok(goodsSkuSpecValueService.getById(id));
    }

    /**
    * 新增商品sku规格值
    * @param goodsSkuSpecValue 商品sku规格值
    * @return R
    */
	@ApiOperation(value = "新增商品sku规格值")
    @SysLog("新增商品sku规格值")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodsskuspecvalue:add')")
    public R save(@RequestBody GoodsSkuSpecValue goodsSkuSpecValue){
        return R.ok(goodsSkuSpecValueService.save(goodsSkuSpecValue));
    }

    /**
    * 修改商品sku规格值
    * @param goodsSkuSpecValue 商品sku规格值
    * @return R
    */
	@ApiOperation(value = "修改商品sku规格值")
    @SysLog("修改商品sku规格值")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:goodsskuspecvalue:edit')")
    public R updateById(@RequestBody GoodsSkuSpecValue goodsSkuSpecValue){
        return R.ok(goodsSkuSpecValueService.updateById(goodsSkuSpecValue));
    }

    /**
    * 通过id删除商品sku规格值
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id删除商品sku规格值")
    @SysLog("删除商品sku规格值")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:goodsskuspecvalue:del')")
    public R removeById(@PathVariable String id){
        return R.ok(goodsSkuSpecValueService.removeById(id));
    }

}
