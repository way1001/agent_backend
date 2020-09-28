CREATE TABLE `base_upms`.`sys_log_login` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PK',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求URI',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作提交的数据',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  `tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1243833202171219970','登录日志列表','sys:loglogin:index','','1243832970918268930','','','0','0','1','2020-03-28 17:31:59','2020-03-28 17:31:59','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1243832970918268930','登录日志','','loglogin','2000000','el-icon-s-order','views/admin/loglogin/index','4','0','0','2020-03-28 17:31:04','2020-03-28 17:31:04','0');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1243833202171219970','2020-03-28 17:31:59','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1243832970918268930','2020-03-28 17:31:04','1');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1243869063717883905','登录日志查询','sys:loglogin:get','','1243832970918268930','','','0','0','1','2020-03-28 19:54:29','2020-03-28 19:54:29','0');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1243869063717883905','2020-03-28 19:54:29','1');

ALTER TABLE `base_upms`.`sys_log_login` CHANGE `exception` `address` VARCHAR(255) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '地址信息'; 


ALTER TABLE `base_mall`.`order_info` ADD COLUMN `delivery_way` CHAR(2) NOT NULL COMMENT '配送方式1、普通快递；2、上门自提' AFTER `payment_way`; 

ALTER TABLE `base_mall`.`delivery_place` ADD COLUMN `detail` VARCHAR(500) NOT NULL COMMENT '详细地址' AFTER `place`, ADD COLUMN `tel_num` VARCHAR(50) NOT NULL COMMENT '电话号码' AFTER `detail`; 

ALTER TABLE `base_mall`.`points_config` ADD UNIQUE INDEX `uk_tenant_id` (`tenant_id`); 

ALTER TABLE `base_upms`.`sys_config_editor` ADD UNIQUE INDEX `uk_tenant_id` (`tenant_id`); 

ALTER TABLE `base_upms`.`sys_config_storage` ADD UNIQUE INDEX `uk_tenant_id` (`tenant_id`); 

update `base_mall`.`goods_spu` set `delivery_place_id` = '' where `delivery_place_id` is null;

ALTER TABLE `base_mall`.`goods_spu` CHANGE `freight_templat_id` `freight_templat_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '运费模板ID', CHANGE `delivery_place_id` `delivery_place_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货地ID'; 

ALTER TABLE `base_mall`.`delivery_place` CHANGE `detail` `address` VARCHAR(255) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址'; 

ALTER TABLE `base_mall`.`order_info` DROP INDEX `ids_tenant_id`, ADD KEY `ids_tenant_id` (`tenant_id`, `order_no`); 

update `base_mall`.`order_info` set `delivery_way` = '1' where `delivery_way` = '';


CREATE TABLE `base_upms`.`gen_table` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `table_prefix` varchar(50) DEFAULT NULL COMMENT '表前缀',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_table_name` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成配置表';


UPDATE `base_upms`.`sys_user` SET `phone` = NULL WHERE `phone` = '';

ALTER TABLE `base_upms`.`sys_user` ADD UNIQUE INDEX `uk_phone` (`phone`); 

ALTER TABLE `base_upms`.`sys_user` DROP COLUMN `gitee_login`, DROP COLUMN `osc_id`; 

ALTER TABLE `base_mall`.`order_info` ADD COLUMN `name` VARCHAR(255) NULL COMMENT '订单名' AFTER `is_pay`; 


DROP TABLE `base_upms`.`sys_third_party`; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2502000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '6a6eed170bf8d0d02e2c551992e8dc2d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2503000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2501000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2500000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '494aad894d5dd963bbb2b59642ab78a6'; 
DELETE FROM `base_upms`.`sys_role_menu` WHERE `menu_id` IN('2502000','6a6eed170bf8d0d02e2c551992e8dc2d','2503000','2501000','2500000','494aad894d5dd963bbb2b59642ab78a6');