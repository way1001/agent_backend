/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.common.entity;

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
 * 微信模板/订阅消息
 *
 * @author JL
 * @date 2020-04-16 17:30:03
 */
@Data
@TableName("wx_template_msg")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "微信模板/订阅消息")
public class WxTemplateMsg extends Model<WxTemplateMsg> {
    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateId;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
    /**
     * （1：开启；0：关闭）
     */
    @ApiModelProperty(value = "（1：开启；0：关闭）")
    private String enable;
    /**
     * 所属租户
     */
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 公众号配置ID、小程序AppID
     */
    @ApiModelProperty(value = "公众号配置ID、小程序AppID")
    private String appId;
    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private String priTmplId;
    /**
     * 模版标题
     */
    @ApiModelProperty(value = "模版标题")
    private String title;
    /**
     * 模版内容
     */
    @ApiModelProperty(value = "模版内容")
    private String content;
    /**
     * 模板内容示例
     */
    @ApiModelProperty(value = "模板内容示例")
    private String example;
    /**
     * 模版类型，2 为一次性订阅，3 为长期订阅
     */
    @ApiModelProperty(value = "模版类型，2 为一次性订阅，3 为长期订阅")
    private String type;
	/**
	 * 用途，2：订单支付成功；3、订单发货提醒
	 */
	@ApiModelProperty(value = "用途，2：订单支付成功；3、订单发货提醒")
    private String useType;
	@TableField(exist = false)
	private List<String> useTypeList;
}
