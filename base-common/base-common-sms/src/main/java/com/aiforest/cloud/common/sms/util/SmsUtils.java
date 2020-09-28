package com.aiforest.cloud.common.sms.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aiforest.cloud.common.sms.config.SmsConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@Slf4j
@Configuration
@AllArgsConstructor
public class SmsUtils {

	private final SmsConfigProperties smsConfigProperties;

	/**
	 * 发送短信
	 * @param signName
	 * @param phoneNumbers
	 * @param templateCode
	 */
	public void sendSms(String signName, String phoneNumbers, String templateCode, String templateParam) throws ClientException {
		DefaultProfile profile = DefaultProfile.getProfile(smsConfigProperties.getRegionId(), smsConfigProperties.getAccessKeyId(), smsConfigProperties.getAccessKeySecret());
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("RegionId", smsConfigProperties.getRegionId());
		request.putQueryParameter("PhoneNumbers", phoneNumbers);
		request.putQueryParameter("SignName", signName);
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", templateParam);
		CommonResponse response = client.getCommonResponse(request);
		String data = response.getData();
		JSONObject dataJson = JSONUtil.parseObj(data);
		if(!"OK".equals(dataJson.get("Code"))){
			log.error("发送短信失败：",data);
			throw new RuntimeException("发送短信失败：" + dataJson.get("Message"));
		}
	}
}
