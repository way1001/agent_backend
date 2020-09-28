/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.GrouponInfoService;
import com.aiforest.cloud.mall.common.entity.GrouponInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 拼团
 *
 * @author JL
 * @date 2020-03-17 11:55:32
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/grouponinfo")
@Api(value = "grouponinfo", tags = "拼团Api")
public class GrouponInfoApi {

    private final GrouponInfoService grouponInfoService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param grouponInfo 拼团
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R getPage(Page page, GrouponInfo grouponInfo) {
		return R.ok(grouponInfoService.page2(page, grouponInfo));
    }

    /**
     * 拼团查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "拼团查询")
    @GetMapping("/{id}")
    public R getById(HttpServletRequest request, @PathVariable("id") String id) {
		return R.ok(grouponInfoService.getById2(id));
    }

}
