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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房产布告详情
 *
 * @author way
 * @date 2020-04-01 17:21:07
 */
@Data
@TableName("advertisement_item")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产布告详情")
public class AdvertisementItem extends Model<AdvertisementItem> {
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
     * 布告ID
     */
    @ApiModelProperty(value = "布告ID")
    private String advertisementId;
    /**
     * 类型1、图片；2、视频；3、文字
     */
    @ApiModelProperty(value = "类型1、图片；2、视频；3、文字")
    private String type;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 通知链接
     */
    @ApiModelProperty(value = "通知链接")
    private String url;
    /**
     * 跳转页面
     */
    @ApiModelProperty(value = "跳转页面")
    private String page;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;
    /**
     * （1：开启；0：关闭）
     */
    @ApiModelProperty(value = "（1：开启；0：关闭）")
    private String enable;
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


//	@TableField(exist = false)
//	private String appId;

	@TableField(exist = false)
	private String AdvertisementType;

}
