package com.aiforest.cloud.broker.common.fact;

public class ProcessRuleCheckResult {
	/**
	 * true:通过校验；false：未通过校验
	 */
	private boolean postCodeResult = false;

	public boolean isPostCodeResult() {
		return postCodeResult;
	}

	public void setPostCodeResult(boolean postCodeResult) {
		this.postCodeResult = postCodeResult;
	}
}
