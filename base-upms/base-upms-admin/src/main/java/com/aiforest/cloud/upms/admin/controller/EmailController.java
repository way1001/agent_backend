package com.aiforest.cloud.upms.admin.controller;

import cn.hutool.core.util.RandomUtil;
import com.aiforest.cloud.common.email.util.EmailUtils;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.upms.admin.mapper.SysUserMapper;
import com.aiforest.cloud.upms.common.entity.SysEmail;
import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.common.core.constant.CacheConstants;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.security.annotation.Inside;
import io.github.biezhi.ome.SendMailException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author JL
 * @date 2019/07/14
 * 邮箱管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/email")
@Api(value = "email", tags = "邮箱管理")
public class EmailController {
	private final SysUserMapper sysUserMapper;
	private final RedisTemplate redisTemplate;
	private final EmailUtils emailUtils;

	/**
	 * 发送邮箱验证码
	 *
	 * @param to
	 * @return
	 */
	@ApiOperation(value = "发送邮箱验证码")
	@Inside(value = false)
	@GetMapping("/{to}")
	public R sendEmailCode(@PathVariable String to, @RequestParam("type") String type) throws SendMailException {
		String title = "";
		String content = "";
		switch (type) {
			case CommonConstants.EMAIL_SEND_TYPE_REGISTER://注册
				SysUser sysUser = new SysUser();
				sysUser.setEmail(to);
				sysUser = sysUserMapper.getByNoTenant(sysUser);
				if (sysUser != null) {
					return R.failed("该邮箱已被注册");
				}
				String key = CacheConstants.VER_CODE_REGISTER + CommonConstants.EMAIL + ":" + to;
				String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));//生成验证码
				redisTemplate.opsForValue().set(key, code, 2, TimeUnit.HOURS);//验证码有效期2小时
				title = "aiforest快速开发平台用户注册";
				content = "你正在注册aiforest快速开发平台用户，邮箱验证码：" + code + "；验证码有效期为2小时，请尽快完成注册";
		}
		emailUtils.sendEmail(to, title, content);
		return R.ok();
	}

	/**
	 * 发送邮件
	 * @param sysEmail
	 * @return
	 */
	@ApiOperation(value = "发送邮件")
	@SysLog("发送邮件")
	@PostMapping("/send")
	@PreAuthorize("@ato.hasAuthority('sys:email:add')")
	public R sendEmail(@RequestBody SysEmail sysEmail) throws SendMailException {
		emailUtils.sendEmail(sysEmail.getTo(), sysEmail.getTitle(), sysEmail.getContent());
		return R.ok();
	}
}
