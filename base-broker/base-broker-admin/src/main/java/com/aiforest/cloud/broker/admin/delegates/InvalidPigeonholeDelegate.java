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
public class InvalidPigeonholeDelegate implements JavaDelegate {

	private final ReferralsService referralsService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		String phone = (String)delegateExecution.getVariable("phone");
		String affiliationId = (String)delegateExecution.getVariable("affiliationId");
		String tenantId = (String)delegateExecution.getVariable("tenantId");

		Referrals referrals = referralsService.getOne(Wrappers.<Referrals>lambdaQuery()
				.eq(Referrals::getTenantId,tenantId)
				.eq(Referrals::getAffiliationId, affiliationId)
				.eq(Referrals::getPhone, phone));

		if (referrals == null) return;

		referrals.setWorkflowStatus("1");

		referralsService.updateById(referrals);

	}

}
