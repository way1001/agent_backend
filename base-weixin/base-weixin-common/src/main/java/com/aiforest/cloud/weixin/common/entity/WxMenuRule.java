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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;

/**
 * 微信自定义菜单分组
 *
 * @author JL
 * @date 2020-02-22 19:34:22
 */
@Data
@TableName("wx_menu_rule")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "微信自定义菜单分组")
public class WxMenuRule extends Model<WxMenuRule> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
	@ApiModelProperty(value = "PK")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 创建者
     */
    private String createId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateId;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private String delFlag;
	/**
	 * appId
	 */
	@ApiModelProperty(value = "appId")
	private String appId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String name;
    /**
     * 所属租户
     */
	@ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 菜单类型(1:普通菜单，2:个性化菜单)
     */
	@ApiModelProperty(value = "菜单类型")
    private String menuType;
    /**
     * menuid
     */
	@ApiModelProperty(value = "menuid")
    private String menuId;
    /**
     * 用户标签的id
     */
	@ApiModelProperty(value = "用户标签的id")
    private String tagId;
    /**
     * 性别：男（1）女（2）
     */
	@ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)
     */
	@ApiModelProperty(value = "客户端版本")
    private String clientPlatformType;
    /**
     * 国家信息
     */
	@ApiModelProperty(value = "国家信息")
    private String country;
    /**
     * 省份信息
     */
	@ApiModelProperty(value = "省份信息")
    private String province;
    /**
     * 城市信息
     */
	@ApiModelProperty(value = "城市信息")
    private String city;
    /**
     * 语言信息
     */
	@ApiModelProperty(value = "语言信息")
    private String language;
}
