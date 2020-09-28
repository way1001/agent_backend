package com.aiforest.cloud.codegen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aiforest.cloud.codegen.entity.SysDatasource;

import java.sql.SQLException;

/**
 * 数据源表
 *
 * @author
 */
public interface SysDatasourceService extends IService<SysDatasource> {
	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDatasource
	 * @return
	 */
	Boolean saveSysDatasource(SysDatasource sysDatasource) throws SQLException;

	/**
	 * 更新数据源
	 *
	 * @param sysDatasource
	 * @return
	 */
	Boolean updateSysDatasource(SysDatasource sysDatasource) throws SQLException;
}
