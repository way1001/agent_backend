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
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.entity.Reviews;
import com.aiforest.cloud.estate.admin.service.ReviewsService;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产点评
 *
 * @author way
 * @date 2020-04-09 09:05:34
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
@Api(value = "reviews", tags = "房产点评管理")
public class ReviewsController {

    private final ReviewsService reviewsService;
	private final UserInfoService userInfoService;

	/**
     * 分页列表
     * @param page 分页对象
     * @param reviews 房产点评
     * @return
     */
//    @GetMapping("/page")
//    @PreAuthorize("@ato.hasAuthority('estate_reviews_index')")
//    public R getPage(Page page, Reviews reviews) {
//        return R.ok(reviewsService.page(page, Wrappers.query(reviews)));
//    }
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('estate_reviews_index')")
	public R getPage(Page page, Reviews reviews, UserInfo userInfo) {
		if(userInfo.getUserCode() != null && userInfo.getUserCode()!= 0){
			int userCode = userInfo.getUserCode();
			userInfo = userInfoService.getOne(Wrappers.<UserInfo>query()
					.lambda().eq(UserInfo::getUserCode, userCode));
			if(userInfo != null){
				reviews.setCreateId(userInfo.getId());
			}else{
				reviews.setCreateId(String.valueOf(userCode));
			}
		}
		return R.ok(reviewsService.page2(page, reviews));
	}
    /**
     * 房产点评查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_reviews_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(reviewsService.getById(id));
    }

    /**
     * 房产点评新增
     * @param reviews 房产点评
     * @return R
     */
    @SysLog("新增房产点评")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_reviews_add')")
    public R save(@RequestBody Reviews reviews) {
        return R.ok(reviewsService.save(reviews));
    }

    /**
     * 房产点评修改
     * @param reviews 房产点评
     * @return R
     */
    @SysLog("修改房产点评")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_reviews_edit')")
    public R updateById(@RequestBody Reviews reviews) {
        return R.ok(reviewsService.updateById(reviews));
    }

    /**
     * 房产点评删除
     * @param id
     * @return R
     */
    @SysLog("删除房产点评")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_reviews_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(reviewsService.removeById(id));
    }

}
