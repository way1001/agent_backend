package com.aiforest.cloud.estate.common.feign;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.common.dto.WxOpenDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 */
@FeignClient(contextId = "feignSysConfigStorageService", value = ServiceNameConstants.UMPS_ADMIN_SERVICE)
public interface FeignSysConfigStorageService {

	/**
	 * 上传
	 *
	 * @return
	 */

//	@PostMapping("/upload")
//	String uploadFile(@RequestParam("file") MultipartFile mulFile,
//							 @RequestParam("dir") String dir,
//							 @RequestParam("fileType") String fileType, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/configstorage/inside")
	R getInside(@RequestHeader(SecurityConstants.FROM) String from);
}
