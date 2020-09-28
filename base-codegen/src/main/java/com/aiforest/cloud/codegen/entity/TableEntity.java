package com.aiforest.cloud.codegen.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author JL
 * @date 2018/07/29
 * 表属性： https://blog.csdn.net/lkforce/article/details/79557482
 */
@Data
@ApiModel(value="表属性")
public class TableEntity {
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String tableName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String comments;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private ColumnEntity pk;
	/**
	 * 列名
	 */
	@ApiModelProperty(value = "列名")
	private List<ColumnEntity> columns;
	/**
	 * 驼峰类型
	 */
	@ApiModelProperty(value = "驼峰类型")
	private String caseClassName;
	/**
	 * 普通类型
	 */
	@ApiModelProperty(value = "普通类型")
	private String lowerClassName;
	/**
	 * 服务的路由key
	 */
	@ApiModelProperty(value = "服务的路由key")
	private String genKey;
}
