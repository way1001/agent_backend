package com.aiforest.cloud.auth.handler;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.security.entity.BaseUser;
import com.aiforest.cloud.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import com.aiforest.cloud.common.security.util.SecurityUtils;
import com.aiforest.cloud.upms.common.entity.SysLogLogin;
import com.aiforest.cloud.upms.common.feign.FeignLogLoginService;
import com.aiforest.cloud.upms.common.util.IpUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author
 */
@Slf4j
@Component
@AllArgsConstructor
public class BaseAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	private final FeignLogLoginService feignLogLoginService;

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		BaseUser baseUser = SecurityUtils.getUser(authentication);
		TenantContextHolder.setTenantId(baseUser.getTenantId());
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		//异步处理登录日志
		CompletableFuture.runAsync(() -> {
			SysLogLogin sysLogLogin = new SysLogLogin();
			sysLogLogin.setCreateId(baseUser.getId());
			sysLogLogin.setCreateBy(baseUser.getUsername());
			sysLogLogin.setRemoteAddr(ServletUtil.getClientIP(request));
			sysLogLogin.setRequestUri(URLUtil.getPath(request.getRequestURI()));
			sysLogLogin.setUserAgent(request.getHeader("user-agent"));
			sysLogLogin.setParams(HttpUtil.toParams(request.getParameterMap()));
			String address = IpUtils.getAddresses(sysLogLogin.getRemoteAddr());
			sysLogLogin.setAddress(address);
			feignLogLoginService.saveLogLogin(sysLogLogin, SecurityConstants.FROM_IN);
		});
	}
}
