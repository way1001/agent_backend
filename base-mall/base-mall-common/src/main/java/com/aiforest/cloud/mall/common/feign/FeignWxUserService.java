package com.aiforest.cloud.mall.common.feign;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.common.dto.WxOpenDataDTO;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
@FeignClient(contextId = "feignWxUserService", value = ServiceNameConstants.WX_ADMIN_SERVICE)
public interface FeignWxUserService {

	/**
	 * 获取微信用户信息
	 * @param id
	 * @return
	 */
	@GetMapping("/wxuser/inside/{id}")
	R<WxUser> getById(@PathVariable("id") String id, @RequestHeader(SecurityConstants.FROM) String from);

	@PostMapping("/wxuser/inside")
	R<WxUser> save(@RequestBody WxOpenDataDTO wxOpenDataDTO, @RequestHeader(SecurityConstants.FROM) String from);
}
