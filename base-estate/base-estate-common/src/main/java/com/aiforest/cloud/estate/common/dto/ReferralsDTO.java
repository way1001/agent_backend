package com.aiforest.cloud.estate.common.dto;

import lombok.Data;

@Data
public class ReferralsDTO {

	private Integer currentPage;

	private Integer pageSize;

	private String affiliationId;

	private String brokerId;

	private String tenantId;

	private String currentHandler;

	private String customerName;

	private String phone;

}
