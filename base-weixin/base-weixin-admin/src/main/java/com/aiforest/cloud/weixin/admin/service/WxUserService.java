/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.service;

import com.aiforest.cloud.weixin.common.dto.LoginMaDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.weixin.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.weixin.common.entity.WxApp;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信用户
 *
 * @author JL
 * @date 2019-03-25 15:39:39
 */
public interface WxUserService extends IService<WxUser> {

	/**
	 * 根据openId获取用户
	 * @param appId
	 * @param openId
	 * @return
	 */
	WxUser getByOpenId(String appId, String openId);

	/**
	 * 同步微信用户
	 * @param appId
	 */
	void synchroWxUser(String appId) throws WxErrorException;

	/**
	 * 修改用户备注
	 * @param entity
	 * @return
	 */
	boolean updateRemark(WxUser entity) throws WxErrorException;

	/**
	 * 认识标签
	 * @param taggingType
	 * @param appId
	 * @param tagId
	 * @param openIds
	 * @throws WxErrorException
	 */
	void tagging(String taggingType,String appId,Long tagId,String[] openIds) throws WxErrorException;

	/**
	 * 保存微信用户
	 * @param wxOpenDataDTO
	 */
	WxUser saveInside(WxOpenDataDTO wxOpenDataDTO);

	/**
	 * 保存微信用户
	 * @param wxOpenDataDTO
	 */
	WxUser saveInsideEs(WxOpenDataDTO wxOpenDataDTO);

	/**
	 * 小程序登录
	 * @param wxApp
	 * @param jsCode
	 * @return
	 */
	WxUser loginMa(WxApp wxApp, String jsCode) throws WxErrorException;

	/**
	 * 小程序登录
	 * @param wxApp
	 * @param loginMaDTO
	 * @return
	 */
	WxUser loginEs(WxApp wxApp, LoginMaDTO loginMaDTO) throws WxErrorException;
}
