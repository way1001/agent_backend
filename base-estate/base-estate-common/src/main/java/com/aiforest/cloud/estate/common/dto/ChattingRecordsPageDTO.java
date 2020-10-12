package com.aiforest.cloud.estate.common.dto;

import lombok.Data;

@Data
public class ChattingRecordsPageDTO {

	private Integer currentPage;

	private Integer pageSize;

	private String affiliationId;

	private String tenantId;

	private String userId;

}
