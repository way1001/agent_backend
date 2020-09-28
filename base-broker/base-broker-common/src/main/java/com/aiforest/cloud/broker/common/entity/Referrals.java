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
 * 推荐表
 *
 * @author aiforest
 * @date 2020-09-23 10:15:51
 */
@Data
@TableName("referrals")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "推荐表")
public class Referrals extends Model<Referrals> {
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
	 * 逻辑删除标记（0：显示；1：隐藏）
	 */
	@ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
	private String delFlag;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 客户手机号码
     */
    @ApiModelProperty(value = "客户手机号码")
    private String phone;
    /**
     * 客户姓名
     */
    @NotNull(message = "客户姓名不能为空")
    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    /**
     * 性别（1：男，2：女，0：未知）
     */
    @ApiModelProperty(value = "性别（1：男，2：女，0：未知）")
    private String gender;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 关联楼盘ID
     */
    @ApiModelProperty(value = "关联楼盘ID")
    private String affiliationId;
    /**
     * 经纪人ID
     */
    @ApiModelProperty(value = "经纪人ID")
    private String brokerId;
    /**
     * 经纪人姓名
     */
    @NotNull(message = "经纪人姓名不能为空")
    @ApiModelProperty(value = "经纪人姓名")
    private String brokerName;
    /**
     * 经纪人号码
     */
    @ApiModelProperty(value = "经纪人号码")
    private String brokerPhone;
    /**
     * 业务员ID
     */
    @ApiModelProperty(value = "业务员ID")
    private String salesmanId;
    /**
     * 被邀请人ID
     */
    @ApiModelProperty(value = "被邀请人ID")
    private String inviteeId;
    /**
     * 当前办理人
     */
    @ApiModelProperty(value = "当前办理人")
    private String currentHandler;
    /**
     * 话事人
     */
    @ApiModelProperty(value = "话事人")
    private String staffId;
    /**
     * 流程实例
     */
    @ApiModelProperty(value = "流程实例")
    private String instanceId;
    /**
     * 流程状态 0活跃 1完结 2挂起
     */
    @NotNull(message = "流程状态 0活跃 1完结 2挂起不能为空")
    @ApiModelProperty(value = "流程状态 0活跃 1完结 2挂起")
    private String workflowStatus;

}
