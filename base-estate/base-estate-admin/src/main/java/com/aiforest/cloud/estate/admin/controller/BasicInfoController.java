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
import com.aiforest.cloud.estate.common.dto.GpsDTO;
import com.aiforest.cloud.estate.common.entity.BasicInfo;
import com.aiforest.cloud.estate.admin.service.BasicInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 房产基础信息
 *
 * @author way
 * @date 2020-04-06 08:39:12
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/basicinfo")
@Api(value = "basicinfo", tags = "房产基础信息管理")
public class BasicInfoController {

    private final BasicInfoService basicInfoService;


    /**
     * 分页列表
     * @param page 分页对象
     * @param basicInfo 房产基础信息
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_basicinfo_index')")
    public R getPage(Page page, BasicInfo basicInfo) {
        return R.ok(basicInfoService.page(page, Wrappers.query(basicInfo)));
    }
	/**
	 * list列表
	 * @param basicInfo
	 * @return
	 */
	@ApiOperation(value = "list列表")
	@GetMapping("/list")
	public List<BasicInfo> getList(BasicInfo basicInfo) {
		return basicInfoService.list(Wrappers.query(basicInfo).lambda()
				.select(BasicInfo::getId,
						BasicInfo::getProjectName));
	}

	/**
	 * 列表
	 * @return
	 */
	@ApiOperation(value = "返回list集合")
	@GetMapping("/all")
	public R getAll() {
		return R.ok(basicInfoService.list());
	}

	/**
	 * 树
	 * @return
	 */
	@ApiOperation(value = "返回树形菜单集合")
	@GetMapping(value = "/tree")
	public R getTree() {
		return R.ok(basicInfoService.selectTree());
	}

    /**
     * 房产基础信息查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_basicinfo_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(basicInfoService.getById(id));
    }

    /**
     * 房产基础信息新增
     * @param basicInfo 房产基础信息
     * @return R
     */
    @SysLog("新增房产基础信息")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_basicinfo_add')")
    public R save(@RequestBody BasicInfo basicInfo) {
        return R.ok(basicInfoService.save(basicInfo));
    }

    /**
     * 房产基础信息修改
     * @param basicInfo 房产基础信息
     * @return R
     */
    @SysLog("修改房产基础信息")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_basicinfo_edit')")
    public R updateById(@RequestBody BasicInfo basicInfo) {
        return R.ok(basicInfoService.saveOrUpdate(basicInfo));
    }

    /**
     * 房产基础信息删除
     * @param id
     * @return R
     */
    @SysLog("删除房产基础信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_basicinfo_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(basicInfoService.removeById(id));
    }

	/**
	 * 查询基础信息
	 * @return R
	 */
	@ApiOperation(value = "查询基础信息")
	@GetMapping()
	public R get() {
		return R.ok(basicInfoService.getOne(Wrappers.emptyWrapper()));
	}

}
