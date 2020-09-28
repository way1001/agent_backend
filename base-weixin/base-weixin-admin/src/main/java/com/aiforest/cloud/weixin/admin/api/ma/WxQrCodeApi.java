/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.api.ma;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.weixin.admin.config.ma.WxMaConfiguration;
import com.aiforest.cloud.weixin.common.dto.MaQrCodeDTO;
import com.aiforest.cloud.weixin.common.util.WxMaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * 小程序码
 *
 * @author JL
 * @date 2019-10-25 15:39:39
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/wxqrcode")
@Api(value = "wxqrcode", tags = "小程序码API")
public class WxQrCodeApi {

	/**
	 * 生成小程序码
	 * @param request
	 * @param maQrCodeDTO
	 * @return
	 */
	@ApiOperation(value = "生成小程序码")
	@PostMapping("/unlimited")
	public R getUnlimited(HttpServletRequest request, @RequestBody MaQrCodeDTO maQrCodeDTO){
		try {
			String wxAppId = WxMaUtil.getAppId(request);
			File fileImg = WxMaConfiguration.getMaService(wxAppId).getQrcodeService()
					.createWxaCodeUnlimit(maQrCodeDTO.getScene(), maQrCodeDTO.getPage(),100, true, null, false);
			FileInputStream inputFile = new FileInputStream(fileImg);
			byte[] buffer = new byte[(int)fileImg.length()];
			inputFile.read(buffer);
			inputFile.close();
			String rs =  new String(Base64.getEncoder().encode(buffer));
			return R.ok(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}
}
