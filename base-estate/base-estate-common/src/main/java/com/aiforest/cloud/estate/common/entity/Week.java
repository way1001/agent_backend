package com.aiforest.cloud.estate.common.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Week implements Serializable {

	/** 日期(2018-06-01~2018-06-10) **/
	private String day;
	/** 开始日期 **/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	/** 结束日期 **/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	/** 开始日期数字化 **/
	private Integer startTimeNumber;
	/** 结束日期数字化 **/
	private Integer endTimeNumber;
}
