package com.aiforest.cloud.estate.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReferralsPageVO {

	private Integer total;

	private Integer currentPage;

	private Integer pageSize;

	private List<ReferralsVO> referrals;

}
