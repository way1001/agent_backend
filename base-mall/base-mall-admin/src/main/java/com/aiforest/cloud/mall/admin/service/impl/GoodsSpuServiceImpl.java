/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.mall.admin.service.*;
import com.aiforest.cloud.mall.common.constant.MallConstants;
import com.aiforest.cloud.mall.common.entity.*;
import com.aiforest.cloud.mall.admin.mapper.GoodsSpuMapper;
import com.aiforest.cloud.upms.common.entity.SysUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spu商品
 *
 * @author JL
 * @date 2019-08-12 16:25:10
 */
@Service
@AllArgsConstructor
public class GoodsSpuServiceImpl extends ServiceImpl<GoodsSpuMapper, GoodsSpu> implements GoodsSpuService {

	private final GoodsSkuService goodsSkuService;
	private final GoodsSpuSpecService goodsSpuSpecService;
	private final GoodsSkuSpecValueService goodsSkuSpecValueService;
	private final UserCollectService userCollectService;
	private final EnsureGoodsService ensureGoodsService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean removeById(Serializable id) {
		super.removeById(id);
		//删除SpuSpec、SkuSpecValue、Sku、用户收藏
		goodsSpuSpecService.remove(Wrappers.<GoodsSpuSpec>update().lambda()
				.eq(GoodsSpuSpec::getSpuId, id));
		goodsSkuSpecValueService.remove(Wrappers.<GoodsSkuSpecValue>update().lambda()
				.eq(GoodsSkuSpecValue::getSpuId, id));
		goodsSkuService.remove(Wrappers.<GoodsSku>update().lambda()
				.eq(GoodsSku::getSpuId, id));
		userCollectService.remove(Wrappers.<UserCollect>update().lambda()
				.eq(UserCollect::getType, MallConstants.COLLECT_TYPE_1)
				.eq(UserCollect::getRelationId, id));
		return true;
	}

