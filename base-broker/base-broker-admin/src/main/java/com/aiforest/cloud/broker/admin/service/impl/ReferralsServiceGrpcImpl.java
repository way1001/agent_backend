package com.aiforest.cloud.broker.admin.service.impl;

import com.aiforest.cloud.broker.admin.service.ReferralsService;
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.aiforest.cloud.common.grpc.api.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;
import java.time.ZoneOffset;

@GrpcService
@AllArgsConstructor
public class ReferralsServiceGrpcImpl extends ReferralsServiceGrpc.ReferralsServiceImplBase{

	private final ReferralsService referralsService;

	@Override
	public void get(ReferralsGetRequest request, StreamObserver<ReferralsGetResponse> responseObserver) {
		if (request.getId() == null || request.getId().equals("")) {
			responseObserver.onCompleted();
		}
		ReferralsGetResponse.Builder builder = ReferralsGetResponse.newBuilder();
		Referrals referrals = referralsService.getById(request.getId());
		if (referrals != null) {
			Instant javaTimeInstant = referrals.getCreateTime().toInstant(ZoneOffset.UTC);
			Timestamp timestamp = Timestamp.newBuilder()
					.setSeconds(javaTimeInstant.getEpochSecond())
					.setNanos(javaTimeInstant.getNano())
					.build();

			builder.setReferrals(
					com.aiforest.cloud.common.grpc.api.Referrals.newBuilder().setId(referrals.getId())
					.setAffiliationId(referrals.getAffiliationId())
					.setCreateTime(timestamp)
					.setPhone(referrals.getPhone() != null ? referrals.getPhone() : "")
					.setCustomerName(referrals.getCustomerName() != null ? referrals.getCustomerName() : "")
					.setGender(referrals.getGender() != null ? referrals.getGender() : "")
					.setDescription(referrals.getDescription() != null ? referrals.getDescription() : "")
					.setStaffId(referrals.getStaffId() != null ? referrals.getStaffId() : "")
					.setInstanceId(referrals.getInstanceId() != null ? referrals.getInstanceId() : "")
					.setWorkflowStatus(referrals.getWorkflowStatus() != null ? referrals.getWorkflowStatus() : "").build());

		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();

	}

	@Override
	public void del(ReferralsDelRequest request, StreamObserver<ReferralsDelResponse> responseObserver) {
		if (request.getId() == null || request.getId().equals("")) {
			responseObserver.onCompleted();
		}
		ReferralsDelResponse.Builder builder = ReferralsDelResponse.newBuilder();
		Referrals referrals = referralsService.getById(request.getId());
		if (referrals != null) {

			referrals.setDelFlag("1");
			builder.setSucceed(referralsService.updateById(referrals) ? "ok" : "fail");

		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();

	}

	@Override
	public void page(ReferralsPageRequest request, StreamObserver<ReferralsPageResponse> responseObserver) {

		if (request.getBrokerId() == null || request.getBrokerId().equals("")) {
			responseObserver.onCompleted();
		}

		Page page = new Page();

		page.setCurrent(request.getCurrentPage());

		page.setSize(request.getPageSize());

		Referrals referrals = new Referrals();

		referrals.setBrokerId(request.getBrokerId());

		referrals.setTenantId(request.getTenantId());

		referrals.setAffiliationId(request.getAffiliationId());

		referrals.setCustomerName(request.getCustomerName());

		referrals.setPhone(request.getPhone());

		IPage<Referrals> referralsList = referralsService.page1(page, referrals);

		ReferralsPageResponse.Builder builder = ReferralsPageResponse.newBuilder();

		if (referralsList.getTotal() > 0) {

			builder.setCurrentPage((int)referralsList.getCurrent())
					.setPageSize((int)referralsList.getSize())
					.setTotal((int)referralsList.getTotal());

			referralsList.getRecords().forEach( item -> {
				Instant javaTimeInstant = item.getCreateTime().toInstant(ZoneOffset.UTC);
				Timestamp timestamp = Timestamp.newBuilder()
						.setSeconds(javaTimeInstant.getEpochSecond())
						.setNanos(javaTimeInstant.getNano())
						.build();
				builder.addReferrals(com.aiforest.cloud.common.grpc.api.Referrals.newBuilder().setId(item.getId())
					.setAffiliationId(item.getAffiliationId())
					.setCreateTime(timestamp)
					.setPhone(item.getPhone() != null ? item.getPhone() : "")
					.setCustomerName(item.getCustomerName() != null ? item.getCustomerName() : "")
					.setGender(item.getGender() != null ? item.getGender() : "")
					.setDescription(item.getDescription() != null ? item.getDescription() : "")
					.setStaffId(item.getStaffId() != null ? item.getStaffId() : "")
					.setInstanceId(item.getInstanceId() != null ? item.getInstanceId() : "")
					.setWorkflowStatus(item.getWorkflowStatus() != null ? item.getWorkflowStatus() : "").build());
			});

		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();
	}

}