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
import com.aiforest.cloud.estate.admin.service.ShareInfoService;
import com.aiforest.cloud.estate.admin.service.SmsRecordsService;
import com.aiforest.cloud.estate.common.dto.RecommendDTO;
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 云通讯短信
 *
 * @author way
 * @date 2020-05-09 15:52:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/yuntongxun")
@Api(value = "yuntongxun", tags = "云通讯短信")
public class YuntongxunSmsApi {

	private final SmsRecordsService smsRecordsService;


	/**
	 * 查询分享信息
	 *
	 * @return R
	 */
	@PutMapping("/custn")
	public R sendCustomNoticeSms(@RequestBody RecommendDTO recommendDTO){
		SmsRecords smsRecords = new SmsRecords();
		if (recommendDTO.getPhone().isEmpty() || recommendDTO.getPhone().equals("")){
			return R.failed();
		}
		smsRecords.setPhone(recommendDTO.getPhone());
		int count = smsRecordsService.count(Wrappers.query(smsRecords));
		if (count > 0) {
			return R.failed(Boolean.FALSE, "该号码已存在");
		}
		smsRecords.setSenderId(ThirdSessionHolder.getEstateUserId());
		return smsRecordsService.sendCustomNoticeSms(recommendDTO, smsRecords);
	}

}