	@Override
	public IPage<GoodsSpu> page1(IPage<GoodsSpu> page, GoodsSpu goodsSpu) {
		return baseMapper.selectPage1(page, goodsSpu);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save1(GoodsSpu goodsSpu) {
		goodsSpu.setPriceDown(null);
		goodsSpu.setPriceUp(null);
		baseMapper.insert(goodsSpu);
		List<GoodsSku> listGoodsSku = goodsSpu.getSkus();
		if(listGoodsSku !=null && listGoodsSku.size()>0){
			//新增sku
			listGoodsSku.forEach(goodsSku -> {
				priceUpDown(goodsSpu,goodsSku);
				goodsSku.setSpuId(goodsSpu.getId());
				goodsSkuService.save(goodsSku);
				goodsSku.setId(goodsSku.getId());
			});
			baseMapper.updateById(goodsSpu);
			if(MallConstants.SPU_SPEC_TYPE_1.equals(goodsSpu.getSpecType())){//多规格处理
				goodsSpu.setId(goodsSpu.getId());
				addSpec(goodsSpu);
			}
		}
		if(goodsSpu.getEnsureIds() != null && goodsSpu.getEnsureIds().size() > 0){//新增保障服务
			List<EnsureGoods> listEnsureGoods = goodsSpu.getEnsureIds()
					.stream().map(ensureId -> {
						EnsureGoods ensureGoods = new EnsureGoods();
						ensureGoods.setEnsureId(ensureId);
						ensureGoods.setSpuId(goodsSpu.getId());
						return ensureGoods;
					}).collect(Collectors.toList());
			ensureGoodsService.saveBatch(listEnsureGoods);
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateById1(GoodsSpu goodsSpu) {
		goodsSpu.setPriceDown(null);
		goodsSpu.setPriceUp(null);
		List<GoodsSku> listGoodsSku = goodsSpu.getSkus();
		if(listGoodsSku !=null && listGoodsSku.size()>0){
			//先删除旧sku
			goodsSkuService.remove(Wrappers.<GoodsSku>update().lambda()
					.eq(GoodsSku::getSpuId, goodsSpu.getId()));
			//新增sku
			listGoodsSku.forEach(goodsSku -> {
				priceUpDown(goodsSpu,goodsSku);
				goodsSku.setSpuId(goodsSpu.getId());
				goodsSku.setTenantId(null);
				if(!goodsSkuService.saveOrUpdate(goodsSku)){//更新库存
					throw new RuntimeException("请重新提交");
				}
				goodsSku.setId(goodsSku.getId());
			});
			baseMapper.updateById(goodsSpu);
			//统一删除SpuSpec、SkuSpecValue
			goodsSpuSpecService.remove(Wrappers.<GoodsSpuSpec>update().lambda()
					.eq(GoodsSpuSpec::getSpuId, goodsSpu.getId()));
			goodsSkuSpecValueService.remove(Wrappers.<GoodsSkuSpecValue>update().lambda()
					.eq(GoodsSkuSpecValue::getSpuId, goodsSpu.getId()));
			if(MallConstants.SPU_SPEC_TYPE_1.equals(goodsSpu.getSpecType())) {//多规格处理
				addSpec(goodsSpu);
			}
			//修改保障服务
			ensureGoodsService.remove(Wrappers.<EnsureGoods>update().lambda()
					.eq(EnsureGoods::getSpuId, goodsSpu.getId()));
			if(goodsSpu.getEnsureIds() != null && goodsSpu.getEnsureIds().size() > 0){
				List<EnsureGoods> listEnsureGoods = new ArrayList<>();
				goodsSpu.getEnsureIds().forEach(ensureId -> {
					EnsureGoods ensureGoods = new EnsureGoods();
					ensureGoods.setEnsureId(ensureId);
					ensureGoods.setSpuId(goodsSpu.getId());
					listEnsureGoods.add(ensureGoods);
				});
				ensureGoodsService.saveBatch(listEnsureGoods);
			}
		}
		return true;
	}

	@Override
	public GoodsSpu getById1(String id) {
		return baseMapper.selectById1(id);
	}

	@Override
	public GoodsSpu getById2(String id) {
		return baseMapper.selectById2(id);
	}

	@Override
	public IPage<GoodsSpu> page2(IPage<GoodsSpu> page, GoodsSpu goodsSpu, CouponUser couponUser) {
		return baseMapper.selectPage2(page, goodsSpu, couponUser);
	}

	/**
	 * 多规格处理
	 * @param goodsSpu
	 */
	void addSpec(GoodsSpu goodsSpu){
		//新增SpuSpec
		List<GoodsSpuSpec> listGoodsSpuSpec = goodsSpu.getSpuSpec().stream().map(spuSpec->{
			GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
			goodsSpuSpec.setSpuId(goodsSpu.getId());
			goodsSpuSpec.setSpecId(spuSpec.getId());
			return goodsSpuSpec;
		}).collect(Collectors.toList());
		goodsSpuSpecService.saveBatch(listGoodsSpuSpec);
		//新增SkuSpecValue
		List<GoodsSkuSpecValue> listGoodsSkuSpecValue = new ArrayList<>();
		goodsSpu.getSkus().forEach(goodsSku -> {
			goodsSku.getSpecs().forEach(goodsSkuSpecValue -> {
				goodsSkuSpecValue.setSpuId(goodsSpu.getId());
				goodsSkuSpecValue.setSkuId(goodsSku.getId());
				listGoodsSkuSpecValue.add(goodsSkuSpecValue);
			});
		});
		goodsSkuSpecValueService.saveBatch(listGoodsSkuSpecValue);
	}

	/**
	 * 获取商品最高最低价
	 * @param goodsSpu
	 * @param goodsSku
	 */
	void priceUpDown(GoodsSpu goodsSpu,GoodsSku goodsSku){
		if(CommonConstants.YES.equals(goodsSku.getEnable())){
			BigDecimal priceDown = goodsSpu.getPriceDown();
			if(priceDown == null || priceDown.compareTo(goodsSku.getSalesPrice()) == 1){
				goodsSpu.setPriceDown(goodsSku.getSalesPrice());
			}
			BigDecimal priceUp = goodsSpu.getPriceUp();
			if(priceUp == null || priceUp.compareTo(goodsSku.getSalesPrice()) == -1){
				goodsSpu.setPriceUp(goodsSku.getSalesPrice());
			}
		}
	}
}
