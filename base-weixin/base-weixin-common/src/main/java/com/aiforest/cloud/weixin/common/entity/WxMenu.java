/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.common.entity;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.aiforest.cloud.common.data.mybatis.typehandler.JsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 自定义菜单
 *
 * @author JL
 * @date 2019-03-27 16:52:10
 */
@Data

@ApiModel(description = "自定义菜单")
@TableName("wx_menu")
@EqualsAndHashCode(callSuper = true)
public class WxMenu extends Model<WxMenu> {
private static final long serialVersionUID = 1L;

    /**
   * 菜单ID（click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、pic_weixin、location_select：保存key）
   */
	@ApiModelProperty(value = "PK")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
	/**
	 * 所属租户
	 */
	@ApiModelProperty(value = "所属租户")
	private String tenantId;
	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID")
	private String parentId;
	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 逻辑删除标记（0：显示；1：隐藏）
	 */
	private String delFlag;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "应用ID")
	private String appId;
    /**
   * 菜单类型click、view、miniprogram、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、pic_weixin、location_select、media_id、view_limited等
   */
	@ApiModelProperty(value = "菜单类型")
	@NotNull(message = "菜单类型不能为空")
    private String type;
	/**
	 * 菜单名
	 */
	@ApiModelProperty(value = "PK")
	@NotNull(message = "菜单名")
	private String name;
    /**
   * View：保存链接到url
   */
	@ApiModelProperty(value = "View：保存链接到url")
    private String url;
    /**
   * Img、voice、News：保存mediaID
   */
	@ApiModelProperty(value = "Img、voice、News：保存mediaID")
    private String repMediaId;
	/**
	 * 回复消息类型（text：文本；image：图片；voice：语音；video：视频；music：音乐；news：图文）
	 */
	@ApiModelProperty(value = "回复消息类型")
	private String repType;
	/**
	 * 素材名、视频和音乐的标题
	 */
	@ApiModelProperty(value = "素材名、视频和音乐的标题")
	private String repName;
    /**
   * Text:保存文字
   */
	@ApiModelProperty(value = "Text:保存文字")
    private String repContent;
	/**
	 * 小程序的appid
	 */
	@ApiModelProperty(value = "小程序的appid")
	private String maAppId;
	/**
	 * 小程序的页面路径
	 */
	@ApiModelProperty(value = "小程序的页面路径")
	private String maPagePath;
	/**
	 * 视频和音乐的描述
	 */
	@ApiModelProperty(value = "视频和音乐的描述")
	private String repDesc;
	/**
	 * 视频和音乐的描述
	 */
	@ApiModelProperty(value = "视频和音乐的描述")
	private String repUrl;
	/**
	 * 高质量链接
	 */
	@ApiModelProperty(value = "高质量链接")
	private String repHqUrl;
	/**
	 * 缩略图的媒体id
	 */
	@ApiModelProperty(value = "缩略图的媒体id")
	private String repThumbMediaId;
	/**
	 * 缩略图url
	 */
	@ApiModelProperty(value = "缩略图url")
	private String repThumbUrl;
	/**
	 * 菜单组ID
	 */
	@ApiModelProperty(value = "菜单组ID")
	private String menuRuleId;
	/**
	 * 图文消息的内容
	 */
	@TableField(typeHandler = JsonTypeHandler.class, jdbcType= JdbcType.VARCHAR)
	private JSONObject content;
}
