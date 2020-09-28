/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import com.aiforest.cloud.weixin.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import com.aiforest.cloud.weixin.admin.service.WxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户
 *
 * @author JL
 * @date 2019-03-25 15:39:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxuser")
@Api(value = "wxuser", tags = "微信用户管理")
public class WxUserController {

	private final WxUserService wxUserService;

	/**
	* 分页查询
	* @param page 分页对象
	* @param wxUser 微信用户
	* @return
	*/
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:index')")
	public R getWxUserPage(Page page, WxUser wxUser,String tagId) {
		Wrapper<WxUser> queryWrapper;
		if(StringUtils.isNotBlank(tagId)){
			queryWrapper = Wrappers.lambdaQuery(wxUser)
					.and(wrapper -> wrapper
							.eq(WxUser::getTagidList,"["+tagId+"]")
							.or()
							.like(WxUser::getTagidList,","+tagId+",")
							.or()
							.likeRight(WxUser::getTagidList,"["+tagId+",")
							.or()
							.likeLeft(WxUser::getTagidList,","+tagId+"]"));
		}else if(StrUtil.isNotBlank(wxUser.getNickName())){
			String nickName = wxUser.getNickName();
			wxUser.setNickName(null);
			queryWrapper = Wrappers.lambdaQuery(wxUser)
					.like(WxUser::getNickName,nickName);
		}else{
			queryWrapper = Wrappers.lambdaQuery(wxUser);
		}
		return R.ok(wxUserService.page(page,queryWrapper));
	}


	/**
	* 通过id查询微信用户
	* @param id id
	* @return R
	*/
	@ApiOperation(value = "通过id查询微信用户")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:get')")
	public R getById(@PathVariable("id") String id){
	return R.ok(wxUserService.getById(id));
	}

	/**
	* 新增微信用户
	* @param wxUser 微信用户
	* @return R
	*/
	@ApiOperation(value = "新增微信用户")
	@SysLog("新增微信用户")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:add')")
	public R save(@RequestBody WxUser wxUser){
	return R.ok(wxUserService.save(wxUser));
	}

	/**
	* 修改微信用户
	* @param wxUser 微信用户
	* @return R
	*/
	@ApiOperation(value = "修改微信用户")
	@SysLog("修改微信用户")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:edit')")
	public R updateById(@RequestBody WxUser wxUser){
	return R.ok(wxUserService.updateById(wxUser));
	}

	/**
	* 通过id删除微信用户
	* @param id id
	* @return R
	*/
	@ApiOperation(value = "通过id删除微信用户")
	@SysLog("删除微信用户")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:del')")
	public R removeById(@PathVariable String id){
    return R.ok(wxUserService.removeById(id));
  }

	@ApiOperation(value = "同步微信用户")
	@SysLog("同步微信用户")
	@PostMapping("/synchron")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:synchro')")
	public R synchron(@RequestBody WxUser wxUser){
		try {
			wxUserService.synchroWxUser(wxUser.getAppId());
			return new R<>();
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("同步微信用户失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 修改微信用户备注
	 * @param wxUser
	 * @return
	 */
	@ApiOperation(value = "修改微信用户备注")
	@SysLog("修改微信用户备注")
	@PutMapping("/remark")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:edit:remark')")
	public R remark(@RequestBody WxUser wxUser){
		try {
			return R.ok(wxUserService.updateRemark(wxUser));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("修改微信用户备注失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 打标签
	 * @param data
	 * @return
	 */
	@ApiOperation(value = "打标签")
	@PutMapping("/tagid-list")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxuser:tagging')")
	public R tagidList(@RequestBody JSONObject data){
		try {
			String appId = data.getStr("appId");
			String taggingType = data.getStr("taggingType");
			JSONArray tagIdsArray = data.getJSONArray("tagIds");
			JSONArray openIdsArray = data.getJSONArray("openIds");
			String[] openIds = openIdsArray.toArray(new String[0]);
			for(Object tagId : tagIdsArray){
				wxUserService.tagging(taggingType,appId,Long.valueOf(String.valueOf(tagId)),openIds);
			}
			return new R<>();
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("修改微信用户备注失败", e);
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 通过id查询微信用户
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询微信用户")
	@Inside
	@GetMapping("/inside/{id}")
	public R getByIdInside(@PathVariable("id") String id){
		return R.ok(wxUserService.getById(id));
	}

	/**
	 * 保存微信用户
	 * @param wxOpenDataDTO
	 * @return R
	 */
	@ApiOperation(value = "保存微信用户")
	@Inside
	@PostMapping("/inside")
	public R saveInside(@RequestBody WxOpenDataDTO wxOpenDataDTO){
		return R.ok(wxUserService.saveInside(wxOpenDataDTO));
	}

	/**
	 * 保存微信用户
	 * @param wxOpenDataDTO
	 * @return R
	 */
	@ApiOperation(value = "保存微信用户ES")
	@Inside
	@PostMapping("/insidees")
	public R saveInsideEs(@RequestBody WxOpenDataDTO wxOpenDataDTO){
		return R.ok(wxUserService.saveInsideEs(wxOpenDataDTO));
	}
}
