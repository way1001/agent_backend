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
 * 房产提问
 *
 * @author way
 * @date 2020-04-13 11:15:42
 */
@Data
@TableName("likes")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产提问")
public class Likes extends Model<Likes> {
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
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 点赞内容ID
     */
    @ApiModelProperty(value = "点赞内容ID")
    private String likesId;
    /**
     * 1炫拍 2点评 3户型 4提问 5回复
     */
    @ApiModelProperty(value = "1炫拍 2点评 3户型 4提问 5回复")
    private String likesType;
    /**
     * 点赞状态（0：已赞；1：取赞）
     */
    @ApiModelProperty(value = "点赞状态（0：已赞；1：取赞）")
    private String likeStatus;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	/**
	 * 用户信息
	 */
	@TableField(exist = false)
	private UserInfo userInfo;

}
