package com.aiforest.cloud.broker.admin.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class ExceptionHandlingDelegate implements JavaDelegate {

	private final Logger LOGGER = Logger.getLogger(ExceptionHandlingDelegate.class.getName());


	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {


		String phone = (String) delegateExecution.getVariable("phone");


		LOGGER.info("\n\n ...get phone" + phone);

		delegateExecution.setVariable("repetition", false);

	}
}
