/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.aiforest.cloud.estate.common.entity.Attention;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 房产点评
 *
 * @author way
 * @date 2020-04-09 09:05:34
 */
@Data
public class DepreciateDTO {

	@JsonProperty(value = "row")
	private Attention attention;

	private String thing1;

    private String character_string2;

    private String character_string3;

    private String thing4;

}
