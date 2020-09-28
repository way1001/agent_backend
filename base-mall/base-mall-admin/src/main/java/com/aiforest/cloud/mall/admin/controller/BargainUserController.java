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
import com.aiforest.cloud.mall.common.entity.BargainUser;
import com.aiforest.cloud.mall.admin.service.BargainUserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 砍价记录
 *
 * @author JL
 * @date 2019-12-30 11:53:14
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bargainuser")
@Api(value = "bargainuser", tags = "砍价记录管理")
public class BargainUserController {

    private final BargainUserService bargainUserService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param bargainUser 砍价记录
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:bargainuser:index')")
    public R getPage(Page page, BargainUser bargainUser) {
        return R.ok(bargainUserService.page1(page, bargainUser));
    }

    /**
     * 砍价记录查询
     * @param id
     * @return R
     */
	@ApiOperation(value = "砍价记录查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:bargainuser:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(bargainUserService.getById(id));
    }

    /**
     * 砍价记录新增
     * @param bargainUser 砍价记录
     * @return R
     */
	@ApiOperation(value = "砍价记录新增")
    @SysLog("新增砍价记录")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:bargainuser:add')")
    public R save(@RequestBody BargainUser bargainUser) {
        return R.ok(bargainUserService.save(bargainUser));
    }

    /**
     * 砍价记录修改
     * @param bargainUser 砍价记录
     * @return R
     */
	@ApiOperation(value = "砍价记录修改")
    @SysLog("修改砍价记录")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:bargainuser:edit')")
    public R updateById(@RequestBody BargainUser bargainUser) {
        return R.ok(bargainUserService.updateById(bargainUser));
    }

    /**
     * 砍价记录删除
     * @param id
     * @return R
     */
	@ApiOperation(value = "砍价记录删除")
    @SysLog("删除砍价记录")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:bargainuser:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(bargainUserService.removeById(id));
    }

}
