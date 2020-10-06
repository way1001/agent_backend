package com.aiforest.cloud.broker.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ChatMessage implements Serializable {
	private String id;
	private String message;

	@Override
	public String toString() {
		return "ChatMessage [id=$id, message=$message]";
	}
}