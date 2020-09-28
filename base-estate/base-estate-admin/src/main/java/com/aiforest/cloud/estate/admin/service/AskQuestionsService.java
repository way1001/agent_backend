/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.estate.common.dto.AuditDTO;
import com.aiforest.cloud.estate.common.entity.AskQuestions;

/**
 * 房产提问
 *
 * @author way
 * @date 2020-04-13 11:15:48
 */
public interface AskQuestionsService extends IService<AskQuestions> {

	IPage<AskQuestions> page1(IPage<AskQuestions> page, AuditDTO auditDTO);

	IPage<AskQuestions> page2(IPage<AskQuestions> page, AskQuestions askQuestions);

}
