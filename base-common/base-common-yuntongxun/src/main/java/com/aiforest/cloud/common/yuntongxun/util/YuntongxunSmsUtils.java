package com.aiforest.cloud.common.yuntongxun.util;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.aiforest.cloud.common.yuntongxun.config.YuntongxunConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Set;

@Slf4j
@Configuration
@AllArgsConstructor
public class YuntongxunSmsUtils {

	private final YuntongxunConfigProperties yuntongxunConfigProperties;

	/**
	 * 发送短信
	 * @param phoneNumbers
	 * @param templateCode
	 * @param templateParam
	 */
	public HashMap<String, Object> sendYuntongxunSms(String phoneNumbers, String templateCode, String[] templateParam) {
		CCPRestSmsSDK sdk = new CCPRestSmsSDK();

		sdk.init("app.cloopen.com", "8883");
		sdk.setAccount(yuntongxunConfigProperties.getAccountSId(), yuntongxunConfigProperties.getAccountToken());
		sdk.setAppId(yuntongxunConfigProperties.getAppId());
		sdk.setBodyType(BodyType.Type_JSON);
		return sdk.sendTemplateSMS(phoneNumbers,templateCode,templateParam);
//		if("000000".equals(result.get("statusCode"))){
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				System.out.println(key +" = "+object);
//			}
//		}else{
//			//异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//			throw new RuntimeException("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//		}

	}
}
