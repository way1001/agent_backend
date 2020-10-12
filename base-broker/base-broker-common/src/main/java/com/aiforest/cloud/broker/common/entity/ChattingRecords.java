/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 聊天记录
 *
 * @author aiforest
 * @date 2020-10-07 10:46:02
 */
@Data
@TableName("chatting_records")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "聊天记录")
public class ChattingRecords extends Model<ChattingRecords> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @NotNull(message = "PK不能为空")
    @ApiModelProperty(value = "PK")
    private String id;
    /**
     * 所属租户
     */
    @NotNull(message = "所属租户不能为空")
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 关联楼盘ID
     */
    @ApiModelProperty(value = "关联楼盘ID")
    private String affiliationId;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @NotNull(message = "逻辑删除标记（0：显示；1：隐藏）不能为空")
    @ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
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
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String messageblob;
    /**
     * 发送状态（0：未接收；1：已接收）
     */
    @NotNull(message = "发送状态（0：未接收；1：已接收）不能为空")
    @ApiModelProperty(value = "发送状态（0：未接收；1：已接收）")
    private String status;
    /**
     * 类型（0：文本消息，1：图片消息）
     */
    @ApiModelProperty(value = "类型（0：文本消息，1：图片消息）")
    private String type;

}
