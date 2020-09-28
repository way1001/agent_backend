package com.aiforest.cloud.upms.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.sms.config.SmsTemplateProperties;
import com.aiforest.cloud.common.sms.util.SmsUtils;
import com.aiforest.cloud.upms.admin.mapper.SysUserMapper;
import com.aiforest.cloud.upms.admin.service.PhoneService;
import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.common.core.constant.CacheConstants;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * <p>
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
	private final RedisTemplate redisTemplate;
	private final SysUserMapper userMapper;
	private final SmsUtils smsUtils;
	private final SmsTemplateProperties smsTemplateProperties;


	/**
	 * 发送手机验证码
	 *
	 * @param phone
	 * @param type
	 * @return code
	 */
	@Override
	public R<Boolean> sendSmsCode(String phone, String type) throws ClientException {
		SysUser sysUser = new  SysUser();
		sysUser.setPhone(phone);
		sysUser = userMapper.getByNoTenant(sysUser);

		String signName = null;
		String templateCode = null;
		if(CommonConstants.PHONE_CODE_1.equals(type)){
			if (sysUser == null) {
				log.info("手机号未注册:{}", phone);
				return R.failed(Boolean.FALSE, "该手机号未绑定任何账号");
			}
			signName = smsTemplateProperties.getSignName1();
			templateCode = smsTemplateProperties.getTemplateCode1();
		}else if(CommonConstants.PHONE_CODE_2.equals(type)){
			if (sysUser != null) {
				log.info("手机号已被注册:{}", phone);
				return R.failed(Boolean.FALSE, "该手机号已被其他账号绑定");
			}
			signName = smsTemplateProperties.getSignName2();
			templateCode = smsTemplateProperties.getTemplateCode2();
		}else if(CommonConstants.PHONE_CODE_3.equals(type)){
			signName = smsTemplateProperties.getSignName3();
			templateCode = smsTemplateProperties.getTemplateCode3();
		}

		String key = CacheConstants.VER_CODE_DEFAULT + SecurityConstants.SMS_LOGIN + ":" + phone;
		Long seconds = redisTemplate.opsForValue().getOperations().getExpire(key);

		if (seconds > 240) {
			log.info("验证码发送过频繁:{}", phone);
			return R.failed(Boolean.FALSE, "验证码发送过频繁，请稍后再试");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		log.debug("手机号生成验证码成功:{},{}", phone, code);
		smsUtils.sendSms(signName, phone, templateCode, "{\"code\":\""+code+"\"}");
		redisTemplate.opsForValue().set(key, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		return R.ok(Boolean.TRUE, code);
	}
}
