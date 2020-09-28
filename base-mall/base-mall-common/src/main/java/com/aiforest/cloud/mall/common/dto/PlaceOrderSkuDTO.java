/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 下单参数
 *
 * @author JL
 * @date 2019-08-13 10:18:34
 */
@Data
@ApiModel(description = "下单参数")
public class PlaceOrderSkuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品Id
	 */
	@ApiModelProperty(value = "商品Id")
	private String spuId;
    /**
	 * skuId
	 */
	@ApiModelProperty(value = "skuId")
    private String skuId;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Integer quantity;
	/**
	 * 支付金额
	 */
	@ApiModelProperty(value = "支付金额")
	private BigDecimal paymentPrice;
	/**
	 * 运费金额
	 */
	@ApiModelProperty(value = "运费金额")
	private BigDecimal freightPrice;
	/**
	 * 支付积分
	 */
	@ApiModelProperty(value = "支付积分")
	private Integer paymentPoints;
	/**
	 * 电子券支付金额
	 */
	@ApiModelProperty(value = "电子券支付金额")
	private BigDecimal paymentCouponPrice;
	/**
	 * 积分抵扣金额
	 */
	@ApiModelProperty(value = "积分抵扣金额")
	private BigDecimal paymentPointsPrice;
	/**
	 * 用户电子券ID
	 */
	@ApiModelProperty(value = "用户电子券ID")
	private String couponUserId;
}
