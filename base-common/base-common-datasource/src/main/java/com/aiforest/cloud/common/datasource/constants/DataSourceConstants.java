package com.aiforest.cloud.common.datasource.constants;

import com.aiforest.cloud.common.core.constant.CommonConstants;

/**
 * <p>
 * 数据源相关常量
 */
public interface DataSourceConstants {
	/**
	 * 默认的数据源名称
	 */
	String DS_MASTER = "master";
	/**
	 * 数据源查询SQL
	 */
	String DS_QUERY_SQL = "select * from " + CommonConstants.UPMS_DATABASE + ".sys_datasource where del_flag = 0";

	/**
	 * 数据源名称
	 */
	String DS_NAME = "name";

	/**
	 * jdbcurl
	 */
	String DS_JDBC_URL = "url";

	/**
	 * 用户名
	 */
	String DS_USER_NAME = "username";

	/**
	 * 密码
	 */
	String DS_USER_PWD = "password";

}
