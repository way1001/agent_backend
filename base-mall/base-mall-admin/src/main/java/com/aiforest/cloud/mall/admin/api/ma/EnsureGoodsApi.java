/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.mall.admin.api.ma;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.mall.admin.service.EnsureGoodsService;
import com.aiforest.cloud.mall.common.entity.Ensure;
import com.aiforest.cloud.mall.common.entity.EnsureGoods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品保障
 *
 * @author JL
 * @date 2020-02-10 00:02:09
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ma/ensuregoods")
@Api(value = "ensuregoods", tags = "商品保障API")
public class EnsureGoodsApi {

    private final EnsureGoodsService ensureGoodsService;

    /**
     * 通过spuID，查询商品保障
     * @param ensureGoods 商品保障
     * @return
     */
	@ApiOperation(value = "通过spuID，查询商品保障")
    @GetMapping("/listEnsureBySpuId")
    public R listEnsureBySpuId(EnsureGoods ensureGoods) {
		List<Ensure> listEnsure = ensureGoodsService.listEnsureBySpuId(ensureGoods.getSpuId());
        return R.ok(listEnsure);
    }
}
