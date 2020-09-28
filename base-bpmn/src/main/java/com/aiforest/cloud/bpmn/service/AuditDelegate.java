package com.aiforest.cloud.bpmn.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AuditDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution var1) throws Exception {
		System.out.println("审核流程 - serveice task - 回调");
	}

}
