/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.weixin.admin.service.WxUserService;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendDTO;
import com.aiforest.cloud.weixin.common.entity.WxTemplateMsg;
import com.aiforest.cloud.weixin.admin.service.WxTemplateMsgService;
import com.aiforest.cloud.weixin.common.entity.WxUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

/**
 * 微信模板/订阅消息
 *
 * @author JL
 * @date 2020-04-16 17:30:03
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxtemplatemsg")
@Api(value = "wxtemplatemsg", tags = "微信模板/订阅消息管理")
public class WxTemplateMsgController {

    private final WxTemplateMsgService wxTemplateMsgService;
	private final WxUserService wxUserService;

    /**
     * 分页列表
     * @param page 分页对象
     * @param wxTemplateMsg 微信模板/订阅消息
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ato.hasAuthority('wxma:wxtemplatemsg:index')")
    public R getPage(Page page, WxTemplateMsg wxTemplateMsg) {
        return R.ok(wxTemplateMsgService.page(page, Wrappers.query(wxTemplateMsg)));
    }

    /**
     * 微信模板/订阅消息查询
     * @param id
     * @return R
     */
    @ApiOperation(value = "微信模板/订阅消息查询")
    @GetMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('wxma:wxtemplatemsg:get')")
    public R getById(@PathVariable("id") String id) {
        return R.ok(wxTemplateMsgService.getById(id));
    }

    /**
     * 微信模板/订阅消息新增
     * @param wxTemplateMsg 微信模板/订阅消息
     * @return R
     */
    @ApiOperation(value = "微信模板/订阅消息新增")
    @SysLog("新增微信模板/订阅消息")
    @PostMapping
    @PreAuthorize("@ato.hasAuthority('wxma:wxtemplatemsg:add')")
    public R save(@RequestBody WxTemplateMsg wxTemplateMsg) {
		List<WxTemplateMsg> listWxTemplateMsg = wxTemplateMsgService.list(Wrappers.<WxTemplateMsg>lambdaQuery()
				.eq(WxTemplateMsg::getAppId,wxTemplateMsg.getAppId())
				.eq(WxTemplateMsg::getUseType,wxTemplateMsg.getUseType()));
		if(listWxTemplateMsg.size() > 0){
			return R.failed("该用途已有相应模板，请勿重复添加");
		}
        return R.ok(wxTemplateMsgService.save(wxTemplateMsg));
    }

    /**
     * 微信模板/订阅消息修改
     * @param wxTemplateMsg 微信模板/订阅消息
     * @return R
     */
    @ApiOperation(value = "微信模板/订阅消息修改")
    @SysLog("修改微信模板/订阅消息")
    @PutMapping
    @PreAuthorize("@ato.hasAuthority('wxma:wxtemplatemsg:edit')")
    public R updateById(@RequestBody WxTemplateMsg wxTemplateMsg) {
		List<WxTemplateMsg> listWxTemplateMsg = wxTemplateMsgService.list(Wrappers.<WxTemplateMsg>lambdaQuery()
				.eq(WxTemplateMsg::getAppId,wxTemplateMsg.getAppId())
				.eq(WxTemplateMsg::getUseType,wxTemplateMsg.getUseType()));
		if(listWxTemplateMsg.size() == 1){
			if(!listWxTemplateMsg.get(0).getId().equals(wxTemplateMsg.getId())){
				return R.failed("该用途已有相应模板，请勿重复添加");
			}
		}
        return R.ok(wxTemplateMsgService.updateById(wxTemplateMsg));
    }

    /**
     * 微信模板/订阅消息删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "微信模板/订阅消息删除")
    @SysLog("删除微信模板/订阅消息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ato.hasAuthority('wxma:wxtemplatemsg:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(wxTemplateMsgService.removeById(id));
    }

	/**
	 * 发送微信模板/订阅消息
	 * @param
	 * @return
	 */
	@Inside
	@ApiOperation(value = "发送微信模板/订阅消息")
	@PostMapping("/send")
	public R send(@RequestBody WxTemplateMsgSendDTO wxTemplateMsgSendDTO) {
		//查询出用户信息
		WxUser wxUser = wxUserService.getOne(Wrappers.<WxUser>lambdaQuery()
				.eq(WxUser::getMallUserId,wxTemplateMsgSendDTO.getMallUserId()));
		if(wxUser == null){
			return R.failed("用户不存在");
		}
		wxTemplateMsgService.sendWxTemplateMsg(wxTemplateMsgSendDTO, wxUser);
		return R.ok();
	}
}
