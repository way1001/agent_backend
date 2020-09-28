/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.AwesomeShootingService;
import com.aiforest.cloud.estate.common.constant.MyReturnCode;
import com.aiforest.cloud.estate.common.dto.AuditDTO;
import com.aiforest.cloud.estate.common.entity.AwesomeShooting;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
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
@RequestMapping("/api/es/awesomeshooting")
@Api(value = "awesomeshooting", tags = "房产炫拍")
public class AwesomeShootingApi {

	private final AwesomeShootingService awesomeShootingService;

	/**
	 * 查询炫拍
	 *
	 * @return R
	 */
	@GetMapping("/page")
	public R getPage(Page page, AuditDTO auditDTO) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		return R.ok(awesomeShootingService.page1(page, auditDTO));
	}


	/**
	 * 新增炫拍
	 * @param
	 * @return R
	 */
	@PostMapping
	public R save( @RequestBody AwesomeShooting awesomeShooting){
		//检验用户session登录
//		R checkThirdSession = BaseApi.checkThirdSession(awesomeShooting, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		bargainCut.setUserId(ThirdSessionHolder.getMallUserId());
		awesomeShooting.setCreateId(ThirdSessionHolder.getEstateUserId());
		if(awesomeShooting.getPicUrls().length <= 0){
			R.failed(MyReturnCode.ERR_90002.getCode(), MyReturnCode.ERR_90002.getMsg());
		}

		return R.ok(awesomeShootingService.save(awesomeShooting));
	}


	@PostMapping("/addviews")
	public R addViews(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		return R.ok(awesomeShootingService.addViews(id));
	}

	@PostMapping("/thumbup")
	public R likes(@RequestBody Map<String,String> param) {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		String id = param.get("id");
		String userId = param.get("userId");
		return R.ok(awesomeShootingService.thumbUp(id, userId));
	}

	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		return R.ok(awesomeShootingService.removeById(id));
	}

	/**
	 * 房产炫拍修改
	 * @param awesomeShooting 房产炫拍
	 * @return R
	 */
	@PutMapping
	public R updateById(@RequestBody AwesomeShooting awesomeShooting) {
		return R.ok(awesomeShootingService.updateById(awesomeShooting));
	}

}
