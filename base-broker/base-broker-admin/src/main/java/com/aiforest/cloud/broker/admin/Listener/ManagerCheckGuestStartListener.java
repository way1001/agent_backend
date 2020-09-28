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

import java.util.List;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class ManagerCheckGuestStartListener implements TaskListener {

	private final UserInfoService userInfoService;
	private final VariableDefinitionService variableDefinitionService;
	private final ReferralsService referralsService;


//	@Override
//	public void notify(DelegateExecution delegateExecution) throws Exception {
//
//		LOGGER.info("\n\n ...LogeerDelegate invoked by"
//				+ "processDefinitionId=" + delegateExecution.getProcessDefinitionId()
//				+ "activityId=" + delegateExecution.getCurrentActivityId()
//				+ "activityName=" + delegateExecution.getCurrentActivityName()
//				+ "processInstanceId=" + delegateExecution.getProcessInstanceId()
//				+ "processBusinessKey=" + delegateExecution.getProcessBusinessKey()
//				+ "delegateExecutionId=" + delegateExecution.getId()
//				+ "\n\n");
//	}

	@Override
	public void notify(DelegateTask delegateTask) {

		String affiliationId = (String)delegateTask.getVariable("affiliationId");
		String tenantId = (String)delegateTask.getVariable("tenantId");


		VariableDefinition userRolesDefinition = variableDefinitionService.getOne(Wrappers.<VariableDefinition>lambdaQuery()
				.eq(VariableDefinition::getDefinitionId, delegateTask.getProcessDefinitionId())
				.eq(VariableDefinition::getVariableId, "distinguishRoles"));

		UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
				.eq(UserInfo::getTenantId,tenantId)
				.eq(UserInfo::getUserRole, userRolesDefinition.getVariableString())
				.like(UserInfo::getAffiliated, affiliationId));


		if (userInfo == null) return;

		Referrals referrals = referralsService.getOne(Wrappers.<Referrals>lambdaQuery()
				.eq(Referrals::getInstanceId, delegateTask.getProcessInstanceId()));
		referrals.setCurrentHandler(delegateTask.getName());
		referrals.setStaffId(userInfo.getId());

		referralsService.updateById(referrals);

		delegateTask.setAssignee(userInfo.getId());

	}
}
