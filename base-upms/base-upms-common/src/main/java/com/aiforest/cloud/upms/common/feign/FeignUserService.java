package com.aiforest.cloud.upms.common.feign;

import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.upms.common.dto.UserInfo;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author
 */
@FeignClient(contextId = "feignUserService", value = ServiceNameConstants.UMPS_ADMIN_SERVICE)
public interface FeignUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 手机号查询用户、角色信息
	 *
	 * @param phone
	 * @param from  调用标志
	 * @return
	 */
	@GetMapping("/phone/info/{phone}")
	R<UserInfo> infoByPhone(@PathVariable("phone") String phone
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级机构的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	R<List<SysUser>> ancestorUsers(@PathVariable("username") String username);
}
