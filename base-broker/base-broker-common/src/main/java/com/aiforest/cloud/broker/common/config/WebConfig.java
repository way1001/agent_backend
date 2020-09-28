package com.aiforest.cloud.broker.common.config;

import com.aiforest.cloud.broker.common.interceptor.ThirdSessionInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 */
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final RedisTemplate redisTemplate;

	/**
	 * 拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 进入ThirdSession拦截器
		 */
		registry.addInterceptor(new ThirdSessionInterceptor(redisTemplate))
				.addPathPatterns("/mapi/**")//拦截/api/**接口
				.addPathPatterns("/engine-rest/history/**")
				.excludePathPatterns("/mapi/br/userinfo/login")
				.excludePathPatterns("/mapi/br/yuntongxun")
				.excludePathPatterns("/mapi/br/file/upload");//放行接口
	}
}
