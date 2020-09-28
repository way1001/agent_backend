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
    import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房产户型
 *
 * @author way
 * @date 2020-04-13 16:05:09
 */
@Data
@TableName("apartment_layout")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产户型")
public class ApartmentLayout extends Model<ApartmentLayout> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "PK")
    private String id;
	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private Integer ordinal;
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
     * 销售状态 0在售 1待售 2售馨
     */
    @ApiModelProperty(value = "销售状态 0在售 1待售 2售馨")
    private String salesStatus;
    /**
     * 户型图
     */
    @ApiModelProperty(value = "户型图")
    private String src;
    /**
     * 户型名称
     */
    @ApiModelProperty(value = "户型名称")
    private String name;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价")
    private String price;
    /**
     * 户型详情关键字
     */
    @ApiModelProperty(value = "户型详情关键字")
    private String keywords;
    /**
     * 户型类型
     */
    @ApiModelProperty(value = "户型类型")
    private String type;
	/**
	 * 户型分类
	 */
	@ApiModelProperty(value = "户型分类")
	private String category;
    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积")
    private String floorage;
    /**
     * 朝向
     */
    @ApiModelProperty(value = "朝向")
    private String orientation;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    /**
     * 是否置顶 0为否 1为是
     */
    @ApiModelProperty(value = "是否置顶 0为否 1为是")
    private String isStick;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	@TableField(exist = false)
	private List<TopicReply> listTopicReply;

}
