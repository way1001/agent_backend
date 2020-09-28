/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.broker.common.entity;

import com.aiforest.cloud.common.data.mybatis.typehandler.ArrayStringTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.type.JdbcType;

/**
 * 经纪人用户
 *
 * @author aiforest
 * @date 2020-08-31 08:43:29
 */
@Data
@TableName("user_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "经纪人用户")
public class UserInfo extends Model<UserInfo> {
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
     * 最后更新时间
     */
    @NotNull(message = "最后更新时间不能为空")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;
    /**
     * 用户编码
     */
    @NotNull(message = "用户编码不能为空")
    @ApiModelProperty(value = "用户编码")
    private Integer userCode;
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
    private String userRole;
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
     * 用户状态（0：在职；1：离职 2：停职）
     */
    @NotNull(message = "用户状态（0：在职；1：离职 2：停职）不能为空")
    @ApiModelProperty(value = "用户状态（0：在职；1：离职 2：停职）")
    private String userStatus;
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
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间")
    private LocalDateTime entryTime;
    /**
     * 用户二维码
     */
    @ApiModelProperty(value = "用户二维码")
    private String userQrcode;
    /**
     * 用户分享码
     */
    @ApiModelProperty(value = "用户分享码")
    private String userSharecode;
    /**
     * 
     */
    @ApiModelProperty(value = "")
	@TableField(typeHandler = ArrayStringTypeHandler.class, jdbcType= JdbcType.VARCHAR)
	private String[] affiliated;
    /**
     * 隶属组
     */
    @ApiModelProperty(value = "隶属组")
    private String memberGroup;
	/**
	 * 隶属组
	 */
	@ApiModelProperty(value = "验证码")
	private String captcha;
	/**
	 * 会话密钥
	 */
	@ApiModelProperty(value = "会话密钥")
	private String sessionKey;
	/**
	 * session创建时间戳
	 */
	@TableField(exist = false)
	private Long creation;

}
