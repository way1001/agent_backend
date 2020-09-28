package com.aiforest.cloud.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.codegen.entity.GenTable;
import com.aiforest.cloud.codegen.mapper.SysDatasourceMapper;
import com.aiforest.cloud.codegen.util.GenUtils;
import com.aiforest.cloud.codegen.mapper.SysGeneratorMapper;
import com.aiforest.cloud.codegen.service.SysGeneratorService;
import com.aiforest.cloud.common.datasource.constants.DataSourceConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author JL
 * @date 2018-07-30
 */
@Service
@AllArgsConstructor
public class SysGeneratorServiceImpl implements SysGeneratorService {
	private final SysGeneratorMapper sysGeneratorMapper;
	private final SysDatasourceMapper sysDatasourceMapper;

	/**
	 * 分页查询表
	 *
	 * @param tableName 查询条件
	 * @param sysDatasourceId
	 * @return
	 */
	@Override
	public IPage<List<Map<String, Object>>> getPage(Page page, String tableName, String sysDatasourceId) {
		if(StrUtil.isNotBlank(sysDatasourceId)){
			String ds = sysDatasourceMapper.selectById(sysDatasourceId).getName();
			DynamicDataSourceContextHolder.push(ds);
		}
		IPage<List<Map<String, Object>>> rs = sysGeneratorMapper.queryList(page, tableName);
		DynamicDataSourceContextHolder.push(DataSourceConstants.DS_MASTER);//切回主数据源
		return rs;
	}

	@Override
	public Map<String, String> generatorView(GenTable genTable) {
		if(StrUtil.isNotBlank(genTable.getSysDatasourceId())){
			String ds = sysDatasourceMapper.selectById(genTable.getSysDatasourceId()).getName();
			DynamicDataSourceContextHolder.push(ds);
		}
		//查询表信息
		Map<String, String> table = queryTable(genTable.getTableName());
		//查询列信息
		List<Map<String, Object>> columns = queryColumns(genTable.getTableName());
		Map<String, String> rs = GenUtils.generatorCode(genTable, table, columns, null);
		DynamicDataSourceContextHolder.push(DataSourceConstants.DS_MASTER);//切回主数据源
		return rs;
	}

	/**
	 * 生成代码
	 *
	 * @param genTable 生成表配置
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenTable genTable) {
		if(StrUtil.isNotBlank(genTable.getSysDatasourceId())){
			String ds = sysDatasourceMapper.selectById(genTable.getSysDatasourceId()).getName();
			DynamicDataSourceContextHolder.push(ds);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		//查询表信息
		Map<String, String> table = queryTable(genTable.getTableName());
		//查询列信息
		List<Map<String, Object>> columns = queryColumns(genTable.getTableName());
		//生成代码
		GenUtils.generatorCode(genTable, table, columns, zip);
		IoUtil.close(zip);
		byte[] rs = outputStream.toByteArray();
		DynamicDataSourceContextHolder.push(DataSourceConstants.DS_MASTER);//切回主数据源
		return rs;
	}

	private Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}

	private List<Map<String, Object>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}
}
