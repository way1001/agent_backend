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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.common.constant.EstateConstants;
import com.aiforest.cloud.estate.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import com.aiforest.cloud.estate.admin.mapper.UserInfoMapper;
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.feign.FeignWxUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 房产用户
 *
 * @author way
 * @date 2020-04-03 15:43:34
 */
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
	private final FeignWxUserService feignWxUserService;
//	private final PointsConfigService pointsConfigService;
//	private final PointsRecordService pointsRecordService;

	@Override
	public IPage<UserInfo> page1(IPage<UserInfo> page, UserInfo userInfo) {
		return baseMapper.selectPage1(page, userInfo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public R saveByWxUser(WxOpenDataDTO wxOpenDataDTO) {
		//修改微信用户信息
		R r = feignWxUserService.save(wxOpenDataDTO, SecurityConstants.FROM_IN);
		if(!r.isOk()){
			throw new RuntimeException(r.getMsg());
		}
		//修改商城用户信息
		Map map = (Map<String, Object>) r.getData();
		if(map.get("estateUserId") != null){
			UserInfo userInfo = baseMapper.selectById(String.valueOf(map.get("estateUserId")));
			userInfo.setNickName(String.valueOf(map.get("nickName")));
			userInfo.setHeadimgUrl(String.valueOf(map.get("headimgUrl")));
			userInfo.setSex(String.valueOf(map.get("sex")));
			userInfo.setCity(String.valueOf(map.get("city")));
			userInfo.setCountry(String.valueOf(map.get("country")));
			userInfo.setProvince(String.valueOf(map.get("province")));
			if(EstateConstants.USER_GRADE_0.equals(userInfo.getUserGrade())){
				userInfo.setUserGrade(EstateConstants.USER_GRADE_1);//1：普通会员
//				//获取会员初始积分
//				PointsConfig pointsConfig = pointsConfigService.getOne(Wrappers.emptyWrapper());
//				int initVipPosts = pointsConfig != null ? pointsConfig.getInitVipPosts() : 0;
//				userInfo.setPointsCurrent(userInfo.getPointsCurrent() + initVipPosts);
//				if(initVipPosts > 0){
//					//新增积分变动记录
//					PointsRecord pointsRecord = new PointsRecord();
//					pointsRecord.setUserId(userInfo.getId());
//					pointsRecord.setAmount(initVipPosts);
//					pointsRecord.setRecordType(MallConstants.POINTS_RECORD_TYPE_1);
//					pointsRecord.setDescription("会员初始积分");
//					pointsRecordService.save(pointsRecord);
//				}
			}
			baseMapper.updateById(userInfo);
		}
		return r;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveInside(UserInfo userInfo) {
		//获取用户初始积分
//		PointsConfig pointsConfig = pointsConfigService.getOne(Wrappers.emptyWrapper());
//		int initPosts = pointsConfig != null ? pointsConfig.getInitPosts() : 0;
		//新增商城用户
		userInfo.setUserGrade(EstateConstants.USER_GRADE_0);
//		userInfo.setPointsCurrent(initPosts);
		baseMapper.insert(userInfo);
//		if(initPosts > 0){
//			//新增积分变动记录
//			PointsRecord pointsRecord = new PointsRecord();
//			pointsRecord.setUserId(userInfo.getId());
//			pointsRecord.setAmount(initPosts);
//			pointsRecord.setRecordType(MallConstants.POINTS_RECORD_TYPE_0);
//			pointsRecord.setDescription("用户初始积分");
//			pointsRecordService.save(pointsRecord);
//		}
		return Boolean.TRUE;
	}

	@Override
	public UserInfo getRandSalesman() {
		return baseMapper.getRandSalesman();
	}
}
