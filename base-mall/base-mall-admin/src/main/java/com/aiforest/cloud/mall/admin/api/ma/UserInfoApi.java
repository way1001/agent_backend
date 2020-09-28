/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.CouponUserService;
import com.aiforest.cloud.mall.admin.service.UserInfoService;
import com.aiforest.cloud.mall.common.entity.CouponUser;
import com.aiforest.cloud.mall.common.entity.ShoppingCart;
import com.aiforest.cloud.mall.common.entity.UserInfo;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 商城用户
 *
 * @author JL
 * @date 2019-12-04 11:09:55
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/userinfo")
@Api(value = "userinfo", tags = "商城用户API")
public class UserInfoApi {

    private final UserInfoService userInfoService;
	private final CouponUserService couponUserService;

    /**
     * 查询商城用户
     * @return R
     */
	@ApiOperation(value = "查询商城用户")
    @GetMapping
    public R getById(HttpServletRequest request) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(ThirdSessionHolder.getMallUserId());
		int couponNum = couponUserService.count(Wrappers.<CouponUser>lambdaQuery()
				.eq(CouponUser :: getUserId, userInfo.getId())
				.eq(CouponUser :: getStatus, CommonConstants.NO)
				.gt(CouponUser :: getValidEndTime, LocalDateTime.now()));
		userInfo = userInfoService.getById(userInfo.getId());
		userInfo.setCouponNum(couponNum);
        return R.ok(userInfo);
    }
}
