/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.mall.admin.service.UserInfoService;
import com.aiforest.cloud.mall.common.entity.PointsRecord;
import com.aiforest.cloud.mall.admin.service.PointsRecordService;
import com.aiforest.cloud.mall.common.entity.UserInfo;
import com.aiforest.cloud.upms.common.entity.SysUser;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 积分变动记录
 *
 * @author JL
 * @date 2019-12-05 19:47:22
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pointsrecord")
@Api(value = "pointsrecord", tags = "积分变动记录管理")
public class PointsRecordController {

    private final PointsRecordService pointsRecordService;
	private final UserInfoService userInfoService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param pointsRecord 积分变动记录
	 * @return
	 */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:pointsrecord:index')")
    public R getPointsRecordPage(Page page, PointsRecord pointsRecord, UserInfo userInfo) {
    	if(userInfo.getUserCode() != null && userInfo.getUserCode()!= 0){
    		int userCode = userInfo.getUserCode();
			userInfo = userInfoService.getOne(Wrappers.<UserInfo>query()
					.lambda().eq(UserInfo::getUserCode, userCode));
			if(userInfo != null){
				pointsRecord.setUserId(userInfo.getId());
			}else{
				pointsRecord.setUserId(String.valueOf(userCode));
			}
		}
        return R.ok(pointsRecordService.page1(page, pointsRecord));
    }

    /**
     * 通过id查询积分变动记录
     * @param id
     * @return R
     */
	@ApiOperation(value = "通过id查询积分变动记录")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:pointsrecord:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(pointsRecordService.getById(id));
    }

    /**
     * 新增积分变动记录
     * @param pointsRecord 积分变动记录
     * @return R
     */
	@ApiOperation(value = "新增积分变动记录")
    @SysLog("新增积分变动记录")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:pointsrecord:add')")
    public R save(@RequestBody PointsRecord pointsRecord) {
        return R.ok(pointsRecordService.save(pointsRecord));
    }

    /**
     * 修改积分变动记录
     * @param pointsRecord 积分变动记录
     * @return R
     */
	@ApiOperation(value = "修改积分变动记录")
    @SysLog("修改积分变动记录")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:pointsrecord:edit')")
    public R updateById(@RequestBody PointsRecord pointsRecord) {
        return R.ok(pointsRecordService.updateById(pointsRecord));
    }

    /**
     * 通过id删除积分变动记录
     * @param id
     * @return R
     */
	@ApiOperation(value = "通过id删除积分变动记录")
    @SysLog("删除积分变动记录")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:pointsrecord:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(pointsRecordService.removeById(id));
    }

}
