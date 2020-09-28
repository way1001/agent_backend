package com.aiforest.cloud.estate.common.dto;

import com.aiforest.cloud.upms.common.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author
 * 机构树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AffiliationTree extends TreeNode {
	private String projectName;
	/**
	 * 机构编码
	 */
	private String id;
	/**
	 * 排序
	 */
	private Integer sort;
}
