package com.aiforest.cloud.broker.admin.delegates;

import com.aiforest.cloud.broker.admin.service.ReferralsService;
import com.aiforest.cloud.broker.admin.service.UserInfoService;
import com.aiforest.cloud.broker.admin.service.VariableDefinitionService;
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.broker.common.entity.VariableDefinition;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class SystemAllocateGuestDelegate  implements JavaDelegate {

	private final VariableDefinitionService variableDefinitionService;

	private final UserInfoService userInfoService;

//	private final Logger LOGGER = Logger.getLogger(SystemAllocateGuestDelegate.class.getName());


	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		Object recommendInfo = delegateExecution.getVariable("recommendInfo");


		String affiliationId = (String)delegateExecution.getVariable("affiliationId");
		String tenantId = (String)delegateExecution.getVariable("tenantId");
		String salesmanId = (String)delegateExecution.getVariable("salesmanId");



		VariableDefinition userRolesDefinition = variableDefinitionService.getOne(Wrappers.<VariableDefinition>lambdaQuery()
				.eq(VariableDefinition::getDefinitionId, delegateExecution.getProcessDefinitionId())
				.in(VariableDefinition::getVariableId, "allocatedRoles"));


		if (salesmanId != null) {
			UserInfo salesmanInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
					.eq(UserInfo::getId, salesmanId)
					.eq(UserInfo::getUserStatus, "0"));

			if (salesmanInfo == null) return;

			delegateExecution.setVariable("agentId",salesmanInfo.getId());
			return;
		}

		List<UserInfo> userInfoList = userInfoService.list(Wrappers.<UserInfo>lambdaQuery()
				.eq(UserInfo::getTenantId,tenantId)
				.eq(UserInfo::getUserRole, userRolesDefinition.getVariableString())
				.eq(UserInfo::getUserStatus, "0")
				.like(UserInfo::getAffiliated, affiliationId));

		if (userInfoList.size() <= 0) return;


		Integer ra = new Random().nextInt(userInfoList.size());

//		String inviteDuration = (String) delegateExecution.getVariable("inviteDuration");


//		LOGGER.info("\n\n ...get auto check guest" + recommendInfo);

//		UserInfoDto userInfoDto = new UserInfoDto();
//
//		userInfoDto.setId("654321");
//		userInfoDto.setUserRole("salesman");
//		userInfoDto.setRealName("测试业务员");
//
//		delegateExecution.setVariable("salesmanInfo", userInfoDto);

		delegateExecution.setVariable("agentId",userInfoList.get(ra).getId());

//		delegateExecution.setVariable("inviteDuration","PT15M");
//		delegateExecution.setVariable("depositDuration","PT15M");
//
//		delegateExecution.setVariable("executionDuration","PT5M");

	}
}
