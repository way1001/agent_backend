CREATE TABLE `base_wx`.`wx_template_msg` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `del_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `enable` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '（1：开启；0：关闭）',
  `tenant_id` varchar(32) NOT NULL COMMENT '所属租户',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号配置ID、小程序AppID',
  `pri_tmpl_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板ID',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模版标题',
  `content` mediumtext COMMENT '模版内容',
  `example` mediumtext COMMENT '模板内容示例',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模版类型，2 为一次性订阅，3 为长期订阅',
  `use_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用途，1：订单下单成功；2：订单支付成功',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_use_app` (`tenant_id`,`app_id`,`use_type`),
  KEY `ids_app_id` (`app_id`),
  KEY `ids_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='微信模板/订阅消息';

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250725530589585409','订阅消息删除','wxma:wxtemplatemsg:del','','1250724694023069698','','','0','0','1','2020-04-16 17:59:38','2020-04-16 17:59:38','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250725417255297025','订阅消息修改','wxma:wxtemplatemsg:edit','','1250724694023069698','','','0','0','1','2020-04-16 17:59:11','2020-04-16 17:59:11','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250725307804934146','订阅消息查询','wxma:wxtemplatemsg:get','','1250724694023069698','','','0','0','1','2020-04-16 17:58:45','2020-04-16 17:58:45','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250725031559684098','订阅消息新增','wxma:wxtemplatemsg:add','','1250724694023069698','','','0','0','1','2020-04-16 17:57:39','2020-04-16 17:57:39','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250724859953930242','订阅消息列表','wxma:wxtemplatemsg:index','','1250724694023069698','','','0','0','1','2020-04-16 17:56:58','2020-04-16 17:57:58','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1250724694023069698','订阅消息模板','','template','ae0619ea3e5b86a3e382081680785f7e','el-icon-s-comment','views/wxma/wxtemplatemsg/index','30','0','0','2020-04-16 17:56:19','2020-04-16 17:56:34','0');

insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250725530589585409','2020-04-16 17:59:38','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250725417255297025','2020-04-16 17:59:11','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250725307804934146','2020-04-16 17:58:45','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250725031559684098','2020-04-16 17:57:39','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250724859953930242','2020-04-16 17:56:58','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1250724694023069698','2020-04-16 17:56:19','1');

ALTER TABLE `base_upms`.`sys_menu` CHANGE `permission` `permission` VARCHAR(64) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '菜单权限标识'; 

ALTER TABLE `base_wx`.`wx_template_msg` CHANGE `use_type` `use_type` CHAR(2) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用途，2：订单支付成功；3、订单发货提醒'; 


