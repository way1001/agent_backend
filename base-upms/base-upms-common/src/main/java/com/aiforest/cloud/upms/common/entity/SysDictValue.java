package com.aiforest.cloud.upms.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 字典项
 *
 */
@Data
@ApiModel(description = "字典项")
@EqualsAndHashCode(callSuper = true)
public class SysDictValue extends Model<SysDictValue> {
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@ApiModelProperty(value = "PK")
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 *字典ID
	 */
	@ApiModelProperty(value = "字典ID")
	private String dictId;
	/**
	 * 数据值
	 */
	@ApiModelProperty(value = "数据值")
	private String value;
	/**
	 * 标签名
	 */
	@ApiModelProperty(value = "标签名")
	private String label;
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	private String type;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息")
	private String remarks;
	/**
	 * 删除标记
	 */
	@ApiModelProperty(value = "删除标记")
	private String delFlag;

}
