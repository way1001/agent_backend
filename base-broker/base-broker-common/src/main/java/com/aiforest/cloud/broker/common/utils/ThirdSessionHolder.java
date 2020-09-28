package com.aiforest.cloud.broker.common.utils;

import com.aiforest.cloud.broker.common.entity.ThirdSession;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @author
 * thirdSession工具类
 */
@UtilityClass
public class ThirdSessionHolder {

	private final ThreadLocal<ThirdSession> THREAD_LOCAL_THIRD_SESSION = new TransmittableThreadLocal<>();


	/**
	 * TTL 设置thirdSession
	 *
	 * @param thirdSession
	 */
	public void setThirdSession(ThirdSession thirdSession) {
		THREAD_LOCAL_THIRD_SESSION.set(thirdSession);
	}

	/**
	 * 获取TTL中的thirdSession
	 *
	 * @return
	 */
	public ThirdSession getThirdSession() {
		return THREAD_LOCAL_THIRD_SESSION.get();
	}

	/**
	 * 获取用户ID
	 * @return
	 */
	public String getUserId(){
		return THREAD_LOCAL_THIRD_SESSION.get().getUserId();
	}

	public void clear() {
		THREAD_LOCAL_THIRD_SESSION.remove();
	}
}
