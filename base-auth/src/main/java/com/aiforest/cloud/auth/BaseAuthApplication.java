package com.aiforest.cloud.auth;

import com.aiforest.cloud.common.security.annotation.EnableBaseFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author
 * 认证授权中心
 */
@SpringCloudApplication
@EnableBaseFeignClients
public class BaseAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseAuthApplication.class, args);
	}
}
