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
 * 短信记录
 *
 * @author way
 * @date 2020-05-09 17:21:38
 */
@Data
@TableName("sms_records")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "短信记录")
public class SmsRecords extends Model<SmsRecords> {
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
     * 发送者ID
     */
    @ApiModelProperty(value = "发送者ID")
    private String senderId;
    /**
     * 接收者ID
     */
    @ApiModelProperty(value = "接收者ID")
    private String receiverId;
    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String details;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 发送状态（0：成功；1：失败）
     */
    @ApiModelProperty(value = "发送状态（0：成功；1：失败）")
    private String status;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String phone;
	/**
	 * 用户信息
	 */
	@TableField(exist = false)
	private UserInfo senderInfo;
	/**
	 * 用户信息
	 */
	@TableField(exist = false)
	private UserInfo receiverInfo;

}
