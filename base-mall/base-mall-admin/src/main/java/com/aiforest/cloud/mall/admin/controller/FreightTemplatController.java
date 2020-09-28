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
import com.aiforest.cloud.mall.common.entity.FreightTemplat;
import com.aiforest.cloud.mall.admin.service.FreightTemplatService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 运费模板
 *
 * @author JL
 * @date 2019-12-24 16:09:31
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/freighttemplat")
@Api(value = "freighttemplat", tags = "运费模板管理")
public class FreightTemplatController {

    private final FreightTemplatService freightTemplatService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param freightTemplat 运费模板
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:freighttemplat:index')")
    public R getPage(Page page, FreightTemplat freightTemplat) {
        return R.ok(freightTemplatService.page(page, Wrappers.query(freightTemplat)));
    }

	/**
	 * list列表
	 * @param freightTemplat
	 * @return
	 */
	@ApiOperation(value = "list列表")
	@GetMapping("/list")
	public List<FreightTemplat> getList(FreightTemplat freightTemplat) {
		return freightTemplatService.list(Wrappers.query(freightTemplat));
	}

    /**
     * 运费模板查询
     * @param id
     * @return R
     */
	@ApiOperation(value = "运费模板查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:freighttemplat:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(freightTemplatService.getById(id));
    }

    /**
     * 运费模板新增
     * @param freightTemplat 运费模板
     * @return R
     */
	@ApiOperation(value = "运费模板新增")
    @SysLog("新增运费模板")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:freighttemplat:add')")
    public R save(@RequestBody FreightTemplat freightTemplat) {
        return R.ok(freightTemplatService.save(freightTemplat));
    }

    /**
     * 运费模板修改
     * @param freightTemplat 运费模板
     * @return R
     */
	@ApiOperation(value = "运费模板修改")
    @SysLog("修改运费模板")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:freighttemplat:edit')")
    public R updateById(@RequestBody FreightTemplat freightTemplat) {
        return R.ok(freightTemplatService.updateById(freightTemplat));
    }

    /**
     * 运费模板删除
     * @param id
     * @return R
     */
	@ApiOperation(value = "运费模板删除")
    @SysLog("删除运费模板")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:freighttemplat:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(freightTemplatService.removeById(id));
    }

}
