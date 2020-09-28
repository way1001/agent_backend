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
import com.aiforest.cloud.estate.common.entity.AwesomeShooting;
import com.aiforest.cloud.estate.admin.service.AwesomeShootingService;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产炫拍
 *
 * @author way
 * @date 2020-04-09 09:05:26
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/awesomeshooting")
@Api(value = "awesomeshooting", tags = "房产炫拍管理")
public class AwesomeShootingController {

    private final AwesomeShootingService awesomeShootingService;
	private final UserInfoService userInfoService;

	/**
     * 分页列表
     * @param page 分页对象
     * @param awesomeShooting 房产炫拍
     * @return
     */
//    @GetMapping("/page")
//    @PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_index')")
//    public R getPage(Page page, AwesomeShooting awesomeShooting) {
//        return R.ok(awesomeShootingService.page(page, Wrappers.query(awesomeShooting)));
//    }
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_index')")
	public R getPage(Page page, AwesomeShooting awesomeShooting, UserInfo userInfo) {
		if(userInfo.getUserCode() != null && userInfo.getUserCode()!= 0){
			int userCode = userInfo.getUserCode();
			userInfo = userInfoService.getOne(Wrappers.<UserInfo>query()
					.lambda().eq(UserInfo::getUserCode, userCode));
			if(userInfo != null){
				awesomeShooting.setCreateId(userInfo.getId());
			}else{
				awesomeShooting.setCreateId(String.valueOf(userCode));
			}
		}
		return R.ok(awesomeShootingService.page2(page, awesomeShooting));
	}

    /**
     * 房产炫拍查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(awesomeShootingService.getById(id));
    }

    /**
     * 房产炫拍新增
     * @param awesomeShooting 房产炫拍
     * @return R
     */
    @SysLog("新增房产炫拍")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_add')")
    public R save(@RequestBody AwesomeShooting awesomeShooting) {
        return R.ok(awesomeShootingService.save(awesomeShooting));
    }

    /**
     * 房产炫拍修改
     * @param awesomeShooting 房产炫拍
     * @return R
     */
    @SysLog("修改房产炫拍")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_edit')")
    public R updateById(@RequestBody AwesomeShooting awesomeShooting) {
        return R.ok(awesomeShootingService.updateById(awesomeShooting));
    }

    /**
     * 房产炫拍删除
     * @param id
     * @return R
     */
    @SysLog("删除房产炫拍")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_awesomeshooting_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(awesomeShootingService.removeById(id));
    }

}
