/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.estate.admin.service.LikesService;
import com.aiforest.cloud.estate.common.entity.Likes;
import com.aiforest.cloud.estate.common.entity.TopicReply;
import com.aiforest.cloud.estate.admin.mapper.TopicReplyMapper;
import com.aiforest.cloud.estate.admin.service.TopicReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 房产总回复
 *
 * @author way
 * @date 2020-04-09 09:05:43
 */
@Service
@Slf4j
@AllArgsConstructor
public class TopicReplyServiceImpl extends ServiceImpl<TopicReplyMapper, TopicReply> implements TopicReplyService {

	private final LikesService likesService;

	@Override
	public IPage<TopicReply> page1(IPage<TopicReply> page, TopicReply topicReply) {
		return baseMapper.selectPage1(page, topicReply);
	}

	@Override
	public IPage<TopicReply> page2(IPage<TopicReply> page, TopicReply topicReply) {
		switch (topicReply.getReplyTypes()) {
			case "1" :
				return baseMapper.selectPageAS(page, topicReply);
			case "2" :
				return baseMapper.selectPageRv(page, topicReply);
			case "3" :
				return baseMapper.selectPageAL(page, topicReply);
			case "4" :
				return baseMapper.selectPageAQ(page, topicReply);
			default:
				return null;
		}
	}


	@Override
	public boolean thumbUp(String id, String userId) {
		Likes likes = new Likes();
		likes.setLikesId(id);
		likes.setLikesType("5");
		likes.setUserId(userId);
		Likes likes2 = likesService.getOne(Wrappers.query(likes));
		if(likes2 == null){
			likes.setLikeStatus("0");
			return likesService.saveOrUpdate(likes);
		}else{
			likes2.setLikeStatus(likes2.getLikeStatus().equals("0") ? "1" : "0");
			return likesService.saveOrUpdate(likes2);
		}

	}
}
