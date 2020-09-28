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
import com.aiforest.cloud.mall.admin.service.GrouponUserService;
import com.aiforest.cloud.mall.common.constant.MyReturnCode;
import com.aiforest.cloud.mall.common.entity.GrouponInfo;
import com.aiforest.cloud.mall.admin.service.GrouponInfoService;
import com.aiforest.cloud.mall.common.entity.GrouponUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 拼团
 *
 * @author JL
 * @date 2020-03-17 11:55:32
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/grouponinfo")
@Api(value = "grouponinfo", tags = "拼团管理")
public class GrouponInfoController {

    private final GrouponInfoService grouponInfoService;
	private final GrouponUserService grouponUserService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param grouponInfo 拼团
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:grouponinfo:index')")
    public R getPage(Page page, GrouponInfo grouponInfo) {
        return R.ok(grouponInfoService.page(page, Wrappers.query(grouponInfo)));
    }

	/**
	 * list列表
	 * @param grouponInfo 拼团
	 * @return
	 */
	@ApiOperation(value = "list列表")
	@GetMapping("/list")
	public R getList(GrouponInfo grouponInfo) {
		return R.ok(grouponInfoService.list(Wrappers.query(grouponInfo)));
	}


	/**
     * 拼团查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "拼团查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:grouponinfo:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(grouponInfoService.getById(id));
    }

    /**
     * 拼团新增
     * @param grouponInfo 拼团
     * @return R
     */
    @ApiOperation(value = "拼团新增")
    @SysLog("新增拼团")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:grouponinfo:add')")
    public R save(@RequestBody GrouponInfo grouponInfo) {
        return R.ok(grouponInfoService.save(grouponInfo));
    }

    /**
     * 拼团修改
     * @param grouponInfo 拼团
     * @return R
     */
    @ApiOperation(value = "拼团修改")
    @SysLog("修改拼团")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:grouponinfo:edit')")
    public R updateById(@RequestBody GrouponInfo grouponInfo) {
        return R.ok(grouponInfoService.updateById(grouponInfo));
    }

    /**
     * 拼团删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "拼团删除")
    @SysLog("删除拼团")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:grouponinfo:del')")
    public R removeById(@PathVariable String id) {
		int count = grouponUserService.count(Wrappers.<GrouponUser>lambdaQuery()
				.eq(GrouponUser::getGrouponId,id));
		if(count > 0){
			return R.failed(MyReturnCode.ERR_80020.getMsg());
		}
        return R.ok(grouponInfoService.removeById(id));
    }

}
