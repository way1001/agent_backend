/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.weixin.admin.service.WxAutoReplyService;
import com.aiforest.cloud.weixin.common.entity.WxAutoReply;
import com.aiforest.cloud.weixin.admin.mapper.WxAutoReplyMapper;
import org.springframework.stereotype.Service;

/**
 * 消息自动回复
 *
 * @author JL
 * @date 2019-04-18 15:40:39
 */
@Service
public class WxAutoReplyServiceImpl extends ServiceImpl<WxAutoReplyMapper, WxAutoReply> implements WxAutoReplyService {

}
