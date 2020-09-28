/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.weixin.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiforest.cloud.common.core.constant.CommonConstants;
import com.aiforest.cloud.weixin.admin.service.WxMenuRuleService;
import com.aiforest.cloud.weixin.common.constant.ConfigConstant;
import com.aiforest.cloud.weixin.common.entity.*;
import com.aiforest.cloud.weixin.admin.mapper.WxAppMapper;
import com.aiforest.cloud.weixin.admin.mapper.WxMenuMapper;
import com.aiforest.cloud.weixin.admin.config.mp.WxMpConfiguration;
import com.aiforest.cloud.weixin.admin.service.WxMenuService;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义菜单
 *
 * @author JL
 * @date 2019-03-27 16:52:10
 */
@Service
@AllArgsConstructor
public class WxMenuServiceImpl extends ServiceImpl<WxMenuMapper, WxMenu> implements WxMenuService {
	private final WxAppMapper wxAppMapper;
	private final WxMenuRuleService wxMenuRuleService;
	/***
	 * 获取WxApp下的菜单树结构
	 * @param appId
	 * @return
	 */
	@Override
	public String getWxMenuButton(String appId, String menuRuleId) {
		Menu menu = new Menu();
		//查询菜单组
		WxMenuRule wxMenuRule = wxMenuRuleService.getById(menuRuleId);
		if(wxMenuRule == null){
			return null;
		}
		MenuRule menuRule = new MenuRule();
		BeanUtil.copyProperties(wxMenuRule,menuRule);
		menuRule.setTag_id(wxMenuRule.getTagId());
		menu.setMatchrule(menuRule);
		//查出一级菜单
		List<WxMenu> listWxMenu = baseMapper.selectList(Wrappers
				.<WxMenu>query().lambda()
				.eq(WxMenu::getAppId, appId)
				.eq(WxMenu::getParentId,CommonConstants.PARENT_ID)
				.eq(WxMenu::getMenuRuleId, menuRuleId)
				.orderByAsc(WxMenu::getSort));
		List<MenuButton> listMenuButton = new ArrayList<>();
		MenuButton menuButton;
		List<MenuButton> subButtons;
		MenuButton subButton;
		if(listWxMenu!=null&&listWxMenu.size()>0){
			for(WxMenu wxMenu : listWxMenu){
				menuButton = new MenuButton();
				menuButton.setName(wxMenu.getName());
				String type = wxMenu.getType();
				if(StringUtils.isNotBlank(type)){//无二级菜单
					menuButton.setType(type);
					setButtonValue(menuButton,wxMenu);
				}else{//有二级菜单
					//查出二级菜单
					List<WxMenu> listWxMenu1 = baseMapper.selectList(Wrappers
							.<WxMenu>query().lambda()
							.eq(WxMenu::getAppId, appId)
							.eq(WxMenu::getParentId,wxMenu.getId())
							.eq(WxMenu::getMenuRuleId, menuRuleId)
							.orderByAsc(WxMenu::getSort));
					subButtons = new ArrayList<>();
					for(WxMenu wxMenu1 : listWxMenu1){
						subButton = new MenuButton();
						String type1 = wxMenu1.getType();
						subButton.setName(wxMenu1.getName());
						subButton.setType(type1);
						setButtonValue(subButton,wxMenu1);
						subButtons.add(subButton);
					}
					menuButton.setSub_button(subButtons);
				}
				listMenuButton.add(menuButton);
			}
		}
		menu.setButton(listMenuButton);
		return menu.toString();
	}

	void setButtonValue(MenuButton menuButton,WxMenu wxMenu){
		menuButton.setKey(wxMenu.getId());
		menuButton.setUrl(wxMenu.getUrl());
		menuButton.setContent(wxMenu.getContent());
		menuButton.setRepContent(wxMenu.getRepContent());
		menuButton.setMedia_id(wxMenu.getRepMediaId());
		menuButton.setRepType(wxMenu.getRepType());
		menuButton.setRepName(wxMenu.getRepName());
		menuButton.setAppid(wxMenu.getMaAppId());
		menuButton.setPagepath(wxMenu.getMaPagePath());
		menuButton.setUrl(wxMenu.getUrl());
		menuButton.setRepUrl(wxMenu.getRepUrl());
		menuButton.setRepHqUrl(wxMenu.getRepHqUrl());
		menuButton.setRepDesc(wxMenu.getRepDesc());
		menuButton.setRepThumbMediaId(wxMenu.getRepThumbMediaId());
		menuButton.setRepThumbUrl(wxMenu.getRepThumbUrl());
	}

