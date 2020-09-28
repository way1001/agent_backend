/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 流程变量定义
 *
 * @author aiforest
 * @date 2020-09-23 10:12:08
 */
@Data
@TableName("variable_definition")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "流程变量定义")
public class VariableDefinition extends Model<VariableDefinition> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @NotNull(message = "PK不能为空")
    @ApiModelProperty(value = "PK")
    private String id;
    /**
     * 所属租户
     */
    @NotNull(message = "所属租户不能为空")
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
	/**
	 * 关联楼盘ID
	 */
	@NotNull(message = "所属楼盘不能为空")
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    @NotNull(message = "最后更新时间不能为空")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;
    /**
     * 变量Id
     */
    @NotNull(message = "变量Id不能为空")
    @ApiModelProperty(value = "变量Id")
    private String variableId;
    /**
     * 变量类型
     */
    @NotNull(message = "变量类型不能为空")
    @ApiModelProperty(value = "变量类型")
    private String variableType;
    /**
     * 变量标签
     */
    @NotNull(message = "变量标签不能为空")
    @ApiModelProperty(value = "变量标签")
    private String variableLabel;
    /**
     * 字符串变量
     */
    @ApiModelProperty(value = "字符串变量")
    private String variableString;
    /**
     * 布尔变量
     */
    @ApiModelProperty(value = "布尔变量")
    private Integer variableBoolean;
    /**
     * 整形变量
     */
    @ApiModelProperty(value = "整形变量")
    private Integer variableInteger;
    /**
     * 定义Id
     */
    @ApiModelProperty(value = "定义Id")
    private String definitionId;
    /**
     * 定义Id
     */
    @ApiModelProperty(value = "定义Id")
    private String definitionKey;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;

}
