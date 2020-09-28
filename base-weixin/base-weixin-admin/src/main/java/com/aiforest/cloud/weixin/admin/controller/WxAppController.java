/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.config.HomeDirConfigProperties;
import com.aiforest.cloud.common.core.util.FileUtils;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.common.data.tenant.TenantContextHolder;
import com.aiforest.cloud.common.log.annotation.SysLog;
import com.aiforest.cloud.common.security.annotation.Inside;
import com.aiforest.cloud.weixin.common.constant.WxReturnCode;
import com.aiforest.cloud.weixin.admin.service.WxAppService;
import com.aiforest.cloud.weixin.admin.config.mp.WxMpConfiguration;
import com.aiforest.cloud.weixin.common.entity.WxApp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信账号配置
 *
 * @author JL
 * @date 2019-03-23 21:26:35
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxapp")
@Api(value = "wxapp", tags = "微信账号配置")
public class WxAppController {

	private final WxAppService wxAppService;
	private final HomeDirConfigProperties homeDirConfigProperties;

	/**
	 * 分页查询
	 *
	 * @param page  分页对象
	 * @param wxApp 微信账号配置
	 * @return
	 */
	@ApiOperation(value = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
	public R getWxAppPage(Page page, WxApp wxApp) {
		return R.ok(wxAppService.page(page, Wrappers.query(wxApp)));
	}

	/**
	 * list查询
	 * @param wxApp
	 * @return
	 */
	@ApiOperation(value = "list查询")
	@GetMapping("/list")
	public List<WxApp> list(WxApp wxApp) {
		return wxAppService.list(Wrappers.query(wxApp).lambda().orderByDesc(WxApp::getCreateTime));
	}

	/**
	 * 通过id查询微信账号配置
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询微信账号配置")
	@GetMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:get')")
	public R getById(@PathVariable("id") String id) {
		return R.ok(wxAppService.getById(id));
	}

	/**
	 * 新增微信账号配置
	 *
	 * @param wxApp 微信账号配置
	 * @return R
	 */
	@ApiOperation(value = "新增微信账号配置")
	@SysLog("新增微信账号配置")
	@PostMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:add')")
	public R save(@RequestBody WxApp wxApp) {
		wxAppService.save(wxApp);
		return R.ok();
	}

	/**
	 * 修改微信账号配置
	 *
	 * @param wxApp 微信账号配置
	 * @return R
	 */
	@ApiOperation(value = "修改微信账号配置")
	@SysLog("修改微信账号配置")
	@PutMapping
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:edit')")
	public R updateById(@RequestBody WxApp wxApp) {
		wxAppService.updateById(wxApp);
		return R.ok();
	}

	/**
	 * 通过id删除微信账号配置
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除微信账号配置")
	@SysLog("删除微信账号配置")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:del')")
	public R removeById(@PathVariable String id) {
		return R.ok(wxAppService.removeById(id));
	}

	@ApiOperation(value = "生成公众号二维码")
	@SysLog("生成公众号二维码")
	@GetMapping("/qrCode")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
	public R getQrCode(String id, String sceneStr) throws WxErrorException {
		WxMpQrcodeService wxMpQrcodeService = WxMpConfiguration.getMpService(id).getQrcodeService();
		WxMpQrCodeTicket wxMpQrCodeTicket = wxMpQrcodeService.qrCodeCreateLastTicket(sceneStr);
		return R.ok(wxMpQrCodeTicket.getUrl());
	}

	@ApiOperation(value = "生成并保存公众号二维码")
	@SysLog("生成并保存公众号二维码")
	@PostMapping("/qrCode")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
	public R createQrCode(@RequestBody Map<String,String> param) throws WxErrorException {
		String id = param.get("id");
		String sceneStr = param.get("sceneStr");
		WxMpQrcodeService wxMpQrcodeService = WxMpConfiguration.getMpService(id).getQrcodeService();
		WxMpQrCodeTicket wxMpQrCodeTicket = wxMpQrcodeService.qrCodeCreateLastTicket(sceneStr);
		WxApp wxApp = new WxApp();
		wxApp.setId(id);
		wxApp.setQrCode(wxMpQrCodeTicket.getUrl());
		return R.ok(wxAppService.updateById(wxApp));

	}

	@ApiOperation(value = "微信接口次数进行清零")
	@SysLog("微信接口次数进行清零")
	@PutMapping("/quota")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
	public R clearQuota(@RequestBody WxApp wxApp) {
		try {
			WxMpService wxMpService = WxMpConfiguration.getMpService(wxApp.getId());
			wxMpService.clearQuota(wxApp.getId());
			return new R<>();
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("微信接口次数进行清零失败appID:" + wxApp.getId() + ":" + e.getMessage());
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 获取access-token
	 * @param wxApp
	 * @return
	 */
	@ApiOperation(value = "获取access-token")
	@GetMapping("/access-token")
	@PreAuthorize("@ato.hasAuthority('wxmp:wxapp:index')")
	public R getAccessToken(WxApp wxApp) {
		try {
			WxMpService wxMpService = WxMpConfiguration.getMpService(wxApp.getId());
			return R.ok(wxMpService.getAccessToken());
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("获取access-token失败appID:" + wxApp.getId() + ":" + e.getMessage());
			return WxReturnCode.wxErrorExceptionHandler(e);
		}
	}

	/**
	 * 通过id查询微信账号配置
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询微信账号配置")
	@Inside
	@GetMapping("/inside/{id}")
	public R getByIdInside(@PathVariable("id") String id) {
		return R.ok(wxAppService.getById(id));
	}

	/**
	 * 上传支付证书
	 * @param mulFile
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "上传支付证书")
	@PostMapping("/cert/upload")
	public String uploadFile(@RequestParam("file") MultipartFile mulFile,
							 @RequestParam("appId") String appId) throws IOException {
		File file = FileUtils.multipartFileToFile(mulFile);
		Map<Object, Object> responseData = new HashMap<>();
		String home;
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){
			home = homeDirConfigProperties.getWindows();
		}else{
			home = homeDirConfigProperties.getLinux();
		}
		String dir = StrUtil.format("{}/{}/{}/{}/", home,"cert",TenantContextHolder.getTenantId(),appId);
		File file2 = FileUtil.writeBytes(FileUtil.readBytes(file), dir + file.getName());
		responseData.put("link", file2.getPath());
		return JSONUtil.toJsonStr(responseData);
	}
}
