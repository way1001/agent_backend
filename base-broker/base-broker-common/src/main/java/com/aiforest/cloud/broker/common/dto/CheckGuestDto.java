package com.aiforest.cloud.broker.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckGuestDto implements Serializable {
	private Boolean isValid;
	private Boolean directPush;
}