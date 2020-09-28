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
 * 房产动态
 *
 * @author way
 * @date 2020-04-09 09:05:49
 */
@Data
@TableName("news_trends")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产动态")
public class NewsTrends extends Model<NewsTrends> {
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
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 1最新优惠 2楼盘活动 3工程进度 4职业导购
     */
    @ApiModelProperty(value = "1最新优惠 2楼盘活动 3工程进度 4职业导购")
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
	 * 新闻发布类型（0：自定义新闻；1：公众号文章链接）
	 */
	@ApiModelProperty(value = "新闻发布类型（0：自定义新闻；1：公众号文章链接）")
	private String type;
	/**
	 * 文章链接
	 */
	@ApiModelProperty(value = "文章链接")
	private String articleLink;
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
	 * 归属类型
	 */
	@ApiModelProperty(value = "归属类型（1：平台；2：楼盘 3：两兼）")
	private String affiliation;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	@TableField(exist = false)
	private List<TopicReply> listTopicReply;

}
