package com.aiforest.cloud.upms.admin.controller;

import com.aliyuncs.exceptions.ClientException;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.upms.admin.service.PhoneService;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.upms.admin.service.SysTenantService;
import com.aiforest.cloud.upms.admin.service.SysUserService;
import com.aiforest.cloud.upms.common.dto.UserInfo;
import com.aiforest.cloud.upms.common.entity.SysTenant;
import com.aiforest.cloud.upms.common.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.aiforest.cloud.common.security.annotation.Inside;

/**
 * @author JL
 * @date 2018/11/14
 * <p>
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/phone")
@Api(value = "phone", tags = "手机登录管理模块")
public class PhoneController {
	private final PhoneService phoneService;
	private final SysUserService sysUserService;
	private final SysTenantService sysTenantService;

	/**
	 * 发送手机验证码
	 * @param phone
	 * @return
	 * @throws ClientException
	 */
	@Inside(value = false)
	@PutMapping("/code")
	public R sendSmsCode(String phone, String type) throws ClientException {
		return phoneService.sendSmsCode(phone,type);
	}

	/**
	 * 通过手机号查询用户、角色信息
	 *
	 * @param phone
	 * @return
	 */
	@ApiOperation(value = "查询用户、角色信息")
	@Inside(value = false)
	@GetMapping("/info/{phone}")
	public R getUserInfo(@PathVariable String phone) {
		SysUser sysUser = new  SysUser();
		sysUser.setPhone(phone);
		sysUser = sysUserService.getByNoTenant(sysUser);
		if (sysUser == null) {
			return R.failed(null, String.format("用户信息为空 %s", phone));
		}
		TenantContextHolder.setTenantId(sysUser.getTenantId());
		if(CommonConstants.STATUS_NORMAL.equals(sysUser.getLockFlag())){
			//查询所属租户状态是否正常，否则禁止登录
			SysTenant sysTenant = sysTenantService.getById(sysUser.getTenantId());
			if(CommonConstants.STATUS_LOCK.equals(sysTenant.getStatus())){
				sysUser.setLockFlag(CommonConstants.STATUS_LOCK);
			}
		}
		UserInfo userInfo = sysUserService.findUserInfo(sysUser);
		return R.ok(userInfo);
	}
}
