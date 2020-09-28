package com.aiforest.cloud.broker.admin.Listener;

import com.aiforest.cloud.broker.admin.service.ReferralsService;
import com.aiforest.cloud.broker.admin.service.UserInfoService;
import com.aiforest.cloud.broker.admin.service.VariableDefinitionService;
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.broker.common.entity.VariableDefinition;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FollowUpMattersStartListenner implements TaskListener {

	private final ReferralsService referralsService;

	@Override
	public void notify(DelegateTask delegateTask) {

		String agentId = (String)delegateTask.getVariable("agentId");

		Referrals referrals = referralsService.getOne(Wrappers.<Referrals>lambdaQuery()
				.eq(Referrals::getInstanceId, delegateTask.getProcessInstanceId()));
		referrals.setCurrentHandler(delegateTask.getName());
		referrals.setStaffId(agentId);

		referralsService.updateById(referrals);
	}
}