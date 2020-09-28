package com.aiforest.cloud.common.security.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.aiforest.cloud.common.security.entity.BaseUser;
import com.aiforest.cloud.common.security.service.BaseUserDetailsService;
import com.aiforest.cloud.upms.common.dto.UserInfo;
import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.upms.common.feign.FeignUserService;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.constant.CacheConstants;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author
 */
@Slf4j
@Service
@AllArgsConstructor
public class BaseUserDetailsServiceImpl implements BaseUserDetailsService {
	private final FeignUserService feignUserService;
	private final CacheManager cacheManager;

	/**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		//查询缓存中是否有此用户信息，有则直接返回
		Cache cache = cacheManager.getCache(CacheConstants.USER_CACHE);
		if (cache != null && cache.get(username) != null) {
			return (BaseUser) cache.get(username).get();
		}
		//缓存中无此用户信息，feign查询
		R<UserInfo> result = feignUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		if(userDetails.isAccountNonLocked()){
			//合法用户，放入缓存
			cache.put(username, userDetails);
		}
		return userDetails;
	}

	/**
	 * 手机验证码登录
	 *
	 * @param phone
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByPhone(String phone) {
		return getUserDetails(feignUserService.infoByPhone(phone, SecurityConstants.FROM_IN));
	}

	/**
	 * 构建userdetails
	 *
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(R<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
		}
		Collection<? extends GrantedAuthority> authorities
				= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
		// 构造security用户
		return new BaseUser(user.getId(), user.getOrganId(), user.getTenantId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
				true, true, CommonConstants.STATUS_NORMAL.equals(user.getLockFlag()), authorities);
	}
}
