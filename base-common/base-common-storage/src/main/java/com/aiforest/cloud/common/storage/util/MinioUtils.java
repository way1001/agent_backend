package com.aiforest.cloud.common.storage.util;

import com.aiforest.cloud.common.storage.entity.StorageConfig;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.xmlpull.v1.XmlPullParserException;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @JL
 * ali OSS
 * 各操作调用
 */
@AllArgsConstructor
public class MinioUtils {
	private final StorageConfig storageConfig;

	/**
	 * 上传文件
	 * @param file
	 * @param dir 用户上传文件时指定的文件夹。
	 */
	public String uploadFile(File file,String dir) throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidArgumentException {
		MinioClient minioClient = new MinioClient(storageConfig.getEndpoint(), storageConfig.getAccessKeyId(), storageConfig.getAccessKeySecret());
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String key = dir + UUID.randomUUID()+ "." + suffix;
		minioClient.putObject(storageConfig.getBucket(), key, new FileInputStream(file), file.length(), null, null, new MimetypesFileTypeMap().getContentType(file));
		String resultStr = minioClient.getObjectUrl(storageConfig.getBucket(), key);
		return resultStr;
	}
}
