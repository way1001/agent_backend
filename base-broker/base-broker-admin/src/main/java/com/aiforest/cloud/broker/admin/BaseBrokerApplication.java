/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.admin;

import com.aiforest.cloud.common.security.annotation.EnableBaseFeignClients;
import com.aiforest.cloud.common.security.annotation.EnableBaseResourceServer;
import com.aiforest.cloud.common.swagger.annotation.BaseEnableSwagger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author way
 * @date 2020/03/31
 * 房产模块
 */
@BaseEnableSwagger
@SpringCloudApplication
@EnableBaseFeignClients
@EnableBaseResourceServer
@EnableProcessApplication
public class BaseBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseBrokerApplication.class, args);
	}

}
