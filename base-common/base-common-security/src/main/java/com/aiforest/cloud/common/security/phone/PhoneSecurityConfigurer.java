package com.aiforest.cloud.common.security.phone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.aiforest.cloud.common.security.component.ResourceAuthExceptionEntryPoint;
import com.aiforest.cloud.common.security.service.BaseUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author
 * 手机号登录配置入口
 */
@Getter
@Setter
@Component
public class PhoneSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AuthenticationEventPublisher defaultAuthenticationEventPublisher;
	private AuthenticationSuccessHandler mobileLoginSuccessHandler;
	private BaseUserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		PhoneAuthenticationFilter phoneAuthenticationFilter = new PhoneAuthenticationFilter();
		phoneAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		phoneAuthenticationFilter.setAuthenticationSuccessHandler(mobileLoginSuccessHandler);
		phoneAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
		phoneAuthenticationFilter.setAuthenticationEntryPoint(new ResourceAuthExceptionEntryPoint(objectMapper));

		PhoneAuthenticationProvider phoneAuthenticationProvider = new PhoneAuthenticationProvider();
		phoneAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(phoneAuthenticationProvider)
			.addFilterAfter(phoneAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
