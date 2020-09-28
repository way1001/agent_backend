package com.aiforest.cloud.broker.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoDto implements Serializable {

	private String id;

	private String tenantId;

	private Integer userCode;

	private String phone;

	private String userRole;


	private String realName;

	private String headimgUrl;
}