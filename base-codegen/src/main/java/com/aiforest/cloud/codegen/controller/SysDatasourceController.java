package com.aiforest.cloud.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.codegen.entity.SysDatasource;
import com.aiforest.cloud.codegen.service.SysDatasourceService;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


/**
 * 数据源管理
 *
 * @author
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dsconf")
@Api(value = "dsconf", tags = "数据源管理")
public class SysDatasourceController {
	private final SysDatasourceService sysDatasourceService;

	/**
	 * 分页查询
	 *
	 * @param page              分页对象
	 * @param sysDatasource 数据源表
	 * @return
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	public R getSysDatasourceConfPage(Page page, SysDatasource sysDatasource) {
		return R.ok(sysDatasourceService.page(page, Wrappers.query(sysDatasource)));
	}

	/**
	 * 查询全部数据源
	 *
	 * @return
	 */
	@ApiOperation(value = "查询全部数据源")
	@GetMapping("/list")
	public R list() {
		return R.ok(sysDatasourceService.list());
	}


	/**
	 * 通过id查询数据源表
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询数据源表")
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
		return R.ok(sysDatasourceService.getById(id));
	}

	/**
	 * 新增数据源表
	 *
	 * @param sysDatasource 数据源表
	 * @return R
	 */
	@ApiOperation(value = "新增数据源表")
	@SysLog("新增数据源表")
	@PostMapping
	public R save(@RequestBody SysDatasource sysDatasource) throws SQLException {
		return R.ok(sysDatasourceService.saveSysDatasource(sysDatasource));
	}

	/**
	 * 修改数据源表
	 *
	 * @param sysDatasource 数据源表
	 * @return R
	 */
	@ApiOperation(value = "修改数据源表")
	@SysLog("修改数据源表")
	@PutMapping
	public R updateById(@RequestBody SysDatasource sysDatasource) throws SQLException {
		return R.ok(sysDatasourceService.updateSysDatasource(sysDatasource));
	}

	/**
	 * 通过id删除数据源表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除数据源表")
	@SysLog("删除数据源表")
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		return R.ok(sysDatasourceService.removeById(id));
	}

}
