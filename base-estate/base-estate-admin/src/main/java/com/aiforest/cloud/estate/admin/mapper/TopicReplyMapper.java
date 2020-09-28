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
import com.aiforest.cloud.estate.common.entity.TopicReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 房产总回复
 *
 * @author way
 * @date 2020-04-09 09:05:43
 */
public interface TopicReplyMapper extends BaseMapper<TopicReply> {
	IPage<TopicReply> selectPage1(IPage<TopicReply> page, @Param("query") TopicReply topicReply);

	List<TopicReply> selectList2(@Param("query") TopicReply queryWrapper);

	List<TopicReply> selectList3(@Param("query") TopicReply queryWrapper);

	TopicReply getOfficialReplyById(@Param("query") TopicReply topicReply);

	IPage<TopicReply> selectPageAS(IPage<TopicReply> page, @Param("query") TopicReply topicReply);

	IPage<TopicReply> selectPageRv(IPage<TopicReply> page, @Param("query") TopicReply topicReply);

	IPage<TopicReply> selectPageAL(IPage<TopicReply> page, @Param("query") TopicReply topicReply);

	IPage<TopicReply> selectPageAQ(IPage<TopicReply> page, @Param("query") TopicReply topicReply);



}
