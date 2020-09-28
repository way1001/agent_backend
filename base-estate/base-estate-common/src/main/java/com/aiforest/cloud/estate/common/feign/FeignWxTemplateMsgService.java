package com.aiforest.cloud.estate.common.feign;

import com.aiforest.cloud.common.core.constant.SecurityConstants;
import com.aiforest.cloud.common.core.constant.ServiceNameConstants;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.common.dto.WxTemplateMsgSendEsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author way
 */
@FeignClient(contextId = "feignWxTemplateMsgService", value = ServiceNameConstants.WX_ADMIN_SERVICE)
public interface FeignWxTemplateMsgService {

	@PostMapping("/wxtemplatemsg/sendes")
	R sendTemplateMsg(@RequestBody WxTemplateMsgSendEsDTO wxTemplateMsgSendEsDTO, @RequestHeader(SecurityConstants.FROM) String from);

}
