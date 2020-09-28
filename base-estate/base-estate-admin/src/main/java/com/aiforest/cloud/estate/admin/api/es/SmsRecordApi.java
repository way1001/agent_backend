/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.SmsRecordsService;
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信记录
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/smsrecord")
@Api(value = "smsrecord", tags = "房产短信记录")
public class SmsRecordApi {

	private final SmsRecordsService smsRecordsService;

	/**
	 * 分页列表
	 * @param page 分页对象
	 * @param smsRecords 短信记录
	 * @return
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page")
	public R getPage(Page page, SmsRecords smsRecords) {
		return R.ok(smsRecordsService.page1(page, smsRecords));
	}

}
