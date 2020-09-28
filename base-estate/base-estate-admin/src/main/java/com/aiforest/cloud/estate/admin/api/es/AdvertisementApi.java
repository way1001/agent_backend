/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.estate.common.dto.AdvertisementDTO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.AdvertisementService;
import com.aiforest.cloud.estate.common.entity.Advertisement;
import com.aiforest.cloud.weixin.common.util.WxMaUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/es/advertisement")
@Api(value = "advertisement", tags = "房产布告API")
public class AdvertisementApi {

    private final AdvertisementService advertisementService;

	/**
	 * 查询房产布告
	 * @param advertisementDTO
	 * @return R
	 */
	@GetMapping
	public R getOne(HttpServletRequest request, AdvertisementDTO advertisementDTO){
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		advertisement.setAppId(BaseApi.getAppId(request));
//		advertisement.setAppId(WxMaUtil.getAppId(request));

		Advertisement advertisement = advertisementService.getOne1(advertisementDTO);
		return R.ok(advertisement);
	}

}
