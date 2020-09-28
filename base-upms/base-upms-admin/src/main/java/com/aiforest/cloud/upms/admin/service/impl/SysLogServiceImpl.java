package com.aiforest.cloud.upms.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.upms.admin.mapper.SysLogMapper;
import com.aiforest.cloud.upms.admin.service.SysLogService;
import com.aiforest.cloud.upms.common.entity.SysLog;
import com.aiforest.cloud.upms.common.vo.PreLogVO;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVOList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchLogs(List<PreLogVO> preLogVOList) {
		List<SysLog> sysLogs = preLogVOList.stream()
			.map(pre -> {
				SysLog log = new SysLog();
				log.setType(CommonConstants.LOG_TYPE_9);
				log.setTitle(pre.getInfo());
				log.setException(pre.getStack());
				log.setParams(pre.getMessage());
				log.setCreateTime(LocalDateTime.now());
				log.setRequestUri(pre.getUrl());
				log.setCreateBy(pre.getUser());
				return log;
			})
			.collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}
}
