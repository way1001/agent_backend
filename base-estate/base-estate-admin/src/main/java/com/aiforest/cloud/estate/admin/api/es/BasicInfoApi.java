/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.estate.common.entity.TopicReply;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.*;
import com.aiforest.cloud.estate.common.dto.GpsDTO;
import com.aiforest.cloud.estate.common.dto.TotalDTO;
import com.aiforest.cloud.estate.common.entity.BasicInfo;
import com.aiforest.cloud.estate.common.entity.NewsTrends;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/basicinfo")
@Api(value = "basicinfo", tags = "房产基础信息")
public class BasicInfoApi {

    private final BasicInfoService basicInfoService;

    private final TopicReplyService topicReplyService;
	private final SmsRecordsService smsRecordsService;
	private final UserInfoService userInfoService;

	public static final String BAIDU_LBS_TYPE = "bd09ll";

	private static double pi = 3.1415926535897932384626;

	/**
	 * 查询基础信息
	 *
	 * @return R
	 */
	@GetMapping("/get/{id}")
	public R getOne(@PathVariable("id") String id){
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		BasicInfo basicInfo = basicInfoService.getOne(Wrappers.<BasicInfo>lambdaQuery()
				.eq(BasicInfo::getId,id));
		if (basicInfo == null) {
			return R.failed("未配置基本信息");
		}
		GpsDTO gpsDTO = bd09_To_Gcj02(basicInfo.getLatitude().doubleValue(), basicInfo.getLongitude().doubleValue());
		basicInfo.setLatitude(BigDecimal.valueOf(gpsDTO.getWgLat()));
		basicInfo.setLongitude(BigDecimal.valueOf(gpsDTO.getWgLon()));
		return R.ok(basicInfo);
	}


	@PostMapping("/addviews")
	public R addViews(@RequestBody Map<String,String> param) {
		String id = param.get("id");
		BasicInfo basicInfo = basicInfoService.getOne(Wrappers.<BasicInfo>lambdaQuery()
				.eq(BasicInfo::getId,id));
		if (basicInfo == null) {
			return R.failed("未配置基础信息");
		}
		return R.ok(basicInfoService.addViews(basicInfo.getId()));
	}


//	@GetMapping("/total")
//	public R getTotal(){
//		TotalDTO totalDTO = new TotalDTO();
//		totalDTO.setTopicReplyCount(topicReplyService.count());
//		totalDTO.setSmsRecordsCount(smsRecordsService.count());
//		totalDTO.setUserCount(userInfoService.count());
//		BasicInfo basicInfo = basicInfoService.getOne(Wrappers.emptyWrapper());
//		if (basicInfo == null) {
//			totalDTO.setViewsCount(0);
//			return R.ok(totalDTO);
//		}
//		totalDTO.setViewsCount(basicInfo.getViews());
//		return R.ok(totalDTO);
//	}

	@GetMapping("/total/{id}")
	public R getTotal(@PathVariable("id") String id){
		TotalDTO totalDTO = new TotalDTO();
		totalDTO.setTopicReplyCount(topicReplyService.count(Wrappers.<TopicReply>lambdaQuery()
		.eq(TopicReply::getAffiliationId,id)));
//		totalDTO.setSmsRecordsCount(smsRecordsService.count());
		totalDTO.setUserCount(userInfoService.count());
		BasicInfo basicInfo = basicInfoService.getOne(Wrappers.<BasicInfo>lambdaQuery()
				.eq(BasicInfo::getId,id));
		if (basicInfo == null) {
			totalDTO.setViewsCount(0);
			return R.ok(totalDTO);
		}
		totalDTO.setViewsCount(basicInfo.getViews());
		return R.ok(totalDTO);
	}


	/**
	 * 火星坐标系 (GCJ-02) to 百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
	 *
	 * @param gg_lat
	 * @param gg_lon
	 */
	public static GpsDTO gcj02_To_Bd09(double gg_lat, double gg_lon) {
		double x = gg_lon, y = gg_lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
		double bd_lon = z * Math.cos(theta) + 0.0065;
		double bd_lat = z * Math.sin(theta) + 0.006;
		return new GpsDTO(bd_lat, bd_lon);
	}

	/**
	 * * 火星坐标系 (GCJ-02) to 百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param
	 * bd_lat * @param bd_lon * @return
	 */
	public static GpsDTO bd09_To_Gcj02(double bd_lat, double bd_lon) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
		double gg_lon = z * Math.cos(theta);
		double gg_lat = z * Math.sin(theta);
		return new GpsDTO(gg_lat, gg_lon);
	}

}
