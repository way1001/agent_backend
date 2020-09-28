/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.estate.common.dto.AffiliationTree;
import com.aiforest.cloud.upms.common.util.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.estate.common.entity.BasicInfo;
import com.aiforest.cloud.estate.admin.mapper.BasicInfoMapper;
import com.aiforest.cloud.estate.admin.service.BasicInfoService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 房产基础信息
 *
 * @author way
 * @date 2020-04-06 08:39:12
 */
@Service
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements BasicInfoService {
	@Override
	public boolean addViews(String id) {return baseMapper.addViews(id);}

	@Override
	public List<AffiliationTree> selectTree() {
		return getTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 构建机构树
	 *
	 * @param entitys
	 * @return
	 */
	private List<AffiliationTree> getTree(List<BasicInfo> entitys) {
		List<AffiliationTree> treeList = entitys.stream()
				.filter(entity -> !entity.getId().equals(entity.getTenantId()))
				.sorted(Comparator.comparingInt(BasicInfo::getSort))
				.map(entity -> {
					AffiliationTree node = new AffiliationTree();
					BeanUtil.copyProperties(entity,node);
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, CommonConstants.PARENT_ID);
	}
}
