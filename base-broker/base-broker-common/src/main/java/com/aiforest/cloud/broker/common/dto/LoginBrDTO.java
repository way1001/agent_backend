package com.aiforest.cloud.broker.common.dto;

import lombok.Data;

@Data
public class LoginBrDTO {
	private String phone;
	private String captcha;
}
