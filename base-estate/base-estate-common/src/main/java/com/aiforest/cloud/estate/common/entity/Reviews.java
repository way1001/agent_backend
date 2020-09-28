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
        import java.math.BigDecimal;
    import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房产点评
 *
 * @author way
 * @date 2020-04-09 09:05:34
 */
@Data
@TableName("reviews")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产点评")
public class Reviews extends Model<Reviews> {
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
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    /**
     * 地段
     */
    @ApiModelProperty(value = "地段")
    private BigDecimal location;
    /**
     * 交通
     */
    @ApiModelProperty(value = "交通")
    private BigDecimal environment;
    /**
     * 配套
     */
    @ApiModelProperty(value = "配套")
    private BigDecimal supporting;
    /**
     * 环境
     */
    @ApiModelProperty(value = "环境")
    private BigDecimal transportation;
    /**
     * 留言量
     */
    @ApiModelProperty(value = "留言量")
    private Integer guestbooks;
    /**
     * 点赞量
     */
    @ApiModelProperty(value = "点赞量")
    private Integer likes;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer views;
    /**
     * 是否到访 0为否 1为是
     */
    @ApiModelProperty(value = "是否到访 0为否 1为是")
    private String isVisited;
    /**
     * 是否匿名 0为否 1为是
     */
    @ApiModelProperty(value = "是否匿名 0为否 1为是")
    private String isAnonymous;
	/**
	 * 审核状态（0：未审核；1：通过 2：拒绝）
	 */
	@ApiModelProperty(value = "审核状态（0：未审核；1：通过 2：拒绝）")
	private String auditStatus;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	@TableField(exist = false)
	private List<TopicReply> listTopicReply;

	/**
	 * 用户信息
	 */
	@TableField(exist = false)
	private UserInfo userInfo;
	/**
	 * 是否点赞
	 */
	@TableField(exist = false)
	private List<Likes> upvote;

}
