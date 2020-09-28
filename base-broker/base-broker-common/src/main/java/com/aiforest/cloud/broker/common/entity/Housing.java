/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.net
 * 注意：
 * 本软件为www.aiforest.net开发研制，未经购买不得使用
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房源信息
 *
 * @author aiforest
 * @date 2020-08-29 16:56:16
 */
@Data
@TableName("housing")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房源信息")
public class Housing extends Model<Housing> {
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
     * 序号
     */
    @NotNull(message = "序号不能为空")
    @ApiModelProperty(value = "序号")
    private String serialNumber;
    /**
     * 分期
     */
    @NotNull(message = "分期不能为空")
    @ApiModelProperty(value = "分期")
    private String staging;
    /**
     * 楼栋
     */
    @NotNull(message = "楼栋不能为空")
    @ApiModelProperty(value = "楼栋")
    private String building;
    /**
     * 单元
     */
    @NotNull(message = "单元不能为空")
    @ApiModelProperty(value = "单元")
    private String entrance;
    /**
     * 楼层
     */
    @NotNull(message = "楼层不能为空")
    @ApiModelProperty(value = "楼层")
    private String floor;
    /**
     * 户型
     */
    @NotNull(message = "户型不能为空")
    @ApiModelProperty(value = "户型")
    private String houseType;
    /**
     * 物业类型
     */
    @NotNull(message = "物业类型不能为空")
    @ApiModelProperty(value = "物业类型")
    private String propertyType;
    /**
     * 建筑面积
     */
    @NotNull(message = "建筑面积不能为空")
    @ApiModelProperty(value = "建筑面积")
    private String floorage;
    /**
     * 套内面积
     */
    @NotNull(message = "套内面积不能为空")
    @ApiModelProperty(value = "套内面积")
    private String insideFloorage;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;
    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;
    /**
     * 成交价
     */
    @ApiModelProperty(value = "成交价")
    private BigDecimal strikePrice;
    /**
     * 签约总价
     */
    @ApiModelProperty(value = "签约总价")
    private BigDecimal contractPrice;
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
     * 状态（0：待售；1：认购 2：签约 3：销控）
     */
    @NotNull(message = "状态（0：待售；1：认购 2：签约 3：销控）不能为空")
    @ApiModelProperty(value = "状态（0：待售；1：认购 2：签约 3：销控）")
    private String state;

}
