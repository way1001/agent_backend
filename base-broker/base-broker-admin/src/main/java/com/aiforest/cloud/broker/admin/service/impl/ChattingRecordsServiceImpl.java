/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.broker.common.entity.ChattingRecords;
import com.aiforest.cloud.broker.admin.mapper.ChattingRecordsMapper;
import com.aiforest.cloud.broker.admin.service.ChattingRecordsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 聊天记录
 *
 * @author aiforest
 * @date 2020-10-07 10:46:02
 */
@Service
public class ChattingRecordsServiceImpl extends ServiceImpl<ChattingRecordsMapper, ChattingRecords> implements ChattingRecordsService {


	@Override
	public void updateRe(List<ChattingRecords> chattingRecordsList) {
		baseMapper.updateRe(chattingRecordsList);
	}

	@Override
	public IPage<ChattingRecords> page1(IPage<ChattingRecords> page, ChattingRecords chattingRecords) {
		return baseMapper.selectPage1(page, chattingRecords);
	}
}
