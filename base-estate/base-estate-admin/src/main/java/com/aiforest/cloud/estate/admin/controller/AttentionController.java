/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.estate.common.dto.DepreciateDTO;
import com.aiforest.cloud.estate.common.dto.OpeningQuotationDTO;
import com.aiforest.cloud.estate.common.entity.Attention;
import com.aiforest.cloud.estate.admin.service.AttentionService;
import com.aiforest.cloud.estate.common.feign.FeignWxTemplateMsgService;
import com.aiforest.cloud.weixin.common.constant.ConfigConstant;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendEsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 房产关注
 *
 * @author way
 * @date 2020-05-04 14:31:04
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/attention")
@Api(value = "attention", tags = "房产关注管理")
public class AttentionController {

    private final AttentionService attentionService;
	private final FeignWxTemplateMsgService feignWxTemplateMsgService;

	/**
     * 分页列表
     * @param page 分页对象
     * @param attention 房产关注
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate:attention:index')")
    public R getPage(Page page, Attention attention) {
        return R.ok(attentionService.page1(page, attention));
    }

    /**
     * 房产关注查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "房产关注查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:attention:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(attentionService.getById(id));
    }

    /**
     * 房产关注新增
     * @param attention 房产关注
     * @return R
     */
    @ApiOperation(value = "房产关注新增")
    @SysLog("新增房产关注")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate:attention:add')")
    public R save(@RequestBody Attention attention) {
        return R.ok(attentionService.save(attention));
    }

    /**
     * 房产关注修改
     * @param attention 房产关注
     * @return R
     */
    @ApiOperation(value = "房产关注修改")
    @SysLog("修改房产关注")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate:attention:edit')")
    public R updateById(@RequestBody Attention attention) {
        return R.ok(attentionService.updateById(attention));
    }

	@ApiOperation(value = "房产关注修改")
	@SysLog("修改房产关注")
	@PutMapping("/op")
	@PreAuthorize("@ato.hasAuthority('estate:attention:edit')")
	public R updateByOpeningQuotation(@RequestBody OpeningQuotationDTO openingQuotationDTO) {
		return R.ok(attentionService.updateByOP(openingQuotationDTO));
	}

	@ApiOperation(value = "房产关注修改")
	@SysLog("修改房产关注")
	@PutMapping("/de")
	@PreAuthorize("@ato.hasAuthority('estate:attention:edit')")
	public R updateByDepreciate(@RequestBody DepreciateDTO depreciateDTO) {
		return R.ok(attentionService.updateByDe(depreciateDTO));
	}


	@ApiOperation(value = "房产关注查询数量")
	@SysLog("查询房产关注数量")
	@GetMapping("/ct/{ct}")
	@PreAuthorize("@ato.hasAuthority('estate:attention:get')")
	public R getCount(@PathVariable("ct") String ct) {
    	Attention attention = new Attention();
    	attention.setAttentionType(ct);
		attention.setAttentionStatus("0");
		return R.ok(attentionService.count(Wrappers.query(attention)));
	}


	@ApiOperation(value = "批量推送操作")
	@SysLog("批量推送操作")
	@PutMapping("/push")
	@PreAuthorize("@ato.hasAuthority('estate:attention:edit')")
	public R batchPush(@RequestBody JSONObject data){
		String type = data.getStr("type");
		String thing1 = data.getStr("thing1");
		String character_string2= data.getStr("character_string2");
		String character_string3= data.getStr("character_string3");
		String thing4= data.getStr("thing4");
		String time2= data.getStr("time2");
		String thing3= data.getStr("thing3");

		if (!type.equals("4") && !type.equals("5")) {
			return R.failed();
		}
		Attention attention = new Attention();
		attention.setAttentionType(type);
		attention.setAttentionStatus("0");
		List<Attention> list = attentionService.list(Wrappers.query(attention));

		for (Attention a: list) {
			//发送订阅消息
			try {
				WxTemplateMsgSendEsDTO wxTemplateMsgSendEsDTO = new WxTemplateMsgSendEsDTO();
				wxTemplateMsgSendEsDTO.setEstateUserId(a.getUserId());
				wxTemplateMsgSendEsDTO.setUseType(ConfigConstant.WX_TMP_USE_TYPE_5);
				wxTemplateMsgSendEsDTO.setPage("pages/order/order-detail/index");
				HashMap<String, HashMap<String, String>> data1 = new HashMap<>();
				if (type.equals("4")) {
					data1.put("thing1", (HashMap)JSONUtil.toBean("{\"value\": \""+thing1+"\"}", Map.class));
					data1.put("thing4", (HashMap)JSONUtil.toBean("{\"value\": \""+thing4+"\"}",Map.class));
					data1.put("character_string2", (HashMap)JSONUtil.toBean("{\"value\": \""+character_string2+"\"}",Map.class));
					data1.put("character_string3", (HashMap)JSONUtil.toBean("{\"value\": \""+character_string3+"\"}",Map.class));
				} else {
					data1.put("thing1", (HashMap) JSONUtil.toBean("{\"value\": \""+thing1+"\"}", Map.class));
					data1.put("thing3", (HashMap)JSONUtil.toBean("{\"value\": \""+thing3+"\"}",Map.class));
					data1.put("time2", (HashMap)JSONUtil.toBean("{\"value\": \""+time2+"\"}",Map.class));
				}

				wxTemplateMsgSendEsDTO.setData(data1);
				feignWxTemplateMsgService.sendTemplateMsg(wxTemplateMsgSendEsDTO, SecurityConstants.FROM_IN);
			}catch (Exception e){
				log.error("发送订阅消息发送出错："+e.getMessage(), e);
			}
			a.setAttentionStatus("1");
			attentionService.updateById(a);

		}

		return R.ok();
	}
    /**
     * 房产关注删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "房产关注删除")
    @SysLog("删除房产关注")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate:attention:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(attentionService.removeById(id));
    }

}
