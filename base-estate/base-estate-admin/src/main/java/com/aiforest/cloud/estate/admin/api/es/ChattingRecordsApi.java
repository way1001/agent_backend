package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.grpc.api.*;
import com.aiforest.cloud.estate.common.dto.ChattingRecordsDTO;
import com.aiforest.cloud.estate.common.dto.ChattingRecordsPageDTO;
import com.aiforest.cloud.estate.common.vo.ChattingRecordsPageVO;
import com.aiforest.cloud.estate.common.vo.ChattingRecordsVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/es/chattingrecords")
@Api(value = "referrals", tags = "聊天记录")
public class ChattingRecordsApi {

	@GrpcClient("base-broker-admin")
	private ChattingRecordsServiceGrpc.ChattingRecordsServiceBlockingStub chattingRecordsServiceGrpc;

	@PostMapping("/unreadcount")
	public R unReadCount(@RequestBody ChattingRecordsDTO chattingRecordsDTO) {
		// 创建请求
		ChattingRecordsUnReadCountRequest request = ChattingRecordsUnReadCountRequest.newBuilder()
				.setTenantId(chattingRecordsDTO.getTenantId())
				.setAffiliationId(chattingRecordsDTO.getAffiliationId())
				.setUserId(chattingRecordsDTO.getUserId())
				.setSalesmanId(chattingRecordsDTO.getSalesmanId())
				.build();
		// 执行 gRPC 请求
		ChattingRecordsUnReadCountResponse response = chattingRecordsServiceGrpc.unRead(request);
		// 响应
		return R.ok(response);
	}

	@PostMapping("/received")
	public R received(@RequestBody ChattingRecordsDTO chattingRecordsDTO) {
		// 创建请求
		ChattingRecordsReceivedRequest request = ChattingRecordsReceivedRequest.newBuilder()
				.setTenantId(chattingRecordsDTO.getTenantId())
				.setAffiliationId(chattingRecordsDTO.getAffiliationId())
				.setUserId(chattingRecordsDTO.getUserId())
				.setSalesmanId(chattingRecordsDTO.getSalesmanId())
				.build();
		// 执行 gRPC 请求
		ChattingRecordsReceivedResponse response = chattingRecordsServiceGrpc.received(request);
		// 响应
		return R.ok(response);
	}

	@PostMapping("/send")
	public R send(@RequestBody ChattingRecordsDTO chattingRecordsDTO) {
		// 创建请求
		ChattingRecordsSendRequest request = ChattingRecordsSendRequest.newBuilder()
				.setTenantId(chattingRecordsDTO.getTenantId())
				.setAffiliationId(chattingRecordsDTO.getAffiliationId())
				.setUserId(chattingRecordsDTO.getUserId())
				.setSalesmanId(chattingRecordsDTO.getSalesmanId())
				.setMessageblob(chattingRecordsDTO.getMessageblob())
				.setType(chattingRecordsDTO.getType())
				.build();
		// 执行 gRPC 请求
		ChattingRecordsSendResponse response = chattingRecordsServiceGrpc.send(request);
		// 响应
		return R.ok(response);
	}

	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		ChattingRecordsDelRequest request = ChattingRecordsDelRequest.newBuilder()
				.setId(id)
				.build();

		ChattingRecordsDelResponse response = chattingRecordsServiceGrpc.del(request);

		return R.ok(response);
	}

	//TODO @RequestParam("affiliationId") not work!  Modify  @RequestParam("affId") work well

	@PostMapping("/page")
	public R getPage(@RequestBody ChattingRecordsPageDTO chattingRecordsPageDTO) {
		ChattingRecordsPageRequest request = ChattingRecordsPageRequest.newBuilder()
				.setCurrentPage(chattingRecordsPageDTO.getCurrentPage())
				.setPageSize(chattingRecordsPageDTO.getPageSize())
				.setUserId(chattingRecordsPageDTO.getUserId())
				.setAffiliationId(chattingRecordsPageDTO.getAffiliationId())
				.setTenantId(TenantContextHolder.getTenantId())
				.build();
		// 执行 gRPC 请求
		ChattingRecordsPageResponse response = chattingRecordsServiceGrpc.page(request);

//			try {
//				return R.ok(JsonFormat.printer().print(response.getSalesmenList().get(0)));
//			} catch (JsonSyntaxException | InvalidProtocolBufferException e) {
//				return R.failed("Response conversion Error");
//			}

		ChattingRecordsPageVO chattingRecordsPageVO = new ChattingRecordsPageVO();

		chattingRecordsPageVO.setCurrentPage(response.getCurrentPage());
		chattingRecordsPageVO.setPageSize(response.getPageSize());
		chattingRecordsPageVO.setTotal(response.getTotal());

		chattingRecordsPageVO.setChattingRecordsVOList(response.getChattingRecordsList().stream().map (
				item -> {
					ChattingRecordsVO chattingRecordsVO = new ChattingRecordsVO();
					chattingRecordsVO.setId(item.getId());
					chattingRecordsVO.setAffiliationId(item.getAffiliationId());
					chattingRecordsVO.setCreateTime(item.getCreateTime());
					chattingRecordsVO.setTenantId(item.getTenantId());
					chattingRecordsVO.setReceiverId(item.getReceiverId());
					chattingRecordsVO.setSenderId(item.getSenderId());
					chattingRecordsVO.setMessageblob(item.getMessageblob());
					chattingRecordsVO.setStatus(item.getStatus());
					chattingRecordsVO.setType(item.getType());
					return chattingRecordsVO;
				}).collect(Collectors.toList()));

		return R.ok(chattingRecordsPageVO);
	}

}
