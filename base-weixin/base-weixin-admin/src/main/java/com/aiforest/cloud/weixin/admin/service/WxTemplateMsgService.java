/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendDTO;
import com.aiforest.cloud.weixin.common.entity.WxTemplateMsg;
import com.aiforest.cloud.weixin.common.entity.WxUser;

/**
 * 微信模板/订阅消息
 *
 * @author JL
 * @date 2020-04-16 17:30:03
 */
public interface WxTemplateMsgService extends IService<WxTemplateMsg> {

	/**
	 * 发送微信订阅消息
	 * @param wxTemplateMsgSendDTO
	 * @param wxUser
	 */
	void sendWxTemplateMsg(WxTemplateMsgSendDTO wxTemplateMsgSendDTO, WxUser wxUser);
}
