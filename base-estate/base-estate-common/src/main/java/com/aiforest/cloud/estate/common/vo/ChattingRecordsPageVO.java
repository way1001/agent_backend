package com.aiforest.cloud.estate.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChattingRecordsPageVO {
	private Integer total;

	private Integer currentPage;

	private Integer pageSize;

	private List<ChattingRecordsVO> chattingRecordsVOList;
}