	/**
	 * 保存并发布菜单
	 * @param
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public WxMenuRule saveAndRelease(String appId , String strWxMenu) throws WxErrorException {
		Menu menu = Menu.fromJson(strWxMenu);
		MenuRule menuRule = menu.getMatchrule();
		if(menuRule != null){
			WxMenuRule wxMenuRule = new WxMenuRule();
			BeanUtil.copyProperties(menuRule,wxMenuRule);
			wxMenuRule.setTagId(menuRule.getTag_id());
					//保存或修改菜单分组
			wxMenuRule.setAppId(appId);
			wxMenuRuleService.saveOrUpdate(wxMenuRule);
			List<MenuButton> buttons = menu.getButton();
			if(StrUtil.isNotBlank(menuRule.getId())){
				//先删除菜单
				baseMapper.delete(Wrappers
						.<WxMenu>query().lambda()
						.eq(WxMenu::getAppId, appId)
						.eq(WxMenu::getMenuRuleId,wxMenuRule.getId()));
				if(ConfigConstant.WX_MENU_TYPE_2.equals(wxMenuRule.getMenuType())){
					//删除微信已有个性化菜单
					wxMenuRule = wxMenuRuleService.getById(wxMenuRule.getId());
					WxMpConfiguration.getMpService(wxMenuRule.getAppId()).getMenuService().menuDelete(wxMenuRule.getMenuId());
				}
			}
			WxApp wxApp = wxAppMapper.selectById(appId);
			WxMenu wxMenu = null;
			WxMenu wxMenu1 = null;
			int sort1 = 1;
			int sort2 = 1;
			//入库菜单
			for(MenuButton menuButton : buttons){
				wxMenu = new WxMenu();
				setWxMenuValue(wxMenu,menuButton,wxApp);
				wxMenu.setSort(sort1);
				wxMenu.setParentId(CommonConstants.PARENT_ID);
				wxMenu.setMenuRuleId(wxMenuRule.getId());
				baseMapper.insert(wxMenu);
				menuButton.setKey(wxMenu.getId());
				sort1++;
				for(MenuButton menuButton1 : menuButton.getSub_button()){
					wxMenu1 = new WxMenu();
					setWxMenuValue(wxMenu1,menuButton1,wxApp);
					wxMenu1.setSort(sort2);
					wxMenu1.setParentId(wxMenu.getId());
					wxMenu1.setMenuRuleId(wxMenuRule.getId());
					baseMapper.insert(wxMenu1);
					menuButton1.setKey(wxMenu1.getId());
					sort2++;
				}
			}
			//创建菜单
			if(ConfigConstant.WX_MENU_TYPE_1.equals(wxMenuRule.getMenuType())){
				menu.setMatchrule(null);
			}
			String menuId = WxMpConfiguration.getMpService(appId).getMenuService().menuCreate(menu.toString());
			//个性化菜单保存菜单id
			if(ConfigConstant.WX_MENU_TYPE_2.equals(wxMenuRule.getMenuType())){
				wxMenuRule.setMenuId(menuId);
				wxMenuRuleService.updateById(wxMenuRule);
			}
			return wxMenuRule;
		}
		return null;
	}

	void setWxMenuValue(WxMenu wxMenu,MenuButton menuButton,WxApp wxApp){
		wxMenu.setId(menuButton.getKey());
		wxMenu.setAppId(wxApp.getId());
		wxMenu.setType(menuButton.getType());
		wxMenu.setName(menuButton.getName());
		wxMenu.setUrl(menuButton.getUrl());
		wxMenu.setRepMediaId(menuButton.getMedia_id());
		wxMenu.setRepType(menuButton.getRepType());
		wxMenu.setRepName(menuButton.getRepName());
		wxMenu.setMaAppId(menuButton.getAppid());
		wxMenu.setMaPagePath(menuButton.getPagepath());
		wxMenu.setRepContent(menuButton.getRepContent());
		wxMenu.setContent(menuButton.getContent());
		wxMenu.setRepUrl(menuButton.getRepUrl());
		wxMenu.setRepHqUrl(menuButton.getRepHqUrl());
		wxMenu.setRepDesc(menuButton.getRepDesc());
		wxMenu.setRepThumbMediaId(menuButton.getRepThumbMediaId());
		wxMenu.setRepThumbUrl(menuButton.getRepThumbUrl());
		menuButton.setRepUrl(null);
		menuButton.setRepDesc(null);
		menuButton.setRepHqUrl(null);
		menuButton.setContent(null);
		menuButton.setRepContent(null);
		menuButton.setRepType(null);
		menuButton.setRepName(null);
		menuButton.setRepThumbMediaId(null);
		menuButton.setRepThumbUrl(null);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeByRuleId(Serializable ruleId) throws WxErrorException {
		WxMenuRule wxMenuRule = wxMenuRuleService.getById(ruleId);
		if(ConfigConstant.WX_MENU_TYPE_1.equals(wxMenuRule.getMenuType())){//删除普通菜单，要删除所有个性化菜单
			//数据库删除
			wxMenuRuleService.remove(Wrappers
					.<WxMenuRule>query().lambda()
					.eq(WxMenuRule::getAppId, wxMenuRule.getAppId()));
			baseMapper.delete(Wrappers
					.<WxMenu>query().lambda()
					.eq(WxMenu::getAppId, wxMenuRule.getAppId()));
			//微信删除
			WxMpConfiguration.getMpService(wxMenuRule.getAppId()).getMenuService().menuDelete();
		}else{
			//数据库删除
			wxMenuRuleService.removeById(wxMenuRule.getId());
			baseMapper.delete(Wrappers
					.<WxMenu>query().lambda()
					.eq(WxMenu::getAppId, wxMenuRule.getAppId())
					.eq(WxMenu::getMenuRuleId,wxMenuRule.getId()));
			//微信删除
			WxMpConfiguration.getMpService(wxMenuRule.getAppId()).getMenuService().menuDelete(wxMenuRule.getMenuId());
		}
	}
}
