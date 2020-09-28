package com.aiforest.cloud.upms.admin.controller;

import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.upms.common.entity.SysServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 * 
 * @author JL
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysServer")
@Api(value = "sysServer", tags = "服务器监控")
public class ServerController{

	@ApiOperation(value = "获取服务器监控信息")
	@GetMapping
	@PreAuthorize("@ato.hasAuthority('sys:server:index')")
    public R getInfo() throws Exception
    {
        SysServer server = new SysServer();
        server.copyTo();
        return R.ok(server);
    }
}
