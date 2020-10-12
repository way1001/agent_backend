package com.aiforest.cloud.estate.common.vo;

import lombok.Data;

@Data
public class ChattingRecordsVO {

	private String id;

	private String tenantId;

	private String affiliationId;

	private String createTime;

	private String senderId;

	private String receiverId;

	private String messageblob;

	private String status;

	private String type;

}
