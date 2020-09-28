package com.aiforest.cloud.upms.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.upms.admin.service.SysOrganRelationService;
import com.aiforest.cloud.upms.common.entity.SysOrgan;
import com.aiforest.cloud.upms.common.entity.SysOrganRelation;
import com.aiforest.cloud.upms.admin.mapper.SysOrganRelationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 */
@Service
@AllArgsConstructor
public class SysOrganRelationServiceImpl extends ServiceImpl<SysOrganRelationMapper, SysOrganRelation> implements SysOrganRelationService {
	private final SysOrganRelationMapper sysOrganRelationMapper;

	/**
	 * 维护机构关系
	 *
	 * @param sysOrgan 机构
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertOrganRelation(SysOrgan sysOrgan) {
		//增加机构关系表
		SysOrganRelation condition = new SysOrganRelation();
		condition.setDescendant(sysOrgan.getParentId());
		List<SysOrganRelation> relationList = sysOrganRelationMapper
			.selectList(Wrappers.<SysOrganRelation>query().lambda()
				.eq(SysOrganRelation::getDescendant, sysOrgan.getParentId()))
			.stream().map(relation -> {
				relation.setTenantId(null);
				relation.setDescendant(sysOrgan.getId());
				return relation;
			}).collect(Collectors.toList());
		if (CollUtil.isNotEmpty(relationList)) {
			this.saveBatch(relationList);
		}

		//自己也要维护到关系表中
		SysOrganRelation own = new SysOrganRelation();
		own.setDescendant(sysOrgan.getId());
		own.setAncestor(sysOrgan.getId());
		sysOrganRelationMapper.insert(own);
	}

	/**
	 * 通过ID删除机构关系
	 *
	 * @param id
	 */
	@Override
	public void deleteAllOrganRealtion(String id) {
		baseMapper.deleteOrganRelationsById(id);
	}

	/**
	 * 更新机构关系
	 *
	 * @param relation
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateOrganRealtion(SysOrganRelation relation) {
		List<SysOrganRelation> relationList = baseMapper.listOrganRelations(relation)
				.stream().map(relation2 -> {
			relation2.setTenantId(null);
			return relation2;
		}).collect(Collectors.toList());
		if (CollUtil.isNotEmpty(relationList)) {
			this.saveBatch(relationList);
		}
	}

}
