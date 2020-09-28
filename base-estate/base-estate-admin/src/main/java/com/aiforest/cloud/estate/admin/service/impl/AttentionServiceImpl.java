/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.estate.common.dto.DepreciateDTO;
import com.aiforest.cloud.estate.common.dto.OpeningQuotationDTO;
import com.aiforest.cloud.estate.common.entity.Attention;
import com.aiforest.cloud.estate.admin.mapper.AttentionMapper;
import com.aiforest.cloud.estate.admin.service.AttentionService;
import com.aiforest.cloud.estate.common.feign.FeignWxTemplateMsgService;
import com.aiforest.cloud.weixin.common.constant.ConfigConstant;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendEsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 房产关注
 *
 * @author way
 * @date 2020-04-21 10:02:09
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {

	private final FeignWxTemplateMsgService feignWxTemplateMsgService;

	@Override
	public List<Attention> list1(Attention attention){
		return baseMapper.selectList1(attention);
	}

	@Override
	public List<Attention> list2(Attention attention){
		return baseMapper.selectList2(attention);
	}

	@Override
	public IPage<Attention> page1(IPage<Attention> page, Attention attention) {
		return baseMapper.selectPage1(page, attention);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateByOP(OpeningQuotationDTO openingQuotationDTO) {
		if(StrUtil.isBlank(openingQuotationDTO.getAttention().getAttentionId()) && openingQuotationDTO.getAttention().getAttentionType().equals("5")) {
			//发送订阅消息
			try {
				Attention attention = baseMapper.selectById(openingQuotationDTO.getAttention().getId());
				WxTemplateMsgSendEsDTO wxTemplateMsgSendEsDTO = new WxTemplateMsgSendEsDTO();
				wxTemplateMsgSendEsDTO.setEstateUserId(attention.getUserId());
				wxTemplateMsgSendEsDTO.setUseType(ConfigConstant.WX_TMP_USE_TYPE_5);
				wxTemplateMsgSendEsDTO.setPage("pages/index/index");
				HashMap<String, HashMap<String, String>> data = new HashMap<>();
				data.put("thing1", (HashMap)JSONUtil.toBean("{\"value\": \""+openingQuotationDTO.getThing1()+"\"}", Map.class));
				data.put("thing3", (HashMap)JSONUtil.toBean("{\"value\": \""+openingQuotationDTO.getThing3()+"\"}",Map.class));
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
				data.put("time2", (HashMap)JSONUtil.toBean("{\"value\": \""+openingQuotationDTO.getTime2()+"\"}",Map.class));
				wxTemplateMsgSendEsDTO.setData(data);
				feignWxTemplateMsgService.sendTemplateMsg(wxTemplateMsgSendEsDTO, SecurityConstants.FROM_IN);
			}catch (Exception e){
				log.error("发送订阅消息发送出错："+e.getMessage(), e);
			}
		}
		return this.updateById(openingQuotationDTO.getAttention());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateByDe(DepreciateDTO depreciateDTO) {
		if(StrUtil.isBlank(depreciateDTO.getAttention().getAttentionId()) && depreciateDTO.getAttention().getAttentionType().equals("4")) {
			//发送订阅消息
			try {
				Attention attention = baseMapper.selectById(depreciateDTO.getAttention().getId());
				WxTemplateMsgSendEsDTO wxTemplateMsgSendEsDTO = new WxTemplateMsgSendEsDTO();
				wxTemplateMsgSendEsDTO.setEstateUserId(attention.getUserId());
				wxTemplateMsgSendEsDTO.setUseType(ConfigConstant.WX_TMP_USE_TYPE_4);
				wxTemplateMsgSendEsDTO.setPage("pages/index/index");
				HashMap<String, HashMap<String, String>> data = new HashMap<>();
				data.put("thing1", (HashMap)JSONUtil.toBean("{\"value\": \""+depreciateDTO.getThing1()+"\"}", Map.class));
				data.put("thing4", (HashMap)JSONUtil.toBean("{\"value\": \""+depreciateDTO.getThing4()+"\"}",Map.class));
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
				data.put("character_string2", (HashMap)JSONUtil.toBean("{\"value\": \""+depreciateDTO.getCharacter_string2()+"\"}",Map.class));
				data.put("character_string3", (HashMap)JSONUtil.toBean("{\"value\": \""+depreciateDTO.getCharacter_string3()+"\"}",Map.class));
				wxTemplateMsgSendEsDTO.setData(data);
				feignWxTemplateMsgService.sendTemplateMsg(wxTemplateMsgSendEsDTO, SecurityConstants.FROM_IN);
			}catch (Exception e){
				log.error("发送订阅消息发送出错："+e.getMessage(), e);
			}
		}
		return this.updateById(depreciateDTO.getAttention());
	}
}
