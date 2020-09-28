/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.api.ma;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.admin.service.WxTemplateMsgService;
import com.aiforest.cloud.weixin.common.entity.WxTemplateMsg;
import com.aiforest.cloud.weixin.common.util.WxMaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信模板/订阅消息
 *
 * @author JL
 * @date 2020-04-16 17:30:03
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/wxtemplatemsg")
@Api(value = "wxtemplatemsg", tags = "微信模板/订阅消息管理API")
public class WxTemplateMsgApi {

    private final WxTemplateMsgService wxTemplateMsgService;

    /**
     * list列表，可指定useType
     * @param wxTemplateMsg 微信模板/订阅消息
     * @return
     */
    @ApiOperation(value = "list列表")
    @PostMapping("/list")
    public R getList(HttpServletRequest request, @RequestBody WxTemplateMsg wxTemplateMsg) {
		String wxAppId = WxMaUtil.getAppId(request);
		wxTemplateMsg.setAppId(wxAppId);
        return R.ok(wxTemplateMsgService.list(Wrappers.query(wxTemplateMsg).lambda()
				.in(WxTemplateMsg::getUseType,wxTemplateMsg.getUseTypeList())));
    }

}
