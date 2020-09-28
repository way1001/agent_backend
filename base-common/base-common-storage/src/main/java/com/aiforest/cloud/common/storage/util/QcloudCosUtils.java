package com.aiforest.cloud.common.storage.util;

import com.aiforest.cloud.common.storage.entity.StorageConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.AllArgsConstructor;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @JL
 * 腾讯cos
 * 各操作调用
 */
@AllArgsConstructor
public class QcloudCosUtils {
	private final StorageConfig storageConfig;

	/**
	 * 上传文件
	 * @param file
	 * @param dir 用户上传文件时指定的文件夹。
	 */
	public String uploadFile(File file,String dir) {
		// 1 初始化用户身份信息（secretId, secretKey）。
		String secretId = storageConfig.getAccessKeyId();
		String secretKey = storageConfig.getAccessKeySecret();
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		// 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
		// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
		Region region = new Region(storageConfig.getEndpoint());
		ClientConfig clientConfig = new ClientConfig(region);
		// 3 生成 cos 客户端。
		COSClient cosClient = new COSClient(cred, clientConfig);
		// 指定要上传到的存储桶
		String bucketName = storageConfig.getBucket();
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String key = dir + UUID.randomUUID()+ "." + suffix;
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
		// 上传文件。
		cosClient.putObject(putObjectRequest);
		// 关闭OSSClient。
		cosClient.shutdown();
		Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
		URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
		String resultStr = "https://" +  url.getHost() + "/" + key;
		return resultStr;
	}
}
