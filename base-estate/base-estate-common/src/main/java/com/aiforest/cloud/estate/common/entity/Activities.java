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
 * 房产动态
 *
 * @author way
 * @date 2020-08-18 17:10:20
 */
@Data
@TableName("activities")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产动态")
public class Activities extends Model<Activities> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @NotNull(message = "PK不能为空")
    @ApiModelProperty(value = "PK")
    private String id;
    /**
     * 序号
     */
    @NotNull(message = "序号不能为空")
    @ApiModelProperty(value = "序号")
    private Integer ordinal;
    /**
     * 所属租户
     */
    @NotNull(message = "所属租户不能为空")
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @NotNull(message = "逻辑删除标记（0：显示；1：隐藏）不能为空")
    @ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
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
     * 标题
     */
    @NotNull(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 1促销 2暖场 3店庆
     */
    @NotNull(message = "1促销 2暖场 3店庆不能为空")
    @ApiModelProperty(value = "1促销 2暖场 3店庆")
    private String category;
    /**
     * 缩略图链接
     */
    @ApiModelProperty(value = "缩略图链接")
    private String thumbnail;
    /**
     * 动态详情
     */
    @ApiModelProperty(value = "动态详情")
    private String details;
    /**
     * 活动类型（0：H5活动；1：其他活动）
     */
    @NotNull(message = "活动类型（0：H5活动；1：其他活动）不能为空")
    @ApiModelProperty(value = "活动类型（0：H5活动；1：其他活动）")
    private String type;
    /**
     * 活动链接
     */
    @ApiModelProperty(value = "活动链接")
    private String activityLink;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer views;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    /**
     * （1：平台；2：楼盘 3：两兼）
     */
    @ApiModelProperty(value = "（1：平台；2：楼盘 3：两兼）")
    private String affiliation;
    /**
     * 关联楼盘ID
     */
    @ApiModelProperty(value = "关联楼盘ID")
    private String affiliationId;

}
