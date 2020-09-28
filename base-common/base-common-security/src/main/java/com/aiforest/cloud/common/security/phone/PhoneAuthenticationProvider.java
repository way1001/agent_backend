package com.aiforest.cloud.common.security.phone;

import com.aiforest.cloud.common.security.component.BasePreAuthenticationChecks;
import com.aiforest.cloud.common.security.service.BaseUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author
 * 手机登录校验逻辑
 * 验证码登录、社交登录
 */
@Slf4j
public class PhoneAuthenticationProvider implements AuthenticationProvider {
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private UserDetailsChecker detailsChecker = new BasePreAuthenticationChecks();

	@Getter
	@Setter
	private BaseUserDetailsService userDetailsService;

	@Override
	@SneakyThrows
	public Authentication authenticate(Authentication authentication) {
		PhoneAuthenticationToken phoneAuthenticationToken = (PhoneAuthenticationToken) authentication;

		String principal = phoneAuthenticationToken.getPrincipal().toString();
		UserDetails userDetails = userDetailsService.loadUserByPhone(principal);
		if (userDetails == null) {
			log.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.noopBindAccount",
					"Noop Bind Account"));
		}

		// 检查账号状态
		detailsChecker.check(userDetails);

		PhoneAuthenticationToken authenticationToken = new PhoneAuthenticationToken(userDetails, userDetails.getAuthorities());
		authenticationToken.setDetails(phoneAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
