package com.aiforest.cloud.estate.admin.service.impl;

import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.grpc.api.*;
import com.aiforest.cloud.estate.admin.service.SalesmanService;
import com.aiforest.cloud.estate.common.vo.SalesmanVO;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SalesmanServiceImpl implements SalesmanService {

	@GrpcClient("base-broker-admin")
	private SalesmanServiceGrpc.SalesmanServiceBlockingStub salesmanServiceGrpc;

	@Override
	public SalesmanGetResponse get(String id) {
		// 创建请求
		SalesmanGetRequest request = SalesmanGetRequest.newBuilder()
				.setId(id)
				.setTenantId(TenantContextHolder.getTenantId())
				.build();
		// 执行 gRPC 请求
		SalesmanGetResponse response = salesmanServiceGrpc.get(request);
		// 响应
		return response;
	}

	@Override
	public List<SalesmanVO> getAll(String affiliationId) {
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

		return salesmanVOList;
	}
}
