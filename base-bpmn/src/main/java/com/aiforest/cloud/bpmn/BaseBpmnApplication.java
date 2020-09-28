package com.aiforest.cloud.bpmn;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author
 * 认证授权中心
 */
@EnableProcessApplication
@SpringCloudApplication
public class BaseBpmnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseBpmnApplication.class, args);
	}
}
