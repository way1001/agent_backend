/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.admin.config.mp.WxMpConfiguration;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpDataCubeService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;

/**
 * 微信账号配置
 *
 * @author JL
 * @date 2019-03-23 21:26:35
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxsummary")
@Api(value = "wxsummary", tags = "微信账号配置管理")
public class WxSummaryController {

	/**
	 * 获取用户增减数据
	 * @param appId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ApiOperation(value = "获取用户增减数据")
	@GetMapping("/usersummary")
//	@PreAuthorize("@ato.hasAuthority('wxmp:wxsummary:index')")
	public R getUsersummary(String appId, String startDate, String endDate) {
		try {
			WxMpService wxMpService = WxMpConfiguration.getMpService(appId);
			WxMpDataCubeService wxMpDataCubeService = wxMpService.getDataCubeService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return R.ok(wxMpDataCubeService.getUserSummary(sdf.parse(startDate), sdf.parse(endDate)));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取用户增减数据失败",e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("获取用户增减数据失败",e);
			return R.failed("获取用户增减数据失败");
		}
	}

	/**
	 * 获取累计用户数据
	 * @param appId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ApiOperation(value = "获取累计用户数据")
	@GetMapping("/usercumulate")
//	@PreAuthorize("@ato.hasAuthority('wxmp:wxsummary:index')")
	public R getUserCumulate(String appId, String startDate, String endDate){
		try {
			WxMpService wxMpService = WxMpConfiguration.getMpService(appId);
			WxMpDataCubeService wxMpDataCubeService = wxMpService.getDataCubeService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return R.ok(wxMpDataCubeService.getUserCumulate(sdf.parse(startDate), sdf.parse(endDate)));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取累计用户数据失败",e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("获取用户增减数据失败",e);
			return R.failed("获取用户增减数据失败");
		}
	}

	/**
	 * 获取接口分析数据
	 * @param appId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ApiOperation(value = "获取接口分析数据")
	@GetMapping("/interfacesummary")
//	@PreAuthorize("@ato.hasAuthority('wxmp:wxsummary:index')")
	public R getInterfaceSummary(String appId, String startDate, String endDate){
		try {
			WxMpService wxMpService = WxMpConfiguration.getMpService(appId);
			WxMpDataCubeService wxMpDataCubeService = wxMpService.getDataCubeService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return R.ok(wxMpDataCubeService.getInterfaceSummary(sdf.parse(startDate), sdf.parse(endDate)));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取接口分析数据失败",e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("获取接口分析数据失败",e);
			return R.failed("获取接口分析数据失败");
		}
	}
}
