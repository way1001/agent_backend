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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 拼团
 *
 * @author JL
 * @date 2020-03-17 11:55:32
 */
@Data
@TableName("groupon_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "拼团")
public class GrouponInfo extends Model<GrouponInfo> {
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
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    /**
     * （1：开启；0：关闭）
     */
    @ApiModelProperty(value = "（1：开启；0：关闭）")
    private String enable;
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
     * 拼团名称
     */
    @ApiModelProperty(value = "拼团名称")
    private String name;
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
	 * 拼团时长（小时）
	 */
	@ApiModelProperty(value = "拼团时长（小时）")
	private Integer duration;
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
     * 发起人数
     */
    @ApiModelProperty(value = "发起人数")
    private Integer launchNum;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String picUrl;
    /**
     * 拼团规则
     */
    @ApiModelProperty(value = "拼团规则")
    private String grouponRule;
    /**
     * 分享标题 
     */
    @ApiModelProperty(value = "分享标题 ")
    private String shareTitle;

	@TableField(exist = false)
	private GoodsSpu goodsSpu;

	@TableField(exist = false)
	private GoodsSku goodsSku;

	@TableField(exist = false)
	private String status;

	public String getStatus() {
		if(this.validEndTime != null){
			if(LocalDateTime.now().isBefore(this.validBeginTime)){
				return MallConstants.GROUPON_INFO_STATUS_0;
			}else if(LocalDateTime.now().isAfter(this.validEndTime)){
				return MallConstants.GROUPON_INFO_STATUS_2;
			}else{
				return MallConstants.GROUPON_INFO_STATUS_1;
			}
		}
		return null;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
