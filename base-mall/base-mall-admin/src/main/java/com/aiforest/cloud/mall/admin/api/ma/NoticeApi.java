/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.NoticeService;
import com.aiforest.cloud.mall.common.entity.Notice;
import com.aiforest.cloud.weixin.common.util.WxMaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 商城通知
 *
 * @author JL
 * @date 2019-11-09 19:37:56
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/notice")
@Api(value = "notice", tags = "商城通知API")
public class NoticeApi {

    private final NoticeService noticeService;

	/**
	 * 查询商城通知
	 * @param notice
	 * @return R
	 */
	@ApiOperation(value = "查询商城通知")
	@GetMapping
	public R getOne(HttpServletRequest request, Notice notice){
		notice.setAppId(WxMaUtil.getAppId(request));
		notice = noticeService.getOne(Wrappers.query(notice));
		return R.ok(notice);
	}

}
