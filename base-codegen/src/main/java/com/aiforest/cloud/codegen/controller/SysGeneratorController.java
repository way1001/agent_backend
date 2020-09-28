package com.aiforest.cloud.codegen.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.codegen.entity.GenTable;
import com.aiforest.cloud.codegen.service.SysGeneratorService;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 代码生成器
 *
 * @author JL
 * @date 2018-07-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
@Api(value = "generator", tags = "代码生成器")
public class SysGeneratorController {
	private final SysGeneratorService sysGeneratorService;
	private NacosConfigProperties nacosProperties;

	/**
	 * 列表
	 *
	 * @param tableName 参数集
	 * @param sysDatasourceId        数据源ID
	 * @return 数据库表
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	public R getPage(Page page, String tableName, String sysDatasourceId) {
		return R.ok(sysGeneratorService.getPage(page, tableName, sysDatasourceId));
	}

	/**
	 * 生成代码
	 */
	@ApiOperation(value = "生成代码")
	@SneakyThrows
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenTable genTable, HttpServletResponse response) {
		byte[] data = sysGeneratorService.generatorCode(genTable);

		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genTable.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

	/**
	 * 生成代码预览
	 */
	@ApiOperation(value = "生成代码预览")
	@SneakyThrows
	@PostMapping("/view")
	public R generatorView(@RequestBody GenTable genTable) {
		return R.ok(sysGeneratorService.generatorView(genTable));
	}


	/**
	 * 获取服务列表
	 */
	@ApiOperation(value = "获取服务列表")
	@SneakyThrows
	@GetMapping("/genkey/list")
	public R listGenKey() {
		List<Map<String,String>> rs = new ArrayList();
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getServerAddr());
		properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
		properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
		ConfigService configService = NacosFactory.createConfigService(properties);
		String content = configService.getConfig(CommonConstants.CONFIG_DATA_ID, CommonConstants.CONFIG_GROUP, CommonConstants.CONFIG_TIMEOUT_MS);
		Yaml yaml = new Yaml();
		Map<String, Object> mp = (Map<String, Object>) yaml.load(content);
		JSONObject jSONObject = JSONUtil.parseObj(mp);
		JSONArray routes = jSONObject.getJSONArray("routes");
		routes.forEach(route -> {
			JSONArray predicates = JSONUtil.parseObj(route).getJSONArray("predicates");
			predicates.forEach(predicate -> {
				JSONObject args = JSONUtil.parseObj(predicate).getJSONObject("args");
				String genkey = JSONUtil.parseObj(args).getStr("_genkey_0");
				genkey = genkey.replace("/**","").replace("/","");
				System.out.println(genkey);
				Map<String,String> mpGenkey = new HashMap<>();
				mpGenkey.put("label",genkey);
				mpGenkey.put("value",genkey);
				rs.add(mpGenkey);
			});
		});
		return R.ok(rs);
	}
}
