package com.aiforest.cloud.mall.common.feign;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendDTO;

/**
 * @author JL
 */
@FeignClient(contextId = "feignWxTemplateMsgService", value = ServiceNameConstants.WX_ADMIN_SERVICE)
public interface FeignWxTemplateMsgService {

	@PostMapping("/wxtemplatemsg/send")
	R sendTemplateMsg(@RequestBody WxTemplateMsgSendDTO wxTemplateMsgSendDTO, @RequestHeader(SecurityConstants.FROM) String from);

}
