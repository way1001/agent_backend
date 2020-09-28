/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.estate.common.entity.Likes;
import com.aiforest.cloud.estate.admin.service.LikesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产提问
 *
 * @author way
 * @date 2020-04-13 11:15:42
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/likes")
@Api(value = "likes", tags = "房产提问管理")
public class LikesController {

    private final LikesService likesService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param likes 房产提问
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('estate_likes_index')")
    public R getPage(Page page, Likes likes) {
        return R.ok(likesService.page(page, Wrappers.query(likes)));
    }

    /**
     * 房产提问查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_likes_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(likesService.getById(id));
    }

    /**
     * 房产提问新增
     * @param likes 房产提问
     * @return R
     */
    @SysLog("新增房产提问")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_likes_add')")
    public R save(@RequestBody Likes likes) {
        return R.ok(likesService.save(likes));
    }

    /**
     * 房产提问修改
     * @param likes 房产提问
     * @return R
     */
    @SysLog("修改房产提问")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_likes_edit')")
    public R updateById(@RequestBody Likes likes) {
        return R.ok(likesService.updateById(likes));
    }

    /**
     * 房产提问删除
     * @param id
     * @return R
     */
    @SysLog("删除房产提问")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_likes_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(likesService.removeById(id));
    }

}
