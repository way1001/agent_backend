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
import com.aiforest.cloud.estate.admin.service.AdvertisementService;
import com.aiforest.cloud.estate.common.entity.Advertisement;
import com.aiforest.cloud.estate.common.entity.AdvertisementItem;
import com.aiforest.cloud.estate.admin.service.AdvertisementItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产布告详情
 *
 * @author way
 * @date 2020-04-01 17:21:07
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/advertisementitem")
@Api(value = "advertisementitem", tags = "房产布告详情管理")
public class AdvertisementItemController {

    private final AdvertisementItemService advertisementItemService;

	private final AdvertisementService advertisementService;


	/**
     * 分页列表
     * @param page 分页对象
     * @param advertisementItem 房产布告详情
     * @return
     */
//    @GetMapping("/page")
//    @PreAuthorize("@ato.hasAuthority('estate_advertisementitem_index')")
//    public R getPage(Page page, AdvertisementItem advertisementItem) {
//        return R.ok(advertisementItemService.page(page, Wrappers.query(advertisementItem)));
//    }
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('estate_advertisementitem_index')")
	public R getNoticeItemPage(Page page, AdvertisementItem advertisementItem) {
		Advertisement advertisement = new Advertisement();
//		advertisement.setAffiliationId(advertisementItem.getAffiliationId());
		advertisement.setType(advertisementItem.getAdvertisementType());
		advertisement = advertisementService.getOne(Wrappers.query(advertisement));
		if(advertisement == null){
			return R.ok(page);
		}
		advertisementItem.setAdvertisementId(advertisement.getId());
		return R.ok(advertisementItemService.page(page,Wrappers.query(advertisementItem)));
	}

    /**
     * 房产布告详情查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_advertisementitem_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(advertisementItemService.getById(id));
    }

    /**
     * 房产布告详情新增
     * @param advertisementItem 房产布告详情
     * @return R
     */
    @SysLog("新增房产布告详情")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_advertisementitem_add')")
    public R save(@RequestBody AdvertisementItem advertisementItem) {
        return R.ok(advertisementItemService.save(advertisementItem));
    }

    /**
     * 房产布告详情修改
     * @param advertisementItem 房产布告详情
     * @return R
     */
    @SysLog("修改房产布告详情")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_advertisementitem_edit')")
    public R updateById(@RequestBody AdvertisementItem advertisementItem) {
        return R.ok(advertisementItemService.updateById(advertisementItem));
    }

    /**
     * 房产布告详情删除
     * @param id
     * @return R
     */
    @SysLog("删除房产布告详情")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_advertisementitem_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(advertisementItemService.removeById(id));
    }

}
