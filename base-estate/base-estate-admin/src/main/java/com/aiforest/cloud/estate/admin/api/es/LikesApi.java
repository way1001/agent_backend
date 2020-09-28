/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.LikesService;
import com.aiforest.cloud.estate.common.entity.Likes;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/likes")
@Api(value = "likes", tags = "房产点赞")
public class LikesApi {

	private final LikesService likesService;

	/**
	 * 分页列表
	 * @param likes 房产点赞
	 * @return
	 */
	@GetMapping("/list")
	public R getList(Likes likes) {
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(likesService.list1(likes));
	}

}
