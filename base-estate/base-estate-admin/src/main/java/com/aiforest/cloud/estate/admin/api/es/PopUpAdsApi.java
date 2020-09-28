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
import com.aiforest.cloud.estate.admin.service.*;
import com.aiforest.cloud.estate.common.entity.PopUpAds;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 弹窗广告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/popupadsapi")
@Api(value = "popupadsapi", tags = "房产弹窗广告")
public class PopUpAdsApi {

	private final PopUpAdsService popUpAdsService;

	/**
	 * 查询弹窗广告
	 *
	 * @return R
	 */
	@GetMapping
	public R getOne(){
		PopUpAds popUpAds = popUpAdsService.getOne(Wrappers.emptyWrapper());
		if (popUpAds == null) {
			return R.failed("未配置弹窗广告");
		}
		return R.ok(popUpAds);
	}

}
