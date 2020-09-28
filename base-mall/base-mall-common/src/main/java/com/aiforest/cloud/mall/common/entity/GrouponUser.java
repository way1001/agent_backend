/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.aiforest.cloud.mall.common.constant.MallConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 拼团记录
 *
 * @author JL
 * @date 2020-03-17 12:01:53
 */
@Data
@TableName("groupon_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "拼团记录")
public class GrouponUser extends Model<GrouponUser> {
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
     * 拼团ID
     */
    @ApiModelProperty(value = "拼团ID")
    private String grouponId;
	/**
	 * 拼团人数
	 */
	@ApiModelProperty(value = "拼团人数")
	private Integer grouponNum;
	/**
	 * 拼团价
	 */
	@ApiModelProperty(value = "拼团价")
	private BigDecimal grouponPrice;
    /**
     * 组ID（团长的拼团记录ID）
     */
    @ApiModelProperty(value = "组ID（团长的拼团记录ID）")
    private String groupId;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headimgUrl;
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private String spuId;
    /**
     * skuId
     */
    @ApiModelProperty(value = "skuId")
    private String skuId;
    /**
     * 是否团长（0：否；1：是）
     */
    @ApiModelProperty(value = "是否团长（0：否；1：是）")
    private String isLeader;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime validBeginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime validEndTime;
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单Id")
    private String orderId;
	/**
	 * 状态（0：拼团中；1：完成拼团）
	 */
	@ApiModelProperty(value = "状态（0：拼团中；1：完成拼团）")
	private String status;

	@TableField(exist = false)
	private Integer havgrouponNum;

	@TableField(exist = false)
	private GrouponInfo grouponInfo;

	@TableField(exist = false)
	private GrouponUser grouponUser;

	@TableField(exist = false)
	private List<GrouponUser> listGrouponUser;

	public String getStatus() {
		if (MallConstants.GROUPON_USER_STATUS_0.equals(status) && this.validEndTime != null) {
			if (this.validEndTime.isAfter(LocalDateTime.now())) {
				return status;
			} else {
				return MallConstants.GROUPON_USER_STATUS_2;
			}
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
