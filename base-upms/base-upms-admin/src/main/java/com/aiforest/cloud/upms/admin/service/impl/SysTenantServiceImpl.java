package com.aiforest.cloud.upms.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.upms.admin.mapper.SysTenantMapper;
import com.aiforest.cloud.upms.admin.service.SysOrganRelationService;
import com.aiforest.cloud.upms.admin.service.SysRoleService;
import com.aiforest.cloud.upms.admin.service.SysTenantService;
import com.aiforest.cloud.upms.admin.service.SysUserRoleService;
import com.aiforest.cloud.upms.admin.service.SysUserService;
import com.aiforest.cloud.upms.common.entity.SysOrgan;
import com.aiforest.cloud.upms.common.entity.SysRole;
import com.aiforest.cloud.upms.common.entity.SysTenant;
import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.upms.common.entity.SysUserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 租户管理
 *
 * @author
 */
@Service
@AllArgsConstructor
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

	private final SysOrganRelationService sysOrganRelationService;
	private final SysUserService sysUserService;
	private final SysRoleService sysRoleService;
	private final SysUserRoleService sysUserRoleService;
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SysTenant entity) {
		baseMapper.insert(entity);
		//新建机构关联
		SysOrgan sysOrgan = new SysOrgan();
		BeanUtil.copyProperties(entity,sysOrgan);
		sysOrganRelationService.insertOrganRelation(sysOrgan);
		//新建用户
		SysUser sysUser = new SysUser();
		sysUser.setOrganId(sysOrgan.getId());
		sysUser.setUsername(entity.getUsername());
		sysUser.setPassword(ENCODER.encode(entity.getPassword()));
		sysUserService.save(sysUser);
		//新建角色
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("管理员");
		sysRole.setRoleCode(CommonConstants.ROLE_CODE_ADMIN);
		sysRole.setRoleDesc(entity.getName()+"管理员");
		sysRole.setDsType(CommonConstants.DS_TYPE_0);
		sysRoleService.save(sysRole);
		//新建用户角色
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(sysRole.getId());
		sysUserRole.setUserId(sysUser.getId());
		sysUserRoleService.save(sysUserRole);
		return Boolean.TRUE;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean removeById(Serializable id) {
		baseMapper.deleteSysTenantById(id);
		return Boolean.TRUE;
	}
}
