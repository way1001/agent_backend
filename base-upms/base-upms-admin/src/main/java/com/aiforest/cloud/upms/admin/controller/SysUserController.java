package com.aiforest.cloud.upms.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.upms.admin.service.*;
import com.aiforest.cloud.upms.common.dto.UserDTO;
import com.aiforest.cloud.upms.common.dto.UserInfo;
import com.aiforest.cloud.upms.common.dto.UserRegister;
import com.aiforest.cloud.upms.common.entity.SysRole;
import com.aiforest.cloud.upms.common.entity.SysTenant;
import com.aiforest.cloud.upms.common.entity.SysUser;
import com.aiforest.cloud.common.core.constant.CacheConstants;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.common.security.util.SecurityUtils;
import com.aiforest.cloud.upms.common.entity.SysUserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class SysUserController {
	private final SysUserService sysUserService;
	private final SysRoleService sysRoleService;
	private final SysUserRoleService sysUserRoleService;
	private final RedisTemplate redisTemplate;
	private final SysTenantService sysTenantService;
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@ApiOperation(value = "获取当前用户全部信息")
	@GetMapping(value = {"/info"})
	public R info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return R.failed(null, "获取当前用户信息失败");
		}
		return R.ok(sysUserService.findUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@ApiOperation(value = "获取指定用户全部信息")
	@Inside
	@GetMapping("/info/{username}")
	public R info(@PathVariable String username) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser = sysUserService.getByNoTenant(sysUser);
		if (sysUser == null) {
			return R.failed(null, String.format("用户信息为空 %s", username));
		}
		TenantContextHolder.setTenantId(sysUser.getTenantId());
		if(CommonConstants.STATUS_NORMAL.equals(sysUser.getLockFlag())){
			//查询所属租户状态是否正常，否则禁止登录
			SysTenant sysTenant = sysTenantService.getById(sysUser.getTenantId());
			if(CommonConstants.STATUS_LOCK.equals(sysTenant.getStatus())){
				sysUser.setLockFlag(CommonConstants.STATUS_LOCK);
			}
		}
		UserInfo userInfo = sysUserService.findUserInfo(sysUser);
		return R.ok(userInfo);
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@ApiOperation(value = "通过ID查询用户信息")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:user:get')")
	public R user(@PathVariable String id) {
		return R.ok(sysUserService.selectUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@ApiOperation(value = "根据用户名查询用户信息")
	@GetMapping("/detail/{username}")
	public R userByUsername(@PathVariable String username) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		return R.ok(sysUserService.getByNoTenant(sysUser));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:user:del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
	public R userDel(@PathVariable String id) {
		//拥有管理员角色的用户不让删除
		SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>update().lambda()
				.eq(SysRole::getRoleCode,CommonConstants.ROLE_CODE_ADMIN));
		List<SysUserRole> listSysUserRole = sysUserRoleService.list(Wrappers.<SysUserRole>update().lambda()
				.eq(SysUserRole::getUserId,id).eq(SysUserRole::getRoleId,sysRole.getId()));
		if(listSysUserRole.size()>0){
			return R.failed("无法删除拥有管理员角色的用户");
		}
		SysUser sysUser = sysUserService.getById(id);
		return R.ok(sysUserService.deleteUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return ok/false
	 */
	@ApiOperation(value = "添加用户")
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('sys:user:add')")
	public R user(@RequestBody UserDTO userDto) {
		try{
			return R.ok(sysUserService.saveUser(userDto));
		}catch (DuplicateKeyException e){
			return judeParm(e);
		}
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@ApiOperation(value = "更新用户信息")
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('sys:user:edit')")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
		try{
			//查询出管理员角色，判断管理员角色是否至少有1个用户
			SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>update().lambda()
					.eq(SysRole::getRoleCode,CommonConstants.ROLE_CODE_ADMIN));
			if(!CollUtil.contains(userDto.getRoleIds(),sysRole.getId())){
				List<SysUserRole> listSysUserRole = sysUserRoleService.list(Wrappers.<SysUserRole>update().lambda()
						.eq(SysUserRole::getRoleId,sysRole.getId()));
				if(listSysUserRole.size()<=1){//只有一条记录，判断是否当前用户拥有
					listSysUserRole = sysUserRoleService.list(Wrappers.<SysUserRole>update().lambda()
							.eq(SysUserRole::getRoleId,sysRole.getId())
							.eq(SysUserRole::getUserId,userDto.getId()));
					if(listSysUserRole.size()>0){
						return R.failed("至少需要一个用户拥有管理员角色");
					}
				}
			}
			return R.ok(sysUserService.updateUser(userDto));
		}catch (DuplicateKeyException e){
			return judeParm(e);
		}
	}

	/**
	 * 修改用户密码
	 * @param userDto
	 * @return
	 */
	@ApiOperation(value = "修改用户密码")
	@SysLog("修改用户密码")
	@PutMapping("/password")
	@PreAuthorize("@ato.hasAuthority('sys:user:password')")
	public R editPassword(@Valid @RequestBody UserDTO userDto) {
		SysUser sysUser = new SysUser();
		sysUser.setId(userDto.getId());
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		sysUserService.updateById(sysUser);
		return R.ok();
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('sys:user:index')")
	public R getUserPage(Page page, UserDTO userDTO) {
		return R.ok(sysUserService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 数量查询
	 * @param sysUser
	 * @return
	 */
	@ApiOperation(value = "数量查询")
	@GetMapping("/count")
	@PreAuthorize("@ato.hasAuthority('sys:user:index')")
	public R getCount(SysUser sysUser) {
		return R.ok(sysUserService.count(Wrappers.query(sysUser)));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return ok/false
	 */
	@ApiOperation(value = "修改个人信息")
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return R.ok(sysUserService.updateUserInfo(userDto));
	}

	/**
	 * 绑定/解绑手机号
	 *
	 * @param userDto userDto
	 * @return
	 */
	@ApiOperation(value = "绑定/解绑手机号")
	@SysLog("绑定/解绑手机号")
	@PutMapping("/phone")
	public R bindPhone(@RequestBody UserDTO userDto) {
		//校验验证码
		if(StrUtil.isBlank(userDto.getCode())){
			return R.failed("验证码不能为空");
		}
		String key = CacheConstants.VER_CODE_DEFAULT + SecurityConstants.SMS_LOGIN + ":" + userDto.getPhone();
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		if (!redisTemplate.hasKey(key)) {
			return R.failed("验证码错误");
		}
		Object codeObj = redisTemplate.opsForValue().get(key);
		if (codeObj == null) {
			return R.failed("验证码不合法");
		}
		String saveCode = codeObj.toString();
		if (StrUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			return R.failed("验证码不合法");
		}
		if (!StrUtil.equals(saveCode, userDto.getCode())) {
			return R.failed("验证码不合法");
		}
		sysUserService.bindPhone(userDto);
		return R.ok();
	}

	/**
	 * @param username 用户名称
	 * @return 上级机构用户列表
	 */
	@ApiOperation(value = "查询")
	@GetMapping("/ancestor/{username}")
	public R listAncestorUsers(@PathVariable String username) {
		return R.ok(sysUserService.listAncestorUsers(username));
	}

	/**
	 * 自助注册
	 * @param userRegister
	 * @return
	 */
	@ApiOperation(value = "自助注册")
	@PostMapping("/register")
	public R register(@RequestBody UserRegister userRegister){
		//校验验证码
		String key = CacheConstants.VER_CODE_REGISTER + CommonConstants.EMAIL + ":" + userRegister.getEmail();
		Object codeObj = redisTemplate.opsForValue().get(key);
		if(codeObj == null || !codeObj.equals(userRegister.getCode())){
			return R.failed("验证码错误");
		}
		try{
			return sysUserService.register(userRegister);
		}catch (DuplicateKeyException e){
			return judeParm(e);
		}
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	R judeParm(DuplicateKeyException e){
		if(e.getMessage().contains("uk_username")){
			return R.failed("用户名已被占用");
		}else if(e.getMessage().contains("uk_email")){
			return R.failed("邮箱已被占用");
		}else{
			return R.failed(e.getMessage());
		}
	}
}
