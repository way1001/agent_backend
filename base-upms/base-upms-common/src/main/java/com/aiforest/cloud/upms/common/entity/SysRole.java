package com.aiforest.cloud.upms.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author
 */
@Data
@ApiModel(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "PK")
	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "角色名称")
	@NotNull(message = "角色名称 不能为空")
	private String roleName;

	@ApiModelProperty(value = "角色标识")
	@NotNull(message = "角色标识 不能为空")
	private String roleCode;

	@ApiModelProperty(value = "角色描述")
	@NotNull(message = "角色描述 不能为空")
	private String roleDesc;

	@ApiModelProperty(value = "数据权限")
	@NotNull(message = "数据权限类型 不能为空")
	private Integer dsType;

	private String dsScope;

	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
//	@TableLogic
	@ApiModelProperty(value = "删除标识")
	private String delFlag;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
