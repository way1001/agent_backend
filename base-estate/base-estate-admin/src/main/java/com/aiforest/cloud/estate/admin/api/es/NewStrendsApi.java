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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.DetailsInfoService;
import com.aiforest.cloud.estate.admin.service.DetailsInfoSurroundingService;
import com.aiforest.cloud.estate.admin.service.NewsTrendsService;
import com.aiforest.cloud.estate.common.entity.DetailsInfo;
import com.aiforest.cloud.estate.common.entity.NewsTrends;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/es/newstrends")
@Api(value = "newstrends", tags = "房产新闻动态")
public class NewStrendsApi {

	private final NewsTrendsService newsTrendsService;

	/**
	 * 查询基础信息
	 *
	 * @return R
	 */
	@GetMapping("/page")
	public R getPage(Page page, NewsTrends newsTrends) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(newsTrendsService.page(page, Wrappers.query(newsTrends)));
	}


	@PostMapping("/addviews")
	public R addViews(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		return R.ok(newsTrendsService.addViews(id));
	}

}
