package com.aiforest.cloud.common.storage.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aiforest.cloud.common.storage.entity.StorageConfig;
import lombok.AllArgsConstructor;
import java.io.File;
import java.util.UUID;

/**
 * @JL
 * ali OSS
 * 各操作调用
 */
@AllArgsConstructor
public class AliOssUtils {
	private final StorageConfig storageConfig;

	/**
	 * 上传文件
	 * @param file
	 * @param dir 用户上传文件时指定的文件夹。
	 */
	public String uploadFile(File file,String dir) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(storageConfig.getEndpoint(), storageConfig.getAccessKeyId(), storageConfig.getAccessKeySecret());

		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String key = dir + UUID.randomUUID()+ "." + suffix;
		// 创建PutObjectRequest对象。
		PutObjectRequest putObjectRequest = new PutObjectRequest(storageConfig.getBucket(), key, file);
		// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
		// ObjectMetadata metadata = new ObjectMetadata();
		// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
		// metadata.setObjectAcl(CannedAccessControlList.Private);
		// putObjectRequest.setMetadata(metadata);

		// 上传文件。
		ossClient.putObject(putObjectRequest);

		// 关闭OSSClient。
		ossClient.shutdown();
		// 解析结果
		String resultStr = "https://" + storageConfig.getBucket() + "." + storageConfig.getEndpoint() + "/" + key;
		return resultStr;
	}
}
