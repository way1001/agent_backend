/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.BasicInfoService;
import com.aiforest.cloud.estate.admin.service.DetailsInfoService;
import com.aiforest.cloud.estate.admin.service.DetailsInfoSurroundingService;
import com.aiforest.cloud.estate.common.entity.DetailsInfo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/detailsinfo")
@Api(value = "detailsinfo", tags = "房产详情信息")
public class DetailsInfoApi {

    private final DetailsInfoService detailsInfoService;
	private final DetailsInfoSurroundingService detailsInfoSurroundingService;

	/**
	 * 查询基础信息
	 *
	 * @return R
	 */
//	@GetMapping
	@GetMapping("/{id}")
	public R getOne(@PathVariable("id") String id) {
//	public R getOne(){
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		DetailsInfo detailsInfo = detailsInfoService.getOne(Wrappers.<DetailsInfo>lambdaQuery()
				.eq(DetailsInfo::getAffiliationId,id));
		if(detailsInfo != null) {
			detailsInfo.setDetailsInfoSurroundingList(detailsInfoSurroundingService.listByDetailsId(detailsInfo.getId()));
			return R.ok(detailsInfo);
		}
		return R.ok();
	}

}
