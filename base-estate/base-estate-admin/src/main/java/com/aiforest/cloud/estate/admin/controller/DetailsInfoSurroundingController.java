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
import com.aiforest.cloud.estate.common.entity.DetailsInfoSurrounding;
import com.aiforest.cloud.estate.admin.service.DetailsInfoSurroundingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 详情周边
 *
 * @author code generator
 * @date 2020-04-06 19:33:41
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/detailsinfosurrounding")
@Api(value = "detailsinfosurrounding", tags = "详情周边管理")
public class DetailsInfoSurroundingController {

    private final DetailsInfoSurroundingService detailsInfoSurroundingService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param detailsInfoSurrounding 详情周边
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:detailsinfo:index')")
    public R getPage(Page page, DetailsInfoSurrounding detailsInfoSurrounding) {
        return R.ok(detailsInfoSurroundingService.page(page, Wrappers.query(detailsInfoSurrounding)));
    }

	/**
	 * 根据id获取周边内容
	 * @param detailsId
	 * @return
	 */
	@GetMapping("/tree")
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:index')")
	public R getSurroundingTree(String detailsId) {
		return R.ok(detailsInfoSurroundingService.listByDetailsId(detailsId));
	}


	/**
     * 详情周边查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:detailsinfo:del')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(detailsInfoSurroundingService.getById(id));
    }

    /**
     * 详情周边新增
     * @param detailsInfoSurrounding 详情周边
     * @return R
     */
    @SysLog("新增详情周边")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:detailsinfo:add')")
    public R save(@RequestBody DetailsInfoSurrounding detailsInfoSurrounding) {
        return R.ok(detailsInfoSurroundingService.save(detailsInfoSurrounding));
    }

    /**
     * 详情周边修改
     * @param detailsInfoSurrounding 详情周边
     * @return R
     */
    @SysLog("修改详情周边")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:detailsinfo:edit')")
    public R updateById(@RequestBody DetailsInfoSurrounding detailsInfoSurrounding) {
        return R.ok(detailsInfoSurroundingService.updateById(detailsInfoSurrounding));
    }

    /**
     * 详情周边删除
     * @param id
     * @return R
     */
    @SysLog("删除详情周边")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:detailsinfo:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(detailsInfoSurroundingService.removeById(id));
    }

	/**
	 * 详情周边修改
	 * @param detailsInfoSurrounding 详情周边
	 * @return R
	 */
	@SysLog("修改详情周边")
	@PutMapping("/batch")
	@PreAuthorize("@ato.hasAuthority('estate:detailsinfo:edit')")
	public R updateBatchById(@RequestBody List<DetailsInfoSurrounding> detailsInfoSurrounding) {
		return R.ok(detailsInfoSurroundingService.saveOrUpdateBatch(detailsInfoSurrounding));
	}

}
