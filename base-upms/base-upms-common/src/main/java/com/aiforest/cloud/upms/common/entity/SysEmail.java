package com.aiforest.cloud.upms.common.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 邮件
 * </p>
 *
 * @author
 */
@Data
@ApiModel(description = "邮件")
@EqualsAndHashCode(callSuper = true)
public class SysEmail extends Model<SysEmail> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "PK")
	private String id;
	@ApiModelProperty(value = "收件人")
	private String to;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;

}
