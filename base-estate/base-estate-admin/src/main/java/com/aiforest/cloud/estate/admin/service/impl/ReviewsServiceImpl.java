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
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.estate.admin.service.LikesService;
import com.aiforest.cloud.estate.common.dto.AuditDTO;
import com.aiforest.cloud.estate.common.entity.Likes;
import com.aiforest.cloud.estate.common.entity.Reviews;
import com.aiforest.cloud.estate.admin.mapper.ReviewsMapper;
import com.aiforest.cloud.estate.admin.service.ReviewsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 房产点评
 *
 * @author way
 * @date 2020-04-09 09:05:34
 */
@Slf4j
@Service
@AllArgsConstructor
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper, Reviews> implements ReviewsService {

	private final LikesService likesService;

	@Override
	public boolean addViews(String id) {return baseMapper.addViews(id);}

	@Override
	public IPage<Reviews> page1(IPage<Reviews> page, AuditDTO auditDTO) {
		return baseMapper.selectPage1(page, auditDTO);
	}

	@Override
	public boolean thumbUp(String id, String userId) {
		Likes likes = new Likes();
		likes.setLikesId(id);
		likes.setLikesType("2");
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

	@Override
	public IPage<Reviews> page2(IPage<Reviews> page, Reviews reviews) {
		return baseMapper.selectPage2(page,reviews);
	}

}
