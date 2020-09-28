/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产用户
 *
 * @author way
 * @date 2020-04-03 15:43:34
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/userinfo")
@Api(value = "userinfo", tags = "房产用户管理")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param userInfo 房产用户
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_userinfo_index')")
    public R getPage(Page page, UserInfo userInfo) {
//    	if (userInfo.getUserType().equals("1")) {
//			QueryWrapper qw = new QueryWrapper<>();
//			qw.ge("user_type", 1);
//			return R.ok(userInfoService.page1(page, qw));
//		}
//        return R.ok(userInfoService.page1(page, Wrappers.query(userInfo)));
		return R.ok(userInfoService.page1(page, userInfo));
    }

    /**
     * 房产用户查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_userinfo_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(userInfoService.getById(id));
    }

    /**
     * 房产用户新增
     * @param userInfo 房产用户
     * @return R
     */
    @SysLog("新增房产用户")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_userinfo_add')")
    public R save(@RequestBody UserInfo userInfo) {
        return R.ok(userInfoService.save(userInfo));
    }

    /**
     * 房产用户修改
     * @param userInfo 房产用户
     * @return R
     */
    @SysLog("修改房产用户")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_userinfo_edit')")
    public R updateById(@RequestBody UserInfo userInfo) {
        return R.ok(userInfoService.updateById(userInfo));
    }

    /**
     * 房产用户删除
     * @param id
     * @return R
     */
    @SysLog("删除房产用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_userinfo_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(userInfoService.removeById(id));
    }
	/**
	 * 新增商城用户（供服务间调用）
	 * @param userInfo 商城用户
	 * @return R
	 */
	@Inside
	@PostMapping("/inside")
	public R saveInside(@RequestBody UserInfo userInfo) {
		userInfoService.saveInside(userInfo);
		return R.ok(userInfo);
	}

	@Inside
	@GetMapping("/inside/{id}")
	public R getByIdInside(@PathVariable("id") String id) {
		return R.ok(userInfoService.getById(id));
	}

}
