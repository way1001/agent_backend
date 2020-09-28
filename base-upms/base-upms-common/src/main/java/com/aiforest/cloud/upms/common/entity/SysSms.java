package com.aiforest.cloud.upms.common.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 短信
 * </p>
 *
 * @author
 */
@Data
@ApiModel(description = "短信")
@EqualsAndHashCode(callSuper = true)
public class SysSms extends Model<SysSms> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "签名")
	private String signName;
	@ApiModelProperty(value = "号码")
	private String phoneNumbers;
	@ApiModelProperty(value = "模板编码")
	private String templateCode;
	@ApiModelProperty(value = "模板参数")
	private String templateParam;
}
