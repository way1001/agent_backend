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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.BargainCutService;
import com.aiforest.cloud.mall.admin.service.BargainInfoService;
import com.aiforest.cloud.mall.common.entity.BargainCut;
import com.aiforest.cloud.mall.common.entity.BargainInfo;
import com.aiforest.cloud.mall.common.entity.BargainUser;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 砍价
 *
 * @author JL
 * @date 2019-12-28 18:07:51
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/bargaininfo")
@Api(value = "bargaininfo", tags = "砍价Api")
public class BargainInfoApi {

    private final BargainInfoService bargainInfoService;
	private final BargainCutService bargainCutService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param bargainInfo 砍价
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R getPage(Page page, BargainInfo bargainInfo) {
        return R.ok(bargainInfoService.page2(page, bargainInfo));
    }

    /**
     * 砍价查询
     * @param bargainUser
     * @return R
     */
	@ApiOperation(value = "砍价查询")
    @GetMapping
    public R get(BargainUser bargainUser) {
		bargainUser.setUserId(ThirdSessionHolder.getMallUserId());
		BargainInfo bargainInfo = bargainInfoService.getOne2(bargainUser);
		if(bargainInfo.getBargainUser() != null){
			//获取已砍金额
			bargainInfo.getBargainUser().setHavBargainAmount(bargainCutService.getTotalCutPrice(bargainInfo.getBargainUser().getId()));
			//获取当前用户的砍价信息
			BargainCut bargainCut = bargainCutService.getOne(Wrappers.<BargainCut>lambdaQuery()
					.eq(BargainCut::getBargainUserId,bargainInfo.getBargainUser().getId())
					.eq(BargainCut::getUserId,bargainUser.getUserId()));
			bargainInfo.getBargainUser().setBargainCut(bargainCut);
		}
        return R.ok(bargainInfo);
    }

}
