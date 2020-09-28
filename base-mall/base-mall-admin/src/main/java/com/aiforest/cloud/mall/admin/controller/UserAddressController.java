/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.mall.common.entity.UserAddress;
import com.aiforest.cloud.mall.admin.service.UserAddressService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 用户收货地址
 *
 * @author JL
 * @date 2019-09-11 14:28:59
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/useraddress")
@Api(value = "useraddress", tags = "用户收货地址管理")
public class UserAddressController {

    private final UserAddressService userAddressService;

    /**
    * 分页查询
    * @param page 分页对象
    * @param userAddress 用户收货地址
    * @return
    */
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('mall:useraddress:index')")
    public R getUserAddressPage(Page page, UserAddress userAddress) {
        return R.ok(userAddressService.page(page,Wrappers.query(userAddress)));
    }

    /**
    * 通过id查询用户收货地址
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询用户收货地址")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:useraddress:get')")
    public R getById(@PathVariable("id") String id){
        return R.ok(userAddressService.getById(id));
    }

    /**
    * 新增用户收货地址
    * @param userAddress 用户收货地址
    * @return R
    */
	@ApiOperation(value = "新增用户收货地址")
    @SysLog("新增用户收货地址")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('mall:useraddress:add')")
    public R save(@RequestBody UserAddress userAddress){
        return R.ok(userAddressService.save(userAddress));
    }

    /**
    * 修改用户收货地址
    * @param userAddress 用户收货地址
    * @return R
    */
	@ApiOperation(value = "修改用户收货地址")
    @SysLog("修改用户收货地址")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('mall:useraddress:edit')")
    public R updateById(@RequestBody UserAddress userAddress){
        return R.ok(userAddressService.updateById(userAddress));
    }

    /**
    * 通过id删除用户收货地址
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id删除用户收货地址")
    @SysLog("删除用户收货地址")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('mall:useraddress:del')")
    public R removeById(@PathVariable String id){
        return R.ok(userAddressService.removeById(id));
    }

}
