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
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.yuntongxun.config.YuntongxunTemplateProperties;
import com.aiforest.cloud.common.yuntongxun.util.YuntongxunSmsUtils;
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.dto.RecommendDTO;
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import com.aiforest.cloud.estate.admin.mapper.SmsRecordsMapper;
import com.aiforest.cloud.estate.admin.service.SmsRecordsService;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 短信记录
 *
 * @author way
 * @date 2020-05-09 17:21:38
 */
@Service
@Slf4j
@AllArgsConstructor
public class SmsRecordsServiceImpl extends ServiceImpl<SmsRecordsMapper, SmsRecords> implements SmsRecordsService {

	private final UserInfoService userInfoService;
	private final YuntongxunSmsUtils yuntongxunSmsUtils;
	private final YuntongxunTemplateProperties yuntongxunTemplateProperties;

	@Override
	public IPage<SmsRecords> page1(IPage<SmsRecords> page, SmsRecords smsRecords) {
		return baseMapper.selectPage1(page, smsRecords);
	}

	@Override
	public R<Boolean> sendCustomNoticeSms(RecommendDTO recommendDTO, SmsRecords smsRecords) {
		UserInfo userInfo = userInfoService.getById(smsRecords.getSenderId());

		if (userInfo == null) {
			log.info("未知的用户:{}", smsRecords.getSenderId());
			return R.failed(Boolean.FALSE, "未知的用户");
		}

		UserInfo receiverUser;
		if (userInfo.getInvitee() == null || userInfo.getInvitee().equals("")) {
			receiverUser = userInfoService.getRandSalesman();

			if (receiverUser == null) {
				log.info("未知的管理用户:{}");
				return R.failed(Boolean.FALSE, "未知的管理用户");
			}

			userInfo.setInvitee(receiverUser.getId());
			userInfoService.updateById(userInfo);
		} else {
			receiverUser = userInfoService.getById(userInfo.getInvitee());
			if (receiverUser == null || !receiverUser.getUserType().equals("1")) {
				receiverUser = userInfoService.getRandSalesman();

				if (receiverUser == null) {
					log.info("未知的管理用户:{}");
					return R.failed(Boolean.FALSE, "未知的管理用户");
				}

				userInfo.setInvitee(receiverUser.getId());
				userInfoService.updateById(userInfo);
			}
		}

		smsRecords.setReceiverId(receiverUser.getId());

        String[] sdata = {recommendDTO.getEstateName(), recommendDTO.getName(), recommendDTO.getGender(), recommendDTO.getPhone()};

		smsRecords.setDetails(StringUtils.join(sdata,","));

		smsRecords.setType("1"); //添加类型

		HashMap<String, Object> result = yuntongxunSmsUtils.sendYuntongxunSms(receiverUser.getPhone(), yuntongxunTemplateProperties.getTemplateCode1(),
				sdata);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				System.out.println(key +" = "+object);
//			}

			smsRecords.setStatus("0");

			baseMapper.insert(smsRecords);

			return R.ok(Boolean.TRUE, "短信发送成功");


		}else{
			smsRecords.setStatus("1");

			baseMapper.insert(smsRecords);
			//异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return R.failed(Boolean.FALSE, "错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}

	}



}
