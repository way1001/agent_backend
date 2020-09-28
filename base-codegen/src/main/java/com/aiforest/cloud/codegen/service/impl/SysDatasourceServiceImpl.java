package com.aiforest.cloud.codegen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.codegen.entity.SysDatasource;
import com.aiforest.cloud.codegen.mapper.SysDatasourceMapper;
import com.aiforest.cloud.codegen.service.SysDatasourceService;
import com.aiforest.cloud.common.datasource.util.DynamicDataSourceUtils;
import lombok.AllArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

/**
 * 数据源表
 */
@Service
@AllArgsConstructor
public class SysDatasourceServiceImpl extends ServiceImpl<SysDatasourceMapper, SysDatasource> implements SysDatasourceService {
	private final DynamicDataSourceUtils dynamicDataSourceUtils;
	private final StringEncryptor stringEncryptor;

	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDatasource
	 * @return
	 */
	@Override
	public Boolean saveSysDatasource(SysDatasource sysDatasource) {
		DataSourceProperty dataSourceProperty = new DataSourceProperty();
		dataSourceProperty.setPoolName(sysDatasource.getName());
		dataSourceProperty.setUrl(sysDatasource.getUrl());
		dataSourceProperty.setUsername(sysDatasource.getUsername());
		dataSourceProperty.setPassword(sysDatasource.getPassword());
		//校验数据源配置是否能链接
		dynamicDataSourceUtils.checkDataSource(dataSourceProperty);
		//添加动态数据源
		dynamicDataSourceUtils.addDataSource(dataSourceProperty);
		sysDatasource.setPassword(stringEncryptor.encrypt(sysDatasource.getPassword()));
		this.baseMapper.insert(sysDatasource);
		return Boolean.TRUE;
	}

	/**
	 * 更新数据源
	 *
	 * @param sysDatasource
	 * @return
	 */
	@Override
	public Boolean updateSysDatasource(SysDatasource sysDatasource) {
		DataSourceProperty dataSourceProperty = new DataSourceProperty();
		dataSourceProperty.setPoolName(sysDatasource.getName());
		dataSourceProperty.setUrl(sysDatasource.getUrl());
		dataSourceProperty.setUsername(sysDatasource.getUsername());
		dataSourceProperty.setPassword(sysDatasource.getPassword());
		//校验数据源配置是否能链接
		dynamicDataSourceUtils.checkDataSource(dataSourceProperty);
		//先删除动态数据源
		dynamicDataSourceUtils.removeDataSource(baseMapper.selectById(sysDatasource.getId()).getName());
		//再添加动态数据源
		dynamicDataSourceUtils.addDataSource(dataSourceProperty);
		if (StrUtil.isNotBlank(sysDatasource.getPassword())) {
			sysDatasource.setPassword(stringEncryptor.encrypt(sysDatasource.getPassword()));
		}
		this.baseMapper.updateById(sysDatasource);
		return Boolean.TRUE;
	}
}
