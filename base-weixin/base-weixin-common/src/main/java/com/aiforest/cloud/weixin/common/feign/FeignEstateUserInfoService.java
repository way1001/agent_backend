package com.aiforest.cloud.weixin.common.feign;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.common.dto.EstateUserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
@FeignClient(contextId = "feignEstateUserInfoService", value = ServiceNameConstants.ESTATE_ADMIN_SERVICE)
public interface FeignEstateUserInfoService {

	/**
	 * 新增商城用户
	 * @param estateUserInfoDTO
	 * @param from
	 * @return
	 */
	@PostMapping("/userinfo/inside")
	R saveInside(@RequestBody EstateUserInfoDTO estateUserInfoDTO, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/userinfo/inside/{id}")
	R getByIdInside(@PathVariable("id") String id, @RequestHeader(SecurityConstants.FROM) String from);
}
