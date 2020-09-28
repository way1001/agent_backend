/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import com.aiforest.cloud.weixin.admin.service.WxUserService;
import com.aiforest.cloud.weixin.common.entity.WxUserTagsDict;
import com.aiforest.cloud.weixin.admin.config.mp.WxMpConfiguration;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户标签
 *
 * @author JL
 * @date 2019-03-25 15:39:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxusertags")
@Api(value = "wxusertags", tags = "微信用户标签管理")
public class WxUserTagsController {

	private final WxUserService wxUserService;

	/**
	* 获取微信用户标签
	* @return
	*/
	@ApiOperation(value = "获取微信用户标签")
	@GetMapping("/list")
	public R getWxUserList(String appId) {
		WxMpUserTagService wxMpUserTagService = WxMpConfiguration.getMpService(appId).getUserTagService();
		try {
			List<WxUserTag> listWxUserTag =  wxMpUserTagService.tagGet();
			return R.ok(listWxUserTag);
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取微信用户标签失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 获取微信用户标签字典
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "获取微信用户标签字典")
	@GetMapping("/dict")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxusertags:list')")
	public R getWxUserTagsDict(String appId) {
		WxMpUserTagService wxMpUserTagService = WxMpConfiguration.getMpService(appId).getUserTagService();
		try {
			List<WxUserTag> listWxUserTag =  wxMpUserTagService.tagGet();
			List<WxUserTagsDict> listWxUserTagsDict = new ArrayList<>();
			WxUserTagsDict wxUserTagsDict;
			for(WxUserTag wxUserTag : listWxUserTag){
				wxUserTagsDict = new WxUserTagsDict();
				wxUserTagsDict.setName(wxUserTag.getName());
				wxUserTagsDict.setValue(wxUserTag.getId());
				listWxUserTagsDict.add(wxUserTagsDict);
			}
			return R.ok(listWxUserTagsDict);
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取微信用户标签字典失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 新增微信用户标签
	 * @return
	 */
	@ApiOperation(value = "新增微信用户标签")
	@SysLog("新增微信用户标签")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxusertags:add')")
	public R save(@RequestBody JSONObject data){
		String appId = data.getStr("appId");
		String name = data.getStr("name");
		WxMpUserTagService wxMpUserTagService = WxMpConfiguration.getMpService(appId).getUserTagService();
		try {
			return R.ok(wxMpUserTagService.tagCreate(name));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("新增微信用户标签失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 修改微信用户标签
	 * @return
	 */
	@ApiOperation(value = "修改微信用户标签")
	@SysLog("修改微信用户标签")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxusertags:edit')")
	public R updateById(@RequestBody JSONObject data){
		String appId = data.getStr("appId");
		Long id = data.getLong("id");
		String name = data.getStr("name");
		WxMpUserTagService wxMpUserTagService = WxMpConfiguration.getMpService(appId).getUserTagService();
		try {
			return R.ok(wxMpUserTagService.tagUpdate(id,name));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("修改微信用户标签失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 删除微信用户标签
	 * @param id
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "删除微信用户标签")
	@SysLog("删除微信用户标签")
	@DeleteMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxusertags:del')")
	public R removeById(Long id,String appId){
		int count = wxUserService.count(Wrappers.<WxUser>lambdaQuery()
				.eq(WxUser::getAppId, appId)
				.and(wrapper -> wrapper
						.eq(WxUser::getTagidList,"["+id+"]")
						.or()
						.like(WxUser::getTagidList,","+id+",")
						.or()
						.likeRight(WxUser::getTagidList,"["+id+",")
						.or()
						.likeLeft(WxUser::getTagidList,","+id+"]")));
		if(count>0){
			return R.failed("该标签下有用户存在，无法删除");
		}
		WxMpUserTagService wxMpUserTagService = WxMpConfiguration.getMpService(appId).getUserTagService();
		try {
			return  R.ok(wxMpUserTagService.tagDelete(id));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("删除微信用户标签失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}
}
