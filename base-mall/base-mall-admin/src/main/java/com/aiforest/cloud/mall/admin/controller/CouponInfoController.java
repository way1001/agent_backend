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
import com.aiforest.cloud.mall.common.entity.CouponInfo;
import com.aiforest.cloud.mall.admin.service.CouponInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 电子券
 *
 * @author JL
 * @date 2019-12-14 11:30:58
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/couponinfo")
@Api(value = "couponinfo", tags = "电子券管理")
public class CouponInfoController {

    private final CouponInfoService couponInfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param couponInfo 电子券
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:couponinfo:index')")
    public R getPage(Page page, CouponInfo couponInfo) {
        return R.ok(couponInfoService.page(page, Wrappers.query(couponInfo)));
    }

	/**
	 * list查询
	 * @param couponInfo
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public List<CouponInfo> getList(CouponInfo couponInfo) {
		return couponInfoService.list(Wrappers.query(couponInfo).lambda()
				.select(CouponInfo::getId,
						CouponInfo::getName));
	}

    /**
     * 通过id查询电子券
     * @param id
     * @return R
     */
	@ApiOperation(value = "通过id查询电子券")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:couponinfo:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(couponInfoService.getById2(id));
    }

    /**
     * 新增电子券
     * @param couponInfo 电子券
     * @return R
     */
	@ApiOperation(value = "新增电子券")
    @SysLog("新增电子券")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:couponinfo:add')")
    public R save(@RequestBody CouponInfo couponInfo) {
        return R.ok(couponInfoService.save(couponInfo));
    }

    /**
     * 修改电子券
     * @param couponInfo 电子券
     * @return R
     */
	@ApiOperation(value = "修改电子券")
    @SysLog("修改电子券")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:couponinfo:edit')")
    public R updateById(@RequestBody CouponInfo couponInfo) {
        return R.ok(couponInfoService.updateById1(couponInfo));
    }

    /**
     * 通过id删除电子券
     * @param id
     * @return R
     */
	@ApiOperation(value = "通过id删除电子券")
    @SysLog("删除电子券")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:couponinfo:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(couponInfoService.removeById(id));
    }

}
