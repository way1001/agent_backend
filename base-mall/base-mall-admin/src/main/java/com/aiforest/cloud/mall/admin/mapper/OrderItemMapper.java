/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aiforest.cloud.mall.common.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 商城订单详情
 *
 * @author JL
 * @date 2019-09-10 15:31:40
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

	List<OrderItem> selectList2(OrderItem orderItem);

	OrderItem selectById2(Serializable id);
}
