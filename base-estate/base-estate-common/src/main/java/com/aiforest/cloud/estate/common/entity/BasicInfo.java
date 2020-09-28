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
import com.aiforest.cloud.common.data.mybatis.typehandler.ArrayStringTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
        import java.math.BigDecimal;
    import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.type.JdbcType;

/**
 * 房产基础信息
 *
 * @author way
 * @date 2020-04-06 08:39:12
 */
@Data
@TableName("basic_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产基础信息")
public class BasicInfo extends Model<BasicInfo> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "PK")
    private String id;
    /**
     * 所属租户
     */
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
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
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 销售状态 0在售 1待售 2售馨
     */
    @ApiModelProperty(value = "销售状态 0在售 1待售 2售馨")
    private String salesStatus;
    /**
     * 均价
     */
    @ApiModelProperty(value = "均价")
    private String price;
    /**
     * 户型
     */
	@ApiModelProperty(value = "户型")
	@TableField(typeHandler = ArrayStringTypeHandler.class, jdbcType= JdbcType.VARCHAR)
	private String[] houseType;
//    private String houseType;
    /**
     * 建筑类型 1住房 2商住 3公寓 4别墅
     */
    @ApiModelProperty(value = "建筑类型 1住房 2商住 3公寓 4别墅")
    private String residenceType;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String keywords;
    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积")
    private String floorage;
    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String region;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    /**
     * 
     */
    @ApiModelProperty(value = "")
    private BigDecimal longitude;
    /**
     * 
     */
    @ApiModelProperty(value = "")
    private BigDecimal latitude;
    /**
     * 楼盘电话
     */
    @ApiModelProperty(value = "楼盘电话")
    private String premisesMobile;
    /**
     * 开盘日期
     */
    @ApiModelProperty(value = "开盘日期")
    private String openingDate;
	/**
	 * 浏览量
	 */
	@ApiModelProperty(value = "浏览量")
	private Integer views;
	/**
	 *
	 */
	@ApiModelProperty(value = "")
	private String brokerNote;
	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段")
	private Integer sort;
}
