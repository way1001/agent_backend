package com.aiforest.cloud.weixin.common.dto;

import com.aiforest.cloud.weixin.common.entity.WxApp;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * wxApp树
 */
@Data
public class WxAppTree {
	protected String id;
	protected String parentId;
	private Integer sort;
	private String name;
	protected List<WxApp> children = new ArrayList<>();

}
