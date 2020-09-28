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
public class CheckGuestDelegate implements JavaDelegate {

//	private final Logger LOGGER = Logger.getLogger(CheckGuestDelegate.class.getName());

	private final ReferralsService referralsService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		String phone = (String)delegateExecution.getVariable("phone");
		String affiliationId = (String)delegateExecution.getVariable("affiliationId");
		String tenantId = (String)delegateExecution.getVariable("tenantId");


		Integer isNumber = referralsService.count(Wrappers.<Referrals>lambdaQuery()
				.eq(Referrals::getPhone, phone)
				.eq(Referrals::getAffiliationId, affiliationId)
				.eq(Referrals::getTenantId, tenantId));

		Boolean isValid = isNumber <= 0;

//		LOGGER.info("\n\n ...get auto check guest" + phone);

		delegateExecution.setVariable("isValid", isValid);

	}
}
