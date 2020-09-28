/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.grpc.api.*;
import com.aiforest.cloud.estate.common.vo.SalesmanVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@RequestMapping("/api/es/salesmen")
@Api(value = "salesmen", tags = "置业顾问")
public class SalesmenApi {

	@GrpcClient("base-broker-admin")
	private SalesmanServiceGrpc.SalesmanServiceBlockingStub salesmanServiceGrpc;

	@GetMapping("/get")
	public R get(@RequestParam("id") String id) {
		// 创建请求
		SalesmanGetRequest request = SalesmanGetRequest.newBuilder()
				.setId(id)
				.setTenantId(TenantContextHolder.getTenantId())
				.build();
		// 执行 gRPC 请求
		SalesmanGetResponse response = salesmanServiceGrpc.get(request);
		// 响应
		return R.ok(response);
	}

	//TODO @RequestParam("affiliationId") not work!  Modify  @RequestParam("affId") work well

	@GetMapping("/all")
	public R getAll(@RequestParam("affId") String affiliationId) {
		// 创建请求
		SalesmanGetAllRequest request = SalesmanGetAllRequest.newBuilder()
				.setAffiliationId(affiliationId)
				.setTenantId(TenantContextHolder.getTenantId())
				.build();
		// 执行 gRPC 请求
		SalesmanGetAllResponse response = salesmanServiceGrpc.getAll(request);

//			try {
//				return R.ok(JsonFormat.printer().print(response.getSalesmenList().get(0)));
//			} catch (JsonSyntaxException | InvalidProtocolBufferException e) {
//				return R.failed("Response conversion Error");
//			}

		List<SalesmanVO> salesmanVOList = response.getSalesmenList().stream()
				.map(item -> {
					SalesmanVO salesmanVO = new SalesmanVO();
					salesmanVO.setId(item.getId());
					salesmanVO.setUserCode(item.getUserCode());
					salesmanVO.setUserRole(item.getUserRole());
					salesmanVO.setPhone(item.getPhone());
					salesmanVO.setNickName(item.getNickName());
					salesmanVO.setRealName(item.getRealName());
					salesmanVO.setSex(item.getSex());
					salesmanVO.setUserStatus(item.getUserStatus());
					salesmanVO.setHeadimgUrl(item.getHeadimgUrl());
					salesmanVO.setUserQrcode(item.getUserQrcode());
		        return salesmanVO;
		}).collect(Collectors.toList());

        return R.ok(salesmanVOList);
	}

}
