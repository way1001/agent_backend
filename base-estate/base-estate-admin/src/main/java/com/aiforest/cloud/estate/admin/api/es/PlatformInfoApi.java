/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.*;
import com.aiforest.cloud.estate.common.dto.GpsDTO;
import com.aiforest.cloud.estate.common.dto.TotalDTO;
import com.aiforest.cloud.estate.common.entity.BasicInfo;
import com.aiforest.cloud.estate.common.entity.PlatformInfo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.aiforest.cloud.estate.admin.api.es.BasicInfoApi.bd09_To_Gcj02;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/platforminfo")
@Api(value = "platforminfo", tags = "平台基础信息")
public class PlatformInfoApi {

	private final PlatformInfoService platformInfoService;

    private final BasicInfoService basicInfoService;

	/**
	 * 查询基础信息
	 *
	 * @return R
	 */

	@GetMapping
	public R getOne(){

		PlatformInfo platformInfo = platformInfoService.getOne(Wrappers.emptyWrapper());
		if (platformInfo == null) {
			return R.failed("未配置平台基本信息");
		}

		List<BasicInfo> basicInfoList = basicInfoService.list()
				.stream().peek(basicInfo -> {
					GpsDTO gpsDTO = bd09_To_Gcj02(basicInfo.getLatitude().doubleValue(), basicInfo.getLongitude().doubleValue());
					basicInfo.setLatitude(BigDecimal.valueOf(gpsDTO.getWgLat()));
					basicInfo.setLongitude(BigDecimal.valueOf(gpsDTO.getWgLon()));
				}).collect(Collectors.toList());

		platformInfo.setBasicInfoList(basicInfoList);

		return R.ok(platformInfo);
	}


}
