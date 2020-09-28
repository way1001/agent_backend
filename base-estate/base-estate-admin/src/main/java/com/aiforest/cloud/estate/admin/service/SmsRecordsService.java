/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.common.dto.RecommendDTO;
import com.aiforest.cloud.estate.common.entity.SmsRecords;

/**
 * 短信记录
 *
 * @author way
 * @date 2020-05-09 17:21:38
 */
public interface SmsRecordsService extends IService<SmsRecords> {

	R<Boolean> sendCustomNoticeSms(RecommendDTO recommendDTO, SmsRecords smsRecords);

	IPage<SmsRecords> page1(IPage<SmsRecords> page, SmsRecords smsRecords);

}
