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
import com.aiforest.cloud.estate.common.dto.ReferralsDTO;
import com.aiforest.cloud.estate.common.vo.ReferralsPageVO;
import com.aiforest.cloud.estate.common.vo.ReferralsVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


/**
 * 房产布告
 *
 * @author way
 * @date 2020-04-03 9:50:56
 */
@Slf4j
@RestController
@RequestMapping("/api/es/referrals")
@Api(value = "referrals", tags = "推荐")
public class ReferralsApi {

	@GrpcClient("base-broker-admin")
	private ReferralsServiceGrpc.ReferralsServiceBlockingStub referralsServiceGrpc;

	@GetMapping("/get")
	public R get(@RequestParam("id") String id) {
		// 创建请求
		ReferralsGetRequest request = ReferralsGetRequest.newBuilder()
				.setId(id)
				.build();
		// 执行 gRPC 请求
		ReferralsGetResponse response = referralsServiceGrpc.get(request);
		// 响应
		return R.ok(response);
	}

	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		ReferralsDelRequest request = ReferralsDelRequest.newBuilder()
				.setId(id)
				.build();

		ReferralsDelResponse response = referralsServiceGrpc.del(request);

		return R.ok(response);
	}

	//TODO @RequestParam("affiliationId") not work!  Modify  @RequestParam("affId") work well

	@PostMapping("/page")
	public R getPage(@RequestBody ReferralsDTO referralsDTO) {
		// 创建请求
		ReferralsPageRequest request = ReferralsPageRequest.newBuilder()
				.setCurrentPage(referralsDTO.getCurrentPage())
				.setPageSize(referralsDTO.getPageSize())
				.setBrokerId(referralsDTO.getBrokerId())
				.setCurrentHandler(referralsDTO.getCurrentHandler() != null ? referralsDTO.getCurrentHandler() : "")
				.setAffiliationId(referralsDTO.getAffiliationId())
				.setTenantId(TenantContextHolder.getTenantId())
				.setCustomerName(referralsDTO.getCustomerName() != null ? referralsDTO.getCustomerName() : "")
				.setPhone(referralsDTO.getPhone() != null ? referralsDTO.getPhone() : "")
				.build();
		// 执行 gRPC 请求
		ReferralsPageResponse response = referralsServiceGrpc.page(request);

//			try {
//				return R.ok(JsonFormat.printer().print(response.getSalesmenList().get(0)));
//			} catch (JsonSyntaxException | InvalidProtocolBufferException e) {
//				return R.failed("Response conversion Error");
//			}

		ReferralsPageVO referralsPageVO = new ReferralsPageVO();

		referralsPageVO.setCurrentPage(response.getCurrentPage());
		referralsPageVO.setPageSize(response.getPageSize());
		referralsPageVO.setTotal(response.getTotal());

		referralsPageVO.setReferrals(response.getReferralsList().stream().map (
				item -> {
					ReferralsVO referralsVO = new ReferralsVO();
					referralsVO.setId(item.getId());
					referralsVO.setAffiliationId(item.getAffiliationId());
					referralsVO.setCreateTime(item.getCreateTime().toString());
					referralsVO.setGender(item.getGender());
					referralsVO.setDescription(item.getDescription());
					referralsVO.setCurrentHandler(item.getCurrentHandler());
					referralsVO.setCustomerName(item.getCustomerName());
					referralsVO.setPhone(item.getPhone());
					referralsVO.setStaffId(item.getStaffId());
					referralsVO.setInstanceId(item.getInstanceId());
					referralsVO.setWorkflowStatus(item.getWorkflowStatus());
					return referralsVO;
				}).collect(Collectors.toList()));

        return R.ok(referralsPageVO);
	}

}
