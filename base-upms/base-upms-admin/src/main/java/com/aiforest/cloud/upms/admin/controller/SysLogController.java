package com.aiforest.cloud.upms.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.upms.admin.service.SysLogService;
import com.aiforest.cloud.upms.common.entity.SysLog;
import com.aiforest.cloud.upms.common.vo.PreLogVO;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.security.annotation.Inside;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class SysLogController {
	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
//	@PreAuthorize("@ato.hasAuthority('sys:log:index')")
	public R getLogPage(Page page, SysLog sysLog) {
		return R.ok(sysLogService.page(page, Wrappers.query(sysLog)));
	}

	/**
	 * 删除日志
	 *
	 * @param id ID
	 * @return
	 */
	@ApiOperation(value = "删除日志")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('sys:log:del')")
	public R removeById(@PathVariable String id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 *
	 * @param sysLog 日志实体
	 * @return
	 */
	@ApiOperation(value = "插入日志")
	@Inside
	@PostMapping("/save")
	public R save(@Valid @RequestBody SysLog sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 *
	 * @param preLogVOList 日志实体
	 * @return ok/false
	 */
	@ApiOperation(value = "批量插入异常日志")
	@PostMapping("/logs")
	public R saveBatchLogs(@RequestBody List<PreLogVO> preLogVOList) {
		return R.ok(sysLogService.saveBatchLogs(preLogVOList));
	}
}
