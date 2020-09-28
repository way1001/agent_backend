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
import com.aiforest.cloud.common.data.mybatis.typehandler.ArrayStringTypeHandler;
import com.aiforest.cloud.mall.common.dto.SpuSpecDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import org.apache.ibatis.type.JdbcType;

/**
 * spu商品
 *
 * @author JL
 * @date 2019-08-12 16:25:10
 */
@Data
@TableName("goods_spu")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "spu商品")
public class GoodsSpu extends Model<GoodsSpu> {
private static final long serialVersionUID = 1L;

    /**
   * PK
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
   * spu编码
   */
	@ApiModelProperty(value = "spu编码")
    private String spuCode;
    /**
   * spu名字
   */
	@ApiModelProperty(value = "spu名字")
    private String name;
    /**
   * 卖点
   */
	@ApiModelProperty(value = "卖点")
    private String sellPoint;
    /**
   * 描述
   */
	@ApiModelProperty(value = "描述")
    private String description;
    /**
	 * 一级分类ID
	 */
	@ApiModelProperty(value = "一级分类ID")
    private String categoryFirst;
	/**
	 * 二级分类ID
	 */
	@ApiModelProperty(value = "二级分类ID")
	private String categorySecond;
    /**
   * 商品主图
   */
	@ApiModelProperty(value = "商品主图")
	@TableField(typeHandler = ArrayStringTypeHandler.class, jdbcType= JdbcType.VARCHAR)
    private String[] picUrls;
    /**
   * 是否上架（0否 1是）
   */
	@ApiModelProperty(value = "是否上架（0否 1是）")
    private String shelf;
    /**
   * 排序字段
   */
	@ApiModelProperty(value = "排序字段")
    private Integer sort;
	/**
	 * 最低价
	 */
	@ApiModelProperty(value = "最低价")
	private BigDecimal priceDown;
	/**
	 * 最高价
	 */
	@ApiModelProperty(value = "最高价")
	private BigDecimal priceUp;
	/**
	 * 销量
	 */
	@ApiModelProperty(value = "销量")
	private Integer saleNum;
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
	 * 0统一规格；1多规格
	 */
	@ApiModelProperty(value = "0统一规格；1多规格")
	private String specType;
    /**
   * 逻辑删除标记（0：显示；1：隐藏）
   */
	@ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
	/**
	 *积分赠送开关（1开 0关）
	 */
	@ApiModelProperty(value = "积分赠送开关（1开 0关）")
	private String pointsGiveSwitch;
	/**
	 * 积分赠送数量
	 */
	@ApiModelProperty(value = "积分赠送数量")
	private Integer pointsGiveNum;
	/**
	 * 积分抵扣开关（1开 0关）
	 */
	@ApiModelProperty(value = "积分抵扣开关（1开 0关）")
	private String pointsDeductSwitch;
	/**
	 * 积分抵扣商品金额比例（0~100）
	 */
	@ApiModelProperty(value = "积分抵扣商品金额比例（0~100）")
	private Integer pointsDeductScale;
	/**
	 * 1积分数可抵多少元
	 */
	@ApiModelProperty(value = "1积分数可抵多少元")
	private BigDecimal pointsDeductAmount;
	/**
	 * 运费模板ID
	 */
	@ApiModelProperty(value = "运费模板ID")
	private String freightTemplatId;
	/**
	 * 发货地ID
	 */
	@ApiModelProperty(value = "发货地ID")
	private String deliveryPlaceId;

	/**
	 * 保障服务
	 */
	@TableField(exist = false)
	private List<String> ensureIds;

	@TableField(exist = false)
	private List<GoodsSku> skus;

	@TableField(exist = false)
	private String collectId;
	/**
	 * 可领电子券
	 */
	@TableField(exist = false)
	private List<CouponInfo> listCouponInfo;

	@TableField(exist = false)
	private List<SpuSpecDTO> spuSpec;

	@TableField(exist = false)
	private FreightTemplat freightTemplat;

	@TableField(exist = false)
	private DeliveryPlace deliveryPlace;
}
