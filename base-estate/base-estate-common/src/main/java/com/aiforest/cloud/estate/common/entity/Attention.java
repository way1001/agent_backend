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
 * 房产关注
 *
 * @author way
 * @date 2020-04-21 10:02:09
 */
@Data
@TableName("attention")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产关注")
public class Attention extends Model<Attention> {
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
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 关注内容ID
     */
    @ApiModelProperty(value = "关注内容ID")
    private String attentionId;
    /**
     * 1降价通知 2开盘通知 3户型关注
     */
    @ApiModelProperty(value = "1降价通知 2开盘通知 3户型关注")
    private String attentionType;
    /**
     * 关注状态（0：已关；1：取关）
     */
    @ApiModelProperty(value = "关注状态（0：已关；1：取关）")
    private String attentionStatus;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	/**
	 * 关注户型
	 */
	@TableField(exist = false)
	private ApartmentLayout apartmentLayout;

	/**
	 * 用户信息
	 */
	@TableField(exist = false)
	private UserInfo userInfo;

}
