/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.api.ma;

import cn.binarywang.wx.miniapp.api.WxMaLiveService;
import cn.binarywang.wx.miniapp.bean.WxMaLiveResult;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.admin.config.ma.WxMaConfiguration;
import com.aiforest.cloud.weixin.common.util.WxMaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 小程序直播
 *
 * @author JL
 * @date 2019-03-23 21:26:35
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/wxmalive")
@Api(value = "wxmalive", tags = "小程序直播API")
public class WxMaLiveApi {

	private final RedisTemplate<String, String> redisTemplate;

	/**
	* 获取直播房间列表
	* @return
	*/
	@ApiOperation(value = "获取直播房间列表")
	@GetMapping("/roominfo/list")
	public R getWxMaterialList(HttpServletRequest request) {
		try {
			String appId = WxMaUtil.getAppId(request);
			String redisKey = "wx:ma:liveinfo:" + appId;
			Object obj = redisTemplate.opsForValue().get(redisKey);
			List<WxMaLiveResult.RoomInfo> listRoomInfo;
			if(obj != null) {
				JSONArray jSONArray = JSONUtil.parseArray(obj);
				listRoomInfo = JSONUtil.toList(jSONArray, WxMaLiveResult.RoomInfo.class);
				return R.ok(listRoomInfo);
			}
			WxMaLiveService wxMaLiveService = WxMaConfiguration.getMaService(appId).getLiveService();
			listRoomInfo = wxMaLiveService.getLiveInfos();
			//由于微信限制接口总上限 500 次/天，所以将数据放入redis缓存5分钟，防止接口超过调用次数
			redisTemplate.opsForValue().set(redisKey, JSONUtil.parseArray(listRoomInfo).toString(), SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
			return R.ok(listRoomInfo);
		} catch (WxErrorException e) {
			log.error("获取直播房间列表失败", e);
			return R.ok(new ArrayList<>());
		}
	}
}
