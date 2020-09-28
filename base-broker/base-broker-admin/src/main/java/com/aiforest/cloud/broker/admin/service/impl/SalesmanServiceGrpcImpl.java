package com.aiforest.cloud.broker.admin.service.impl;

import com.aiforest.cloud.broker.admin.service.UserInfoService;
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.common.grpc.api.*;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

@GrpcService
@AllArgsConstructor
public class SalesmanServiceGrpcImpl extends SalesmanServiceGrpc.SalesmanServiceImplBase {

	private final UserInfoService userInfoService;

	@Override
	public void get(SalesmanGetRequest request, StreamObserver<SalesmanGetResponse> responseObserver) {
        if (request.getId() == null || request.getId().equals("")) {
			responseObserver.onCompleted();
		}
		SalesmanGetResponse.Builder builder = SalesmanGetResponse.newBuilder();

		UserInfo userInfo = userInfoService.getById1(request.getId(),request.getTenantId());
        if (userInfo != null) {
			builder.setSalesman(
					Salesman.newBuilder().setId(userInfo.getId())
							.setUserCode(userInfo.getUserCode())
							.setPhone(userInfo.getPhone() != null ? userInfo.getPhone() : "")
							.setUserRole(userInfo.getUserRole() != null ? userInfo.getUserRole() : "")
							.setNickName(userInfo.getNickName() != null ? userInfo.getNickName() : "")
							.setRealName(userInfo.getRealName() != null ? userInfo.getRealName() : "")
							.setSex(userInfo.getSex()  != null ? userInfo.getSex() : "")
							.setUserStatus(userInfo.getUserStatus() != null ? userInfo.getUserStatus() : "")
							.setHeadimgUrl(userInfo.getHeadimgUrl() != null ? userInfo.getHeadimgUrl() : "")
							.setUserQrcode(userInfo.getUserQrcode() != null ? userInfo.getUserQrcode() : "").build());
//				.setName("没有昵称：" + request.getId())
//				.setGender(request.getId() % 2 + 1);
		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();

	}

	@Override
	@SqlParser(filter=true)
	public void getAll(SalesmanGetAllRequest request, StreamObserver<SalesmanGetAllResponse> responseObserver) {
		if (request.getAffiliationId() == null || request.getAffiliationId().equals("")) {
			responseObserver.onCompleted();
		}
		List<UserInfo> userInfoList = userInfoService.list1(request.getAffiliationId(), request.getTenantId());
		SalesmanGetAllResponse.Builder builder = SalesmanGetAllResponse.newBuilder();
		if (userInfoList.size() > 0) {
			userInfoList.forEach( item -> builder.addSalesmen(Salesman.newBuilder().setId(item.getId())
					.setUserCode(item.getUserCode())
					.setPhone(item.getPhone() != null ? item.getPhone() : "")
					.setUserRole(item.getUserRole() != null ? item.getUserRole() : "")
					.setNickName(item.getNickName() != null ? item.getNickName() : "")
					.setRealName(item.getRealName() != null ? item.getRealName() : "")
					.setSex(item.getSex()  != null ? item.getSex() : "")
					.setUserStatus(item.getUserStatus() != null ? item.getUserStatus() : "")
					.setHeadimgUrl(item.getHeadimgUrl() != null ? item.getHeadimgUrl() : "")
					.setUserQrcode(item.getUserQrcode() != null ? item.getUserQrcode() : "").build()));
		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();

	}

//	@Override
//	public void get(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
//		// 创建响应对象
//		UserGetResponse.Builder builder = UserGetResponse.newBuilder();
//		builder.setId(request.getId())
//				.setName("没有昵称：" + request.getId())
//				.setGender(request.getId() % 2 + 1);
//		// 返回响应
//		responseObserver.onNext(builder.build());
//		responseObserver.onCompleted();
//	}
//
//	@Override
//	public void create(UserCreateRequest request, StreamObserver<UserCreateResponse> responseObserver) {
//		// 创建响应对象
//		UserCreateResponse.Builder builder = UserCreateResponse.newBuilder();
//		builder.setId((int) (System.currentTimeMillis() / 1000));
//		// 返回响应
//		responseObserver.onNext(builder.build());
//		responseObserver.onCompleted();
//	}

}