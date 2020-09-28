package com.aiforest.cloud.broker.test;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

public class TestCaseSample {
	private static final String PROCESS_KEY = "broker";

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();


	@Test
	@Deployment(resources = {"bpmn/broker.bpmn"})
	public void testCase() {
		String processInstanceId = "a2c2f866-0150-11eb-b06b-fed0548dc8db";
		runtimeService().deleteProcessInstance(processInstanceId, "manually delete Instance！");

		//TODO https://docs.camunda.org/manual/7.9/user-guide/process-engine/history/
		// https://forum.camunda.org/t/problem-using-clean-up-history-async/12325
//		rule.getHistoryService().cleanUpHistoryAsync(true);
//		List<Job> historyCleanupJobs = rule.getHistoryService().findHistoryCleanupJobs();
//		for (Job job: historyCleanupJobs) {
//			String jobId = job.getId();
//		}

	}

	@Test
	@Deployment(resources = {"testCaseSample.bpmn"})
	public void testSampleCase_happyPath() {

		ProcessInstance instance = runtimeService().startProcessInstanceByKey(PROCESS_KEY);

		assertThat(instance)
				.isActive()
				.hasPassed("startEvent")
				.isWaitingAtExactly("userTask1")
				.task().isNotAssigned();

		complete(task(), withVariables(
				"assignPerson", "dpoint",
				"attribute1", "value1"
		));

		assertThat(instance)
				.hasPassed("userTask1")
				.hasPassedInOrder("userTask1", "serviceTask1")
				.isWaitingAt("userTask2")
				.task().isAssignedTo("dpoint");

		complete(task(), withVariables("attributeService", "variableServicevalue"));
		assertThat(instance)
				.hasPassedInOrder("userTask2", "endEvent")
				.isEnded();

	}
}
