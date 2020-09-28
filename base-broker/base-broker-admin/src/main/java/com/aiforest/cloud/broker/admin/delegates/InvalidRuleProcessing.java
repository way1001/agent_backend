package com.aiforest.cloud.broker.admin.delegates;

import com.aiforest.cloud.broker.admin.service.ReferralsService;
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class InvalidRuleProcessing implements JavaDelegate {

	private final ReferralsService referralsService;

//	private final Logger LOGGER = Logger.getLogger(InvalidRuleProcessing.class.getName());

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		 // send a messager to manager

	}

}
