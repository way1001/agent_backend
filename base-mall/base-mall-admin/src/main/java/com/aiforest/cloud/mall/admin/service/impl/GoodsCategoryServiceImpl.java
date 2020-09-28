/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.mall.common.entity.GoodsCategory;
import com.aiforest.cloud.mall.common.entity.GoodsCategoryTree;
import com.aiforest.cloud.mall.admin.mapper.GoodsCategoryMapper;
import com.aiforest.cloud.mall.admin.service.GoodsCategoryService;
import com.aiforest.cloud.upms.common.util.TreeUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类目
 *
 * @author JL
 * @date 2019-08-12 11:46:28
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

	@Override
	public List<GoodsCategoryTree> selectTree(GoodsCategory goodsCategory) {
		return getTree(this.list(Wrappers.lambdaQuery(goodsCategory)));
	}

	/**
	 * 构建树
	 *
	 * @param entitys
	 * @return
	 */
	private List<GoodsCategoryTree> getTree(List<GoodsCategory> entitys) {
		List<GoodsCategoryTree> treeList = entitys.stream()
				.filter(entity -> !entity.getId().equals(entity.getParentId()))
				.sorted(Comparator.comparingInt(GoodsCategory::getSort))
				.map(entity -> {
					GoodsCategoryTree node = new GoodsCategoryTree();
					BeanUtil.copyProperties(entity,node);
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, CommonConstants.PARENT_ID);
	}

	@Override
	public boolean removeById(Serializable id) {
		super.removeById(id);
		remove(Wrappers.<GoodsCategory>query()
				.lambda().eq(GoodsCategory::getParentId, id));
		return true;
	}
}
