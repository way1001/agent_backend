package com.aiforest.cloud.common.storage.entity;

import lombok.Data;

@Data
public class StorageConfig {
	private String accessKeyId;// 请填写您的AccessKeyId。
	private String accessKeySecret; // 请填写您的AccessKeySecret。
	private String endpoint; // 请填写您的 endpoint。
	private String bucket; // 请填写您的 bucketname 。
}
