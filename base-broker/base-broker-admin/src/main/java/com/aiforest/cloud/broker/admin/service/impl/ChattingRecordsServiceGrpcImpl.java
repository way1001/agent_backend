package com.aiforest.cloud.broker.admin.service.impl;

import com.aiforest.cloud.broker.admin.service.ChattingRecordsService;
import com.aiforest.cloud.common.grpc.api.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.format.DateTimeFormatter;
import java.util.List;


@GrpcService
@AllArgsConstructor
public class ChattingRecordsServiceGrpcImpl extends ChattingRecordsServiceGrpc.ChattingRecordsServiceImplBase {

	private final ChattingRecordsService chattingRecordsService;

	/**
	 */
	@Override
	public void unRead(ChattingRecordsUnReadCountRequest request,
					   StreamObserver<ChattingRecordsUnReadCountResponse> responseObserver) {
		if (request.getUserId() == null || request.getUserId().equals("")) {
			responseObserver.onCompleted();
		}
		ChattingRecordsUnReadCountResponse.Builder builder = ChattingRecordsUnReadCountResponse.newBuilder();
		int unReadCount = chattingRecordsService.count(Wrappers.<com.aiforest.cloud.broker.common.entity.ChattingRecords>lambdaQuery()
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getAffiliationId, request.getAffiliationId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getTenantId, request.getTenantId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getReceiverId, request.getUserId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getDelFlag, '0')
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getStatus,'0'));
		builder.setUnReadCount(unReadCount);
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();	}

	/**
	 */
	@Override
	public void received(ChattingRecordsReceivedRequest request,
						 StreamObserver<ChattingRecordsReceivedResponse> responseObserver) {
		if (request.getSalesmanId() == null || request.getSalesmanId().equals("") ||
				request.getUserId() == null || request.getUserId().equals("")) {
			responseObserver.onCompleted();
		}
		ChattingRecordsReceivedResponse.Builder builder = ChattingRecordsReceivedResponse.newBuilder();

		List<com.aiforest.cloud.broker.common.entity.ChattingRecords> chattingRecordsList = chattingRecordsService.list(Wrappers.<com.aiforest.cloud.broker.common.entity.ChattingRecords>lambdaQuery()
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getAffiliationId, request.getAffiliationId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getTenantId, request.getTenantId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getSenderId, request.getSalesmanId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getReceiverId, request.getUserId())
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getDelFlag, '0')
				.eq(com.aiforest.cloud.broker.common.entity.ChattingRecords :: getStatus,'0'));

        if(chattingRecordsList.size() > 0) {
			chattingRecordsService.updateRe(chattingRecordsList);
		}
		builder.setSucceed("ok");
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();	}

	@Override
	public void send(ChattingRecordsSendRequest request,
					 StreamObserver<ChattingRecordsSendResponse> responseObserver) {
		if (request.getSalesmanId() == null || request.getSalesmanId().equals("") ||
				request.getUserId() == null || request.getUserId().equals("")) {
			responseObserver.onCompleted();
		}
		ChattingRecordsSendResponse.Builder builder = ChattingRecordsSendResponse.newBuilder();
		com.aiforest.cloud.broker.common.entity.ChattingRecords chattingRecords = new com.aiforest.cloud.broker.common.entity.ChattingRecords();

		chattingRecords.setSenderId(request.getUserId());
		chattingRecords.setReceiverId(request.getSalesmanId());
		chattingRecords.setAffiliationId(request.getAffiliationId());
		chattingRecords.setTenantId(request.getTenantId());
		chattingRecords.setMessageblob(request.getMessageblob() != null ? request.getMessageblob() : "");
		chattingRecords.setType(request.getType() != null ? request.getType() : "0");
		boolean saveOk = chattingRecordsService.save(chattingRecords);

		builder.setSucceed(saveOk ? "ok" : "fail");
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();	}

	/**
	 */
	@Override
	public void del(ChattingRecordsDelRequest request,
					StreamObserver<ChattingRecordsDelResponse> responseObserver) {
		if (request.getId() == null || request.getId().equals("")) {
			responseObserver.onCompleted();
		}
		ChattingRecordsDelResponse.Builder builder = ChattingRecordsDelResponse.newBuilder();

		boolean delOk = chattingRecordsService.removeById(request.getId());

		builder.setSucceed(delOk ? "ok" : "fail");
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();	}

	/**
	 */
	@Override
	public void page(ChattingRecordsPageRequest request,
					 StreamObserver<ChattingRecordsPageResponse> responseObserver) {
		if (request.getUserId() == null || request.getUserId().equals("")) {
			responseObserver.onCompleted();
		}

		Page page = new Page();

		page.setCurrent(request.getCurrentPage());

		page.setSize(request.getPageSize());

		com.aiforest.cloud.broker.common.entity.ChattingRecords chattingRecords = new com.aiforest.cloud.broker.common.entity.ChattingRecords();

		chattingRecords.setSenderId(request.getUserId());

		chattingRecords.setReceiverId(request.getUserId());

		chattingRecords.setTenantId(request.getTenantId());

		chattingRecords.setAffiliationId(request.getAffiliationId());

		IPage<com.aiforest.cloud.broker.common.entity.ChattingRecords> chattingRecordsIPage = chattingRecordsService.page1(page, chattingRecords);

		ChattingRecordsPageResponse.Builder builder = ChattingRecordsPageResponse.newBuilder();

		if (chattingRecordsIPage.getTotal() > 0) {

			builder.setCurrentPage((int)chattingRecordsIPage.getCurrent())
					.setPageSize((int)chattingRecordsIPage.getSize())
					.setTotal((int)chattingRecordsIPage.getTotal());

			chattingRecordsIPage.getRecords().forEach( item -> {

				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				builder.addChattingRecords(ChattingRecords.newBuilder().setId(item.getId())
						.setTenantId(item.getAffiliationId())
						.setAffiliationId(item.getAffiliationId())
						.setCreateTime(df.format(item.getCreateTime()))
						.setSenderId(item.getSenderId())
						.setReceiverId(item.getReceiverId())
						.setMessageblob(item.getMessageblob())
						.setStatus(item.getStatus())
						.setType(item.getType()).build());
			});

		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();	}
}