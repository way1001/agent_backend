/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import org.apache.ibatis.annotations.Param;

/**
 * 短信记录
 *
 * @author way
 * @date 2020-05-09 17:21:38
 */
public interface SmsRecordsMapper extends BaseMapper<SmsRecords> {
	IPage<SmsRecords> selectPage1(IPage<SmsRecords> page, @Param("query") SmsRecords smsRecords);
}
