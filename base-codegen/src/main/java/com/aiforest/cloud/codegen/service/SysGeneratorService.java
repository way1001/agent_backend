package com.aiforest.cloud.codegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.codegen.entity.GenTable;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
public interface SysGeneratorService {
	/**
	 * 生成代码预览
	 * @param genTable
	 * @return
	 */
	Map<String, String> generatorView(GenTable genTable);
	/**
	 * 生成代码
	 *
	 * @param genTable 生成表配置
	 * @return
	 */
	byte[] generatorCode(GenTable genTable);

	/**
	 * 分页查询表
	 *
	 * @param tableName 表名
	 * @param sysDatasourceId        数据源ID
	 * @return
	 */
	IPage<List<Map<String, Object>>> getPage(Page page, String tableName, String sysDatasourceId);
}
