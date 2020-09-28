/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin.service.impl;

import cn.hutool.json.JSONUtil;
import com.aiforest.cloud.broker.common.constant.WxBRConstants;
import com.aiforest.cloud.broker.common.entity.ThirdSession;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.yuntongxun.config.YuntongxunTemplateProperties;
import com.aiforest.cloud.common.yuntongxun.util.YuntongxunSmsUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.broker.admin.mapper.UserInfoMapper;
import com.aiforest.cloud.broker.admin.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 经纪人用户
 *
 * @author aiforest
 * @date 2020-08-31 08:43:29
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

	private final RedisTemplate redisTemplate;

	private final YuntongxunSmsUtils yuntongxunSmsUtils;
	private final YuntongxunTemplateProperties yuntongxunTemplateProperties;

	@Override
	public List<UserInfo> list1(String affiliationId, String tenantId){
		return baseMapper.selectList1(affiliationId, tenantId);
	}

	@Override
	public	String findIdByRoleCode(String code) {
		return baseMapper.selectIdByRole(code);
	}

	@Override
	public UserInfo getById1(String id, String tenantId) {
		return baseMapper.selectById1(id, tenantId);
	}


	@Override
	public R<Boolean> getSMSVerificationCode(String phone) {
		if (phone == null || phone.equals("")) {
			log.info("无效的号码:{}", phone);
			return R.failed(Boolean.FALSE, "无效的号码");
		}

		//TODO Do not handle multi-platform phone issues!!!
		UserInfo userInfo = baseMapper.selectOne(Wrappers.<UserInfo>lambdaQuery()
						.eq(UserInfo::getPhone, phone));
		if (userInfo != null) {
			String tmp = String.format("%04d",new Random().nextInt(9999));

			String[] sdata = {tmp, "5"};

			HashMap<String, Object> result = yuntongxunSmsUtils.sendYuntongxunSms(phone, yuntongxunTemplateProperties.getTemplateCode1(),
					sdata);

			if("000000".equals(result.get("statusCode"))){

				userInfo.setCaptcha(tmp);
				baseMapper.updateById(userInfo);

				return R.ok(Boolean.TRUE, "短信发送成功");


			}else{

				return R.failed(Boolean.FALSE, "错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			}
		}

		return R.failed(Boolean.FALSE, "未注册的号码");

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserInfo loginBr(String phone, String captcha){

		UserInfo userInfo = baseMapper.selectOne(Wrappers.<UserInfo>lambdaQuery()
				.eq(UserInfo::getPhone, phone)
		.eq(UserInfo::getCaptcha, captcha));

		if(userInfo != null) {

			userInfo.setCaptcha("");

			this.updateById(userInfo);

			String thirdSessionKey = UUID.randomUUID().toString();
			ThirdSession thirdSession = new ThirdSession();
			thirdSession.setUserId(userInfo.getId());
			thirdSession.setSessionKey(userInfo.getSessionKey());
			thirdSession.setUserCode(userInfo.getUserCode());
			//将3rd_session和用户信息存入redis，并设置过期时间
			String key = WxBRConstants.THIRD_SESSION_BEGIN + ":" + thirdSessionKey;
			redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(thirdSession) , WxBRConstants.TIME_OUT_SESSION, TimeUnit.HOURS);
			userInfo.setSessionKey(thirdSessionKey);
			userInfo.setCreation(new Date().getTime());

//			List<Comments>  comments = commentsService.list(Wrappers.<Comments>query()
//					.lambda().eq(Comments::getCreateId, wxUser.getId())
//					.between(Comments::getCommentType,"1", "2")
//					.select(Comments::getId, Comments::getLikes));
//			int sumlikes = 0;
//			if (comments.size() > 0) {
//				sumlikes = comments.stream().map(e -> e.getLikes()).reduce(Integer::sum).get();
//			}
//			wxUser.setReviews(comments.size());
//			wxUser.setLikes(sumlikes);

			return userInfo;

		}

		return null;
	}
}
