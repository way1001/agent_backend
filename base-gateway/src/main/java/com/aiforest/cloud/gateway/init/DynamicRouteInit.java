/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.gateway.init;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.gateway.entity.GatewayRouteList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author
 * <p>
 * 初始化网关路由
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInit {
	private RouteDefinitionWriter routeDefinitionWriter;
	private NacosConfigProperties nacosProperties;

	@PostConstruct
	public void initRoute() {
		try {
			Properties properties = new Properties();
			properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getServerAddr());
			properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
			properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
			ConfigService configService = NacosFactory.createConfigService(properties);
			String content = configService.getConfig(CommonConstants.CONFIG_DATA_ID, CommonConstants.CONFIG_GROUP, CommonConstants.CONFIG_TIMEOUT_MS);
			log.info("初始化网关路由开始");
			updateRoute(content);
			log.info("初始化网关路由完成");
			//开户监听，实现动态
			configService.addListener(CommonConstants.CONFIG_DATA_ID, CommonConstants.CONFIG_GROUP, new Listener() {
				@Override
				public void receiveConfigInfo(String configInfo) {
					log.info("更新网关路由开始");
					updateRoute(configInfo);
					log.info("更新网关路由完成");
				}

				@Override
				public Executor getExecutor() {
					return null;
				}
			});
		} catch (NacosException e) {
			log.error("加载路由出错：{}", e.getErrMsg());
		}
	}

	public void updateRoute(String content){
		Yaml yaml = new Yaml();
		GatewayRouteList gatewayRouteList = yaml.loadAs(content,GatewayRouteList.class);
		gatewayRouteList.getRoutes().forEach(route -> {
			log.info("加载路由：{},{}", route.getId(), route);
			routeDefinitionWriter.save(Mono.just(route)).subscribe();
		});
	}
}
