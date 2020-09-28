package com.aiforest.cloud.upms.admin.controller;

import com.aiforest.cloud.upms.common.feign.FeignTokenService;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author
 * getTokenPage 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
@Api(value = "token", tags = "令牌管理模块")
public class TokenController {
	private final FeignTokenService feignTokenService;

	/**
	 * 分页token 信息
	 *
	 * @param params 参数集
	 * @return token集合
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('sys:token:index')")
	public R getTokenPage(@RequestParam Map<String, Object> params) {
		return feignTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 *
	 * @param token getTokenPage
	 * @return ok/false
	 */
	@ApiOperation(value = "删除")
	@SysLog("删除用户token")
	@DeleteMapping("/{token}")
	@PreAuthorize("@ato.hasAuthority('sys:token:del')")
	public R removeById(@PathVariable String token) {
		return feignTokenService.removeTokenById(token, SecurityConstants.FROM_IN);
	}
}
