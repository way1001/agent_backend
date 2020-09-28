package com.aiforest.cloud.broker.admin.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class LoggerDelegate implements JavaDelegate {

	private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		LOGGER.info("\n\n ...LogeerDelegate invoked by"
				+ "processDefinitionId=" + delegateExecution.getProcessDefinitionId()
				+ "activityId=" + delegateExecution.getCurrentActivityId()
				+ "activityName=" + delegateExecution.getCurrentActivityName()
				+ "processDefinitionId=" + delegateExecution.getProcessDefinitionId()
				+ "processBusinessKey=" + delegateExecution.getProcessBusinessKey()
				+ "delegateExecutionId=" + delegateExecution.getId()
				+ "\n\n");
	}
}
