CREATE TABLE `base_wx`.`wx_menu_rule` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'PK',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `tenant_id` varchar(32) NOT NULL COMMENT '所属租户',
  `app_id` varchar(32) DEFAULT NULL COMMENT 'appId',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `menu_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单类型(1:普通菜单，2:个性化菜单)',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'menuid',
  `tag_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户标签的id',
  `sex` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别：男（1）女（2）',
  `client_platform_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)',
  `country` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家信息',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省份信息',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市信息',
  `language` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '语言信息',
  PRIMARY KEY (`id`),
  KEY `ids_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='微信自定义菜单分组';

ALTER TABLE `base_wx`.`wx_menu` ADD COLUMN `menu_rule_id` VARCHAR(32) NULL COMMENT '菜单组ID' AFTER `content`; 

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1231205330852978690','菜单分组列表','wxmp_wxmenurule_index','','8140000','','','0','0','1','2020-02-22 21:13:20','2020-02-25 20:42:42','0');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`) values('1','1231205330852978690','2020-02-22 21:13:20');

ALTER TABLE `base_upms`.`sys_organ_relation` ADD COLUMN `tenant_id` VARCHAR(32) NOT NULL COMMENT '所属租户' AFTER `descendant`; 

UPDATE
  `base_upms`.`sys_organ_relation` sys_organ_relation
SET
  sys_organ_relation.`tenant_id` =
  (SELECT
    sys_organ.`tenant_id`
  FROM
    `base_upms`.`sys_organ` sys_organ
  WHERE sys_organ.`id` = sys_organ_relation.`ancestor`);

ALTER TABLE `base_upms`.`sys_role_menu` ADD COLUMN `tenant_id` VARCHAR(32) NOT NULL COMMENT '所属租户' AFTER `create_time`;

UPDATE
  `base_upms`.`sys_role_menu` sys_role_menu
SET
  sys_role_menu.`tenant_id` =
  (SELECT
    sys_role.`tenant_id`
  FROM
    `base_upms`.`sys_role` sys_role
  WHERE sys_role.`id` = sys_role_menu.`role_id`);

ALTER TABLE `base_upms`.`sys_user_role` ADD COLUMN `tenant_id` VARCHAR(32) NOT NULL COMMENT '所属租户' AFTER `role_id`;

UPDATE
  `base_upms`.`sys_user_role` sys_user_role
SET
  sys_user_role.`tenant_id` =
  (SELECT
    sys_user.`tenant_id`
  FROM
    `base_upms`.`sys_user` sys_user
  WHERE sys_user.`id` = sys_user_role.`user_id`);

ALTER TABLE `base_upms`.`sys_organ` CHANGE `tenant_id` `tenant_id` VARCHAR(32) CHARSET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '租户ID'; 
ALTER TABLE `base_upms`.`sys_datasource` CHANGE `tenant_id` `tenant_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID'; 
ALTER TABLE `base_upms`.`sys_log` CHANGE `tenant_id` `tenant_id` VARCHAR(32) CHARSET utf8 COLLATE utf8_general_ci DEFAULT '0' NOT NULL COMMENT '所属租户'; 
ALTER TABLE `base_upms`.`sys_role` CHANGE `id` `id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK', CHANGE `role_name` `role_name` VARCHAR(64) CHARSET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名', CHANGE `role_code` `role_code` VARCHAR(64) CHARSET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色编码', CHANGE `role_desc` `role_desc` VARCHAR(255) CHARSET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '角色描述', CHANGE `create_time` `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '新增时间', CHANGE `update_time` `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间', CHANGE `tenant_id` `tenant_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '租户id'; 


insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1233957499251326978','查询服务监控','sys_server_index','','1233957113861967874','','','0','0','1','2020-03-01 11:29:28','2020-03-01 11:29:28','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1233957113861967874','服务监控','','server','1227202152985784322','el-icon-s-marketing','views/admin/server/index','30','0','0','2020-03-01 11:27:56','2020-03-01 11:27:56','0');

insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1233957499251326978','2020-03-01 11:29:28','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1233957113861967874','2020-03-01 11:27:56','1');

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1234086429899862018','数据监控','','druid','1227202152985784322','el-icon-s-operation','views/admin/druid/index','40','0','0','2020-03-01 20:01:47','2020-03-01 20:06:24','0');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1234086429899862018','2020-03-01 20:01:47','1');

DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2700000';
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2700000','文档管理',NULL,'doc','1227202152985784322','el-icon-s-claim','views/admin/doc/index','8','0','0','2019-07-31 11:41:39','2020-03-01 20:26:33','0');
