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
import com.aiforest.cloud.common.security.util.SecurityUtils;
import com.aiforest.cloud.mall.common.entity.Material;
import com.aiforest.cloud.mall.admin.service.MaterialService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 素材
 *
 * @author JL
 * @date 2019-10-26 19:23:45
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/material")
@Api(value = "material", tags = "素材管理")
public class MaterialController {

    private final MaterialService materialService;

    /**
    * 分页查询
    * @param page 分页对象
    * @param material 素材
    * @return
    */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:material:index')")
    public R getMaterialPage(Page page, Material material) {
        return R.ok(materialService.page(page,Wrappers.query(material)));
    }

    /**
    * 通过id查询素材
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询素材")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:material:get')")
    public R getById(@PathVariable("id") String id){
        return R.ok(materialService.getById(id));
    }

    /**
    * 新增素材
    * @param material 素材
    * @return R
    */
	@ApiOperation(value = "新增素材")
    @SysLog("新增素材")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:material:add')")
    public R save(@RequestBody Material material){
		material.setCreateId(SecurityUtils.getUser().getId());
        return R.ok(materialService.save(material));
    }

    /**
    * 修改素材
    * @param material 素材
    * @return R
    */
	@ApiOperation(value = "修改素材")
    @SysLog("修改素材")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:material:edit')")
    public R updateById(@RequestBody Material material){
        return R.ok(materialService.updateById(material));
    }

    /**
    * 通过id删除素材
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id删除素材")
    @SysLog("删除素材")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:material:del')")
    public R removeById(@PathVariable String id){
        return R.ok(materialService.removeById(id));
    }

}
