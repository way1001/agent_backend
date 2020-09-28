/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房产平台信息
 *
 * @author way
 * @date 2020-08-19 10:38:07
 */
@Data
@TableName("platform_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产平台信息")
public class PlatformInfo extends Model<PlatformInfo> {
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
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private String createId;
    /**
     * 项目名称
     */
    @NotNull(message = "项目名称不能为空")
    @ApiModelProperty(value = "项目名称")
    private String platformName;
    /**
     * 来源应用id
     */
    @NotNull(message = "来源应用id不能为空")
    @ApiModelProperty(value = "来源应用id")
    private String appId;
	/**
	 * 互动开关（0：开启；1：关闭）
	 */
	@NotNull(message = "互动开关（0：开启；1：关闭）不能为空")
	@ApiModelProperty(value = "互动开关（0：开启；1：关闭）")
	private String interaction;

	@TableField(exist = false)
	private List<BasicInfo> basicInfoList;

}
