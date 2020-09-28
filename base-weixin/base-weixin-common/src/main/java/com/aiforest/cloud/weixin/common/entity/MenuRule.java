/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.common.entity;

import lombok.Data;

/**
 * 微信自定义菜单分组
 *
 * @author JL
 * @date 2020-02-22 19:34:22
 */
@Data
public class MenuRule {
    private static final long serialVersionUID=1L;

    /**
     * 用户标签的id
     */
    private String tag_id;

    private String id;

    private String menuType;

	private String name;
}
