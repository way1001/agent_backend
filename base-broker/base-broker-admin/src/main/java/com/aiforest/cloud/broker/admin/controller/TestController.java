package com.aiforest.cloud.broker.admin.controller;

import com.aiforest.cloud.broker.admin.service.ReloadDroolsRules;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

	private ReloadDroolsRules rules;

	@GetMapping("/address")
	public void test(){
//		KieSession kieSession = KieUtils.getKieSession();
//
//		SubprocessDefinition subprocessDefinition = new SubprocessDefinition();
//		subprocessDefinition.setId("123456");
//
//		ProcessRuleCheckResult result = new ProcessRuleCheckResult();
//		kieSession.insert(subprocessDefinition);
//		kieSession.insert(result);
//		int ruleFiredCount = kieSession.fireAllRules();
//		System.out.println("触发了" + ruleFiredCount + "条规则");
//
//		if(result.isPostCodeResult()){
//			System.out.println("规则校验通过");
//		}

	}


	@GetMapping("/reload")
	public String reload() throws IOException {
		rules.reload();
		return "ok";
	}
}

