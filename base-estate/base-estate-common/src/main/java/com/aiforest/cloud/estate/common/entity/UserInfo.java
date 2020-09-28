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
 * 房产用户
 *
 * @author way
 * @date 2020-04-03 15:43:34
 */
@Data
@TableName("user_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产用户")
public class UserInfo extends Model<UserInfo> {
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
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private Integer userCode;
    /**
     * 来源应用类型1、小程序；2、公众号
     */
    @ApiModelProperty(value = "来源应用类型1、小程序；2、公众号")
    private String appType;
    /**
     * 来源应用id
     */
    @ApiModelProperty(value = "来源应用id")
    private String appId;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;
    /**
     * 用户等级（0：普通用户，1：管理用户）
     */
    @ApiModelProperty(value = "用户等级（0：普通用户，1：管理用户）")
    private Integer userGrade;
    /**
     * 用户角色（0：用户，101：经理，102：销售秘书，103：财务，105：业务员）
     */
    @ApiModelProperty(value = "用户角色（0：用户，101：经理，102：销售秘书，103：财务，105：业务员）")
    private Integer userRole;
    /**
     * 当前佣金
     */
    @ApiModelProperty(value = "当前佣金")
    private Integer commissionCurrent;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 性别（1：男，2：女，0：未知）
     */
    @ApiModelProperty(value = "性别（1：男，2：女，0：未知）")
    private String sex;
	/**
	 * 用户类型（0：普通；1：管理）
	 */
	@ApiModelProperty(value = "用户类型（0：普通；1：管理）")
	private String userType;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headimgUrl;
    /**
     * 所在城市
     */
    @ApiModelProperty(value = "所在城市")
    private String city;
    /**
     * 所在国家
     */
    @ApiModelProperty(value = "所在国家")
    private String country;
    /**
     * 所在省份
     */
    @ApiModelProperty(value = "所在省份")
    private String province;
	/**
	 * 邀请人ID
	 */
	@ApiModelProperty(value = "邀请人ID")
	private String invitee;
	/**
	 * 业务指向ID
	 */
	@ApiModelProperty(value = "业务指向ID")
	private String distribution;
	/**
	 * 用户二维码
	 */
	@ApiModelProperty(value = "用户二维码")
	private String userQrcode;

	@TableField(exist = false)
	private UserInfo inviteeUser;
}
