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
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.entity.AskQuestions;
import com.aiforest.cloud.estate.admin.service.AskQuestionsService;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

/**
 * 房产提问
 *
 * @author way
 * @date 2020-04-13 11:15:48
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/askquestions")
@Api(value = "askquestions", tags = "房产提问管理")
public class AskQuestionsController {

    private final AskQuestionsService askQuestionsService;
	private final UserInfoService userInfoService;

	/**
     * 分页列表
     * @param page 分页对象
     * @param askQuestions 房产提问
     * @return
     */
//    @GetMapping("/page")
//    @PreAuthorize("@ato.hasAuthority('estate_askquestions_index')")
//    public R getPage(Page page, AskQuestions askQuestions) {
//        return R.ok(askQuestionsService.page(page, Wrappers.query(askQuestions)));
//    }
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('estate_askquestions_index')")
	public R getPage(Page page, AskQuestions askQuestions, UserInfo userInfo) {
		if(userInfo.getUserCode() != null && userInfo.getUserCode()!= 0){
			int userCode = userInfo.getUserCode();
			userInfo = userInfoService.getOne(Wrappers.<UserInfo>query()
					.lambda().eq(UserInfo::getUserCode, userCode));
			if(userInfo != null){
				askQuestions.setCreateId(userInfo.getId());
			}else{
				askQuestions.setCreateId(String.valueOf(userCode));
			}
		}
		return R.ok(askQuestionsService.page2(page, askQuestions));
	}

    /**
     * 房产提问查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_askquestions_get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(askQuestionsService.getById(id));
    }

    /**
     * 房产提问新增
     * @param askQuestions 房产提问
     * @return R
     */
    @SysLog("新增房产提问")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('estate_askquestions_add')")
    public R save(@RequestBody AskQuestions askQuestions) {
        return R.ok(askQuestionsService.save(askQuestions));
    }

    /**
     * 房产提问修改
     * @param askQuestions 房产提问
     * @return R
     */
    @SysLog("修改房产提问")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('estate_askquestions_edit')")
    public R updateById(@RequestBody AskQuestions askQuestions) {
        return R.ok(askQuestionsService.updateById(askQuestions));
    }

    /**
     * 房产提问删除
     * @param id
     * @return R
     */
    @SysLog("删除房产提问")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('estate_askquestions_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(askQuestionsService.removeById(id));
    }

}
