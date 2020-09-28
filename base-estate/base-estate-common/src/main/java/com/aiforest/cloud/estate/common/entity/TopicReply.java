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
 * 房产总回复
 *
 * @author way
 * @date 2020-04-09 09:05:43
 */
@Data
@TableName("topic_reply")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产总回复")
public class TopicReply extends Model<TopicReply> {
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
     * 回复ID
     */
    @ApiModelProperty(value = "回复ID")
    private String typesId;
    /**
     * 1炫拍回复 2点评回复 3户型回复
     */
    @ApiModelProperty(value = "1炫拍回复 2点评回复 3户型回复")
    private String replyTypes;
    /**
     * 0普通回复 1本人回复 2官方回复
     */
    @ApiModelProperty(value = "0普通回复 1本人回复 2官方回复")
    private String replyClass;
	/**
	 * 点赞量
	 */
	@ApiModelProperty(value = "点赞量")
	private Integer likes;
    /**
     * 动态详情
     */
    @ApiModelProperty(value = "动态详情")
    private String details;
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
     * 审核状态（0：未审核；1：通过 2：拒绝）
     */
    @ApiModelProperty(value = "审核状态（0：未审核；1：通过 2：拒绝）")
    private String auditStatus;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

    //取消回复关联
//	@TableField(exist = false)
//	private List<TopicReply> listTopicReply;
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
	/**
	 * 炫拍
	 */
	@TableField(exist = false)
	private AwesomeShooting awesomeShooting;
	/**
	 * 点评
	 */
	@TableField(exist = false)
	private Reviews reviews;
	/**
	 * 户型
	 */
	@TableField(exist = false)
	private ApartmentLayout apartmentLayout;
	/**
	 * 提问
	 */
	@TableField(exist = false)
	private AskQuestions askQuestions;

}
