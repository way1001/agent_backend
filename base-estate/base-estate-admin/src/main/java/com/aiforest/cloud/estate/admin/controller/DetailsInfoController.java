/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.estate.common.entity.DetailsInfo;
import com.aiforest.cloud.estate.admin.service.DetailsInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产商详情
 *
 * @author way
 * @date 2020-04-06 08:39:21
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/detailsinfo")
@Api(value = "detailsinfo", tags = "房产商详情管理")
public class DetailsInfoController {

    private final DetailsInfoService detailsInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param detailsInfo 房产商详情
     * @return
     */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:index')")
	public R getPage(Page page, DetailsInfo detailsInfo) {
		return R.ok(detailsInfoService.page(page, Wrappers.query(detailsInfo)));
	}

	/**
	 * 房产商详情查询
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "房产商详情查询")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:get')")
	public R getById(@PathVariable("id") String id) {
		return R.ok(detailsInfoService.getById(id));
	}

	/**
	 * 房产商详情新增
	 * @param detailsInfo 房产商详情
	 * @return R
	 */
	@ApiOperation(value = "房产商详情新增")
	@SysLog("新增房产商详情")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:add')")
	public R save(@RequestBody DetailsInfo detailsInfo) {
		return R.ok(detailsInfoService.save(detailsInfo));
	}

	/**
	 * 房产商详情修改
	 * @param detailsInfo 房产商详情
	 * @return R
	 */
	@ApiOperation(value = "房产商详情修改")
	@SysLog("修改房产商详情")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:edit')")
	public R updateById(@RequestBody DetailsInfo detailsInfo) {
		return R.ok(detailsInfoService.saveOrUpdate(detailsInfo));
	}

	/**
	 * 房产商详情删除
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "房产商详情删除")
	@SysLog("删除房产商详情")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:del')")
	public R removeById(@PathVariable String id) {
		return R.ok(detailsInfoService.removeById(id));
	}


	/**
	 * 查询基础信息
	 * @return R
	 */
	@ApiOperation(value = "查询详情信息")
	@SysLog("查询房产商详情")
	@GetMapping()
	public R get() {
		return R.ok(detailsInfoService.getOne1(null));
	}

	/**
	 * 查询基础信息
	 * @return R
	 */
	@ApiOperation(value = "查询详情信息")
	@SysLog("查询房产商详情")
	@GetMapping("/aff/{affid}")
	public R getByAffiliation(@PathVariable String affid) {
		if (affid.isEmpty()) {
			return R.failed("无效affId");
		}
		return R.ok(detailsInfoService.getOne1(affid));
	}

}
