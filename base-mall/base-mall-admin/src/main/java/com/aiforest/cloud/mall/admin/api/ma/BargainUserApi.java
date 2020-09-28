/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.BargainUserService;
import com.aiforest.cloud.mall.common.constant.MyReturnCode;
import com.aiforest.cloud.mall.common.entity.BargainUser;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 砍价记录
 *
 * @author JL
 * @date 2019-12-30 11:53:14
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/bargainuser")
@Api(value = "bargainuser", tags = "砍价记录API")
public class BargainUserApi {

    private final BargainUserService bargainUserService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param bargainUser 砍价记录
     * @return
     */
	@ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R getPage(Page page, BargainUser bargainUser) {
		bargainUser.setUserId(ThirdSessionHolder.getMallUserId());
        return R.ok(bargainUserService.page2(page, bargainUser));
    }

    /**
     * 砍价记录查询
     * @param id
     * @return R
     */
	@ApiOperation(value = "砍价记录查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") String id) {
		return R.ok(bargainUserService.getById(id));
    }

    /**
     * 砍价记录新增，发起砍价
     * @param bargainUser 砍价记录
     * @return R
     */
	@ApiOperation(value = "砍价记录新增，发起砍价")
    @PostMapping
    public R save(@RequestBody BargainUser bargainUser) {
		bargainUser.setUserId(ThirdSessionHolder.getMallUserId());
		if(StrUtil.isBlank(bargainUser.getBargainId())){
			R.failed(MyReturnCode.ERR_80005.getCode(), MyReturnCode.ERR_80005.getMsg());
		}
        return R.ok(bargainUserService.saveBargain(bargainUser));
    }

    /**
     * 砍价记录修改
     * @param bargainUser 砍价记录
     * @return R
     */
	@ApiOperation(value = "砍价记录修改")
    @PutMapping
    public R updateById(@RequestBody BargainUser bargainUser) {
		bargainUser.setUserId(ThirdSessionHolder.getMallUserId());
        return R.ok(bargainUserService.updateById(bargainUser));
    }

    /**
     * 砍价记录删除
     * @param id
     * @return R
     */
	@ApiOperation(value = "砍价记录删除")
    @DeleteMapping("/{id}")
    public R removeById(HttpServletRequest request, @PathVariable String id) {
		return R.ok(bargainUserService.removeById(id));
    }

}
