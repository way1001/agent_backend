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
import com.aiforest.cloud.common.data.mybatis.typehandler.ArrayLongTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * 微信用户
 *
 * @author JL
 * @date 2019-03-25 15:39:39
 */
@Data
@ApiModel(description = "微信用户")
@TableName("wx_user")
@EqualsAndHashCode(callSuper = true)
public class WxUser extends Model<WxUser> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
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
   * 备注信息
   */
    private String remark;
    /**
   * 逻辑删除标记（0：显示；1：隐藏）
   */
    private String delFlag;
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
   * 应用类型(1:小程序，2:公众号)
   */
	@ApiModelProperty(value = "应用类型")
    private String appType;
    /**
   * 是否订阅（0：是；1：否；2：网页授权用户）
   */
	@ApiModelProperty(value = "是否订阅")
    private String subscribe;
    /**
   * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
   */
	@ApiModelProperty(value = "返回用户关注的渠道来源")
    private String subscribeScene;
    /**
   * 关注时间
   */
	@ApiModelProperty(value = "关注时间")
    private LocalDateTime subscribeTime;
    /**
   * 关注次数
   */
	@ApiModelProperty(value = "关注次数")
    private Integer subscribeNum;
    /**
   * 取消关注时间
   */
	@ApiModelProperty(value = "取消关注时间")
    private LocalDateTime cancelSubscribeTime;
    /**
   * 用户标识
   */
	@ApiModelProperty(value = "用户标识")
    private String openId;
    /**
   * 昵称
   */
	@ApiModelProperty(value = "昵称")
    private String nickName;
    /**
   * 性别（1：男，2：女，0：未知）
   */
	@ApiModelProperty(value = "性别")
    private String sex;
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
   * 手机号码
   */
	@ApiModelProperty(value = "手机号码")
    private String phone;
    /**
   * 用户语言
   */
	@ApiModelProperty(value = "用户语言")
    private String language;
    /**
   * 头像
   */
	@ApiModelProperty(value = "头像")
    private String headimgUrl;
    /**
   * union_id
   */
	@ApiModelProperty(value = "union_id")
    private String unionId;
    /**
   * 用户组
   */
	@ApiModelProperty(value = "用户组")
    private String groupId;
    /**
   * 标签列表
   */
	@ApiModelProperty(value = "标签列表")
	@TableField(typeHandler = ArrayLongTypeHandler.class, jdbcType= JdbcType.VARCHAR)
    private Long[] tagidList;
	/**
	 * 二维码扫码场景
	 */
	@ApiModelProperty(value = "二维码扫码场景")
	private String qrSceneStr;
	/**
	 * 地理位置纬度
	 */
	@ApiModelProperty(value = "地理位置纬度")
	private Double latitude;
	/**
	 * 地理位置经度
	 */
	@ApiModelProperty(value = "地理位置经度")
	private Double longitude;
	/**
	 * 地理位置精度
	 */
	@ApiModelProperty(value = "地理位置精度")
	@TableField(value = "`precision`")
	private Double precision;
    /**
   * 会话密钥
   */
	@ApiModelProperty(value = "会话密钥")
    private String sessionKey;
	/**
	 * 商城用户ID
	 */
	@ApiModelProperty(value = "商城用户ID")
	private String mallUserId;
	/**
	 * 房产用户ID
	 */
	@ApiModelProperty(value = "房产用户ID")
	private String estateUserId;

	/**
	 * 三方session
	 */
	@TableField(exist = false)
	private String thirdSessionKey;
}
