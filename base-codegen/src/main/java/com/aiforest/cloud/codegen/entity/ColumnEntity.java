package com.aiforest.cloud.codegen.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JL
 * 列属性
 */
@Data
@ApiModel(value="列属性")
public class ColumnEntity {
	/**
	 * 列表
	 */
	@ApiModelProperty(value = "列名")
	private String columnName;
	/**
	 * 数据类型
	 */
	@ApiModelProperty(value = "数据类型")
	private String dataType;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String comments;

	/**
	 * 驼峰属性
	 */
	@ApiModelProperty(value = "驼峰属性")
	private String caseAttrName;
	/**
	 * 普通属性
	 */
	@ApiModelProperty(value = "普通属性")
	private String lowerAttrName;
	/**
	 * 属性类型
	 */
	@ApiModelProperty(value = "属性类型")
	private String attrType;
	/**
	 * 其他信息
	 */
	@ApiModelProperty(value = "其他信息")
	private String extra;
	/**
	 * 字符最大长度
	 */
	@ApiModelProperty(value = "字符最大长度")
	private Long characterMaximumLength;
	/**
	 * 是否可为空
	 */
	@ApiModelProperty(value = "是否可为空")
	private String isNullable;
}
