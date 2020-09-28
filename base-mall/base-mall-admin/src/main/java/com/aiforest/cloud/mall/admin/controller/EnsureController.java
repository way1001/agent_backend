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
import com.aiforest.cloud.mall.common.entity.DeliveryPlace;
import com.aiforest.cloud.mall.common.entity.Ensure;
import com.aiforest.cloud.mall.admin.service.EnsureService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 保障服务
 *
 * @author JL
 * @date 2020-02-09 23:31:36
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/ensure")
@Api(value = "ensure", tags = "保障服务管理")
public class EnsureController {

    private final EnsureService ensureService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param ensure 保障服务
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:ensure:index')")
    public R getPage(Page page, Ensure ensure) {
        return R.ok(ensureService.page(page, Wrappers.query(ensure)));
    }

    /**
     * 保障服务查询
     * @param id
     * @return R
     */
	@ApiOperation(value = "保障服务查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:ensure:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(ensureService.getById(id));
    }

	/**
	 * list查询
	 * @param ensure
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public List<Ensure> getList(Ensure ensure) {
		return ensureService.list(Wrappers.query(ensure).lambda()
				.select(Ensure::getId,
						Ensure::getName));
	}

    /**
     * 保障服务新增
     * @param ensure 保障服务
     * @return R
     */
	@ApiOperation(value = "保障服务新增")
    @SysLog("新增保障服务")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:ensure:add')")
    public R save(@RequestBody Ensure ensure) {
        return R.ok(ensureService.save(ensure));
    }

    /**
     * 保障服务修改
     * @param ensure 保障服务
     * @return R
     */
	@ApiOperation(value = "保障服务修改")
    @SysLog("修改保障服务")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:ensure:edit')")
    public R updateById(@RequestBody Ensure ensure) {
        return R.ok(ensureService.updateById(ensure));
    }

    /**
     * 保障服务删除
     * @param id
     * @return R
     */
	@ApiOperation(value = "保障服务删除")
    @SysLog("删除保障服务")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:ensure:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(ensureService.removeById(id));
    }

}
