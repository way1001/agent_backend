/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.service.impl;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CacheConstants;
import com.aiforest.cloud.weixin.common.entity.WxApp;
import com.aiforest.cloud.weixin.admin.mapper.WxAppMapper;
import com.aiforest.cloud.weixin.admin.service.WxAppService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 微信应用
 *
 * @author JL
 * @date 2019-03-15 10:26:44
 */
@Service
public class WxAppServiceImpl extends ServiceImpl<WxAppMapper, WxApp> implements WxAppService {

	/**
	 * 微信原始标识查找
	 * @param weixinSign
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstants.WXAPP_WEIXIN_SIGN_CACHE, key = "#weixinSign", unless = "#result == null")
	public WxApp findByWeixinSign(String weixinSign){
		WxApp wxApp = null;
		List<WxApp> listWxApp = baseMapper.findByWeixinSign(weixinSign);
		if(CollectionUtil.isNotEmpty(listWxApp)){
			wxApp = listWxApp.get(0);
		}
		return wxApp;
	}

	@Override
	@Cacheable(value = CacheConstants.WXAPP_APP_ID_CACHE, key = "#appId", unless = "#result == null")
	public WxApp findByAppId(String appId) {
		WxApp wxApp = null;
		List<WxApp> listWxApp = baseMapper.findByAppId(appId);
		if(CollectionUtil.isNotEmpty(listWxApp)){
			wxApp = listWxApp.get(0);
		}
		return wxApp;
	}

	@Override
	@Cacheable(value = CacheConstants.WXAPP_APP_ID_CACHE, key = "#id", unless = "#result == null")
	public WxApp getById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	@Caching(evict={
			@CacheEvict(value = CacheConstants.WXAPP_WEIXIN_SIGN_CACHE, allEntries = true),
			@CacheEvict(value = CacheConstants.WXAPP_APP_ID_CACHE, allEntries = true)
	})
	public boolean updateById(WxApp entity) {
		baseMapper.updateById(entity);
		return Boolean.TRUE;
	}

	@Override
	@Caching(evict={
			@CacheEvict(value = CacheConstants.WXAPP_WEIXIN_SIGN_CACHE, allEntries = true),
			@CacheEvict(value = CacheConstants.WXAPP_APP_ID_CACHE, allEntries = true)
	})
	public boolean removeById(Serializable id) {
		baseMapper.deleteById(id);
		return Boolean.TRUE;
	}
}
