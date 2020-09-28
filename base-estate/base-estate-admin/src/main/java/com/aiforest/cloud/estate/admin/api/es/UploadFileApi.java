package com.aiforest.cloud.estate.admin.api.es;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.FileUtils;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.core.util.WaterMarkUtils;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.storage.util.UploadFileUtils;
import com.aiforest.cloud.estate.common.feign.FeignSysConfigStorageService;
import com.aiforest.cloud.upms.common.entity.SysConfigStorage;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * @author way
 * @date 2019/07/14
 * 文件上传
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/file")
@Api(value = "file", tags = "文件上传")
public class UploadFileApi {

	private final FeignSysConfigStorageService feignSysConfigStorageService;

	/**
	 * 上传文件
	 * @param mulFile
	 * @param dir 文件存放目录
	 * @param fileType 文件类型 image:图片
	 * @return
	 */
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile mulFile,
							 @RequestParam("dir") String dir,
							 @RequestParam("fileType") String fileType) throws Exception {
//		R checkThirdSession = BaseApi.checkThirdSession(null, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession.toString();
//		}

		File file = FileUtils.multipartFileToFile(mulFile);
		Map<Object, Object> responseData = new HashMap<>();
		dir = StrUtil.format("{}/{}", TenantContextHolder.getTenantId(),  dir);
		R r = feignSysConfigStorageService.getInside(SecurityConstants.FROM_IN);
		Map map = (Map<Object, Object>)r.getData();
		if(r == null){
			throw new RuntimeException("请先配置文件存储信息");
		}
		if(CommonConstants.FILE_TYPE_IMG.equals(fileType) &&
			StrUtil.isNotBlank(map.get("waterMarkContent")!=null ? String.valueOf(map.get("waterMarkContent")) : null)){//图片添加水印
			//添加水印
			file = WaterMarkUtils.markStr(file, Color.GRAY, map.get("waterMarkContent")!=null ? String.valueOf(map.get("waterMarkContent")) : null);
		}

		SysConfigStorage sysConfigStorage = new SysConfigStorage();
		sysConfigStorage.setAccessKeyId(map.get("accessKeyId")!=null ? String.valueOf(map.get("accessKeyId")) : null);
		sysConfigStorage.setStorageType(map.get("storageType")!=null ? String.valueOf(map.get("storageType")) : null);
		sysConfigStorage.setBucket(map.get("bucket")!=null ? String.valueOf(map.get("bucket")) : null);
		sysConfigStorage.setAccessKeySecret(map.get("accessKeySecret")!=null ? String.valueOf(map.get("accessKeySecret")) : null);
		sysConfigStorage.setEndpoint(map.get("endpoint")!=null ? String.valueOf(map.get("endpoint")) : null);
		responseData.put("link", UploadFileUtils.uploadFile(file,dir,sysConfigStorage));
		return JSONUtil.toJsonStr(responseData);
	}
}
