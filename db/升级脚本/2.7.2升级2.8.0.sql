CREATE TABLE `base_mall`.`groupon_info` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属租户',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `enable` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '（1：开启；0：关闭）',
  `spu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品Id',
  `sku_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'skuId',
  `name` varchar(100) DEFAULT NULL COMMENT '拼团名称',
  `groupon_num` int(11) DEFAULT '0' COMMENT '拼团人数',
  `groupon_price` decimal(10,2) NOT NULL COMMENT '拼团价',
  `valid_begin_time` datetime NOT NULL COMMENT '开始时间',
  `valid_end_time` datetime NOT NULL COMMENT '结束时间',
  `launch_num` int(11) DEFAULT '0' COMMENT '发起人数',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '图片',
  `groupon_rule` text COMMENT '拼团规则',
  `share_title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分享标题 ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ids_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='拼团';

CREATE TABLE `base_mall`.`groupon_user` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属租户',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `groupon_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼团ID',
  `group_id` varchar(32) NOT NULL COMMENT '组ID（团长的拼团记录ID）',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `headimg_url` varchar(1000) DEFAULT NULL COMMENT '头像',
  `spu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品Id',
  `sku_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'skuId',
  `is_leader` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否团长（0：否；1：是）',
  `valid_begin_time` datetime NOT NULL COMMENT '开始时间',
  `valid_end_time` datetime NOT NULL COMMENT '结束时间',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单Id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ids_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='拼团记录';

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767627204640770','拼团记录列表','mall_grouponuser_index','','1239767506106695682','','','0','0','1','2020-03-17 12:16:50','2020-03-17 12:16:50','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767506106695682','拼团记录','','grouponuser','1239766266052329473','el-icon-s-order','views/mall/grouponuser/index','20','0','0','2020-03-17 12:16:21','2020-03-17 12:16:21','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767264883884034','拼团删除','mall_grouponinfo_del','','1239766777090523137','','','0','0','1','2020-03-17 12:15:24','2020-03-17 12:15:24','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767195036139522','拼团修改','mall_grouponinfo_edit','','1239766777090523137','','','0','0','1','2020-03-17 12:15:07','2020-03-17 12:15:07','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767117328269313','拼团新增','mall_grouponinfo_add','','1239766777090523137','','','0','0','1','2020-03-17 12:14:49','2020-03-17 12:14:49','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767051196678145','拼团查询','mall_grouponinfo_get','','1239766777090523137','','','0','0','1','2020-03-17 12:14:33','2020-03-17 12:14:33','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239766958087323650','拼团列表','mall_grouponinfo_index','','1239766777090523137','','','0','0','1','2020-03-17 12:14:11','2020-03-17 12:14:11','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239766777090523137','拼团配置','','grouponinfo','1239766266052329473','el-icon-s-tools','views/mall/grouponinfo/index','1','0','0','2020-03-17 12:13:28','2020-03-17 12:13:28','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239766266052329473','拼团管理','','groupon','8000000','el-icon-s-management','Layout','20','0','0','2020-03-17 12:11:26','2020-03-17 12:11:26','0');

insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767627204640770','2020-03-17 12:16:50','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767506106695682','2020-03-17 12:16:21','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767264883884034','2020-03-17 12:15:24','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767195036139522','2020-03-17 12:15:07','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767117328269313','2020-03-17 12:14:49','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239767051196678145','2020-03-17 12:14:33','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239766958087323650','2020-03-17 12:14:11','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239766777090523137','2020-03-17 12:13:28','1');
insert into `base_upms`.`sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) values('1','1239766266052329473','2020-03-17 12:11:26','1');

ALTER TABLE `base_mall`.`order_info` CHANGE `order_type` `order_type` CHAR(2) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' NULL COMMENT '订单类型（0、普通订单；1、砍价订单；2、拼团订单）', CHANGE `relation_id` `relation_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '关联ID（砍价记录ID、拼团记录ID）'; 

ALTER TABLE `base_mall`.`order_info` ADD COLUMN `market_id` VARCHAR(32) NULL COMMENT '营销ID（砍价ID、拼团ID）' AFTER `order_type`, CHANGE `relation_id` `relation_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '营销记录ID（砍价记录ID、拼团记录ID）'; 

ALTER TABLE `base_mall`.`order_info` CHANGE `status` `status` CHAR(2) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '订单状态1、待发货 2、待收货 3、确认收货/已完成 5、已关闭 10、拼团中'; 

ALTER TABLE `base_mall`.`order_info` CHANGE `relation_id` `relation_id` VARCHAR(32) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '营销记录ID（砍价记录ID、拼团记录组ID（团长的拼团记录ID））'; 

ALTER TABLE `base_mall`.`groupon_info` ADD COLUMN `duration` INT NULL COMMENT '拼团时长（小时）' AFTER `groupon_price`; 

ALTER TABLE `base_mall`.`groupon_info` CHANGE `duration` `duration` INT(11) NOT NULL COMMENT '拼团时长（小时）'; 

ALTER TABLE `base_mall`.`groupon_user` CHANGE `is_leader` `is_leader` CHAR(2) CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '是否团长（0：否；1：是）'; 

ALTER TABLE `base_mall`.`groupon_user` CHANGE `valid_begin_time` `valid_begin_time` DATETIME NULL COMMENT '开始时间', CHANGE `valid_end_time` `valid_end_time` DATETIME NULL COMMENT '结束时间'; 

ALTER TABLE `base_mall`.`groupon_user` ADD COLUMN `status` CHAR(2) NULL COMMENT '状态（0：拼团中；1：完成拼团）' AFTER `order_id`; 

ALTER TABLE `base_mall`.`groupon_user` ADD UNIQUE INDEX `uk_group_user` (`group_id`, `user_id`); 

ALTER TABLE `base_mall`.`groupon_user` ADD COLUMN `groupon_num` INT NULL COMMENT '拼团人数（几人团）' AFTER `groupon_id`, ADD COLUMN `groupon_price` DECIMAL(10,2) NULL COMMENT '拼团价' AFTER `groupon_num`; 

ALTER TABLE `base_mall`.`groupon_info` CHANGE `groupon_num` `groupon_num` INT(11) DEFAULT 0 NULL COMMENT '拼团人数（几人团）'; 

ALTER TABLE `base_mall`.`coupon_user` DROP PRIMARY KEY, ADD PRIMARY KEY (`id`), ADD UNIQUE INDEX `uk_coupon_code` (`coupon_code`); 

ALTER TABLE `base_mall`.`order_info` DROP PRIMARY KEY, ADD PRIMARY KEY (`id`), ADD UNIQUE INDEX `uk_order_no` (`order_no`); 

ALTER TABLE `base_wx`.`wx_app` DROP PRIMARY KEY, ADD PRIMARY KEY (`id`), ADD UNIQUE INDEX `uk_weixin_sign` (`weixin_sign`); 


DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '0776fdb3afe857025aefeb6500f67fb6'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '088b9371d30c932f9ba555a457a165cb'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '097f4947ac78faf5074c357f5b1ee8f1'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '0d1cfcbd88e13d75ea9796d08b4964a0'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '10e7a8ca4da4f8cd9e36a375d7266f6a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1101000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1102000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1103000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1104000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1105000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1201000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1202000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1203000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1224306628599173121'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1224307007944609794'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1225254548244733953'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226514140274573313'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226514304217333762'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226514436212080641'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226514630622265346'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226514858779820034'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226525576396750849'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226525700103553025'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226525832920383490'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226525967616262146'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226526082179481602'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226759834914713601'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1226760046232137729'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1227203300044042241'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1227524435889614850'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1227568833306177537'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1228676665992794113'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1228676836369616897'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229323003373785090'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229750619910119425'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229750717675151362'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229750794644824065'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229750881777295362'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1229750964161814530'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1231205330852978690'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1233957499251326978'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239766958087323650'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239767051196678145'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239767117328269313'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239767195036139522'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239767264883884034'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1239767627204640770'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '129a20e268846e9f03e7c7317d34763a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1301000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1302000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1303000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1304000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1305000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1401000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1402000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1403000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1404000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '158da8253a07848d3087e27742b90ecf'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '17e1ead2dacd1d312105b396b3517032'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1803c5717dce433515683a91bb6d704b'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1923c874226184c056658a742f295534'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1a722cc14550813e4caeb5dee7bd5597'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '1ee60d42f423c8e0b1b0438ab69aaba4'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2101000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2185077a9a1a8c4ed3ae2d892e17db1a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2201000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2202000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2203000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '22dad641b94b70574166204f014e462d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2304aae6101ef1b56160e5cfe63b997a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2401000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2402000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2403000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '241b84a3f3ae7876271634c094dee5cc'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2501000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2502000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2503000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2601000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '266d5676deeafd74df0febe6a45cc209'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2a437bae8175be76cb075ea2888f412d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2b8fa95ebcb3111266ba38f833faf4db'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '2f5d0e7eda34754af13a3d3f519dce19'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '3412a37e6cdc2de99fd534b9327c6288'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '39855c97d751071177ab9e88680b78aa'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '3d73a5a1971f8485850e387e00da4381'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '3fe13d13918b4386750fbc470cb74689'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '43886e8bc03aa4dbbd56b88bc7616b3f'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '494aad894d5dd963bbb2b59642ab78a6'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '4b382a3090885d2783f1f8a1c8e72da9'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '4d4ee31e793ab205bd1b0c0796ab464a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '4e2f07380b3a3a3c83491b44becc87d1'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '56b7671766135eedb032de157694aecb'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5905015167129a3fd2ac4440132f06e2'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5a46087c7799d15b3f7dec6373459c18'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5b234a22036af9fcacaad011a3a5a207'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5c4d7e01c344912ab0f55f4c951995f7'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5d7eca401883fb565e5dfc7b855ed566'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '5f3b1faf8a0b5179bf32acd9759f2bfc'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '62165a91efcb202e65c55cde8a0ef62a'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '682cd9c287b1f8eddb5ffaa88711be49'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '6a6eed170bf8d0d02e2c551992e8dc2d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '6c717f6f1ddc913366e4ba902f84aecf'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '6ce1a6e397603915346dc39d2cf2cd8e'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '70429f02ff42a948929190d7363cb8ae'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '70a667199c0b5af89a9aec25395de035'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '71efc9c14c1c83dd30c826e82ea33eda'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '74008da0be270ec7c90d9b44d5336e24'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '7b507e991dc0d6364e9ede4a3e3c0f75'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '7b7dce33eb721f48c81c4c245345bf2d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '7dcb5d797e46db0bfbc8a1bd2b2079b8'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '7f9dde9bc5f370716d2295302b78a5c1'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8111000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8112000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8113000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8114000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8121000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8122000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8123000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8124000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8125000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8126000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8127000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8131000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8132000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8133000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8134000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8141000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8142000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8151000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8152000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8153000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8154000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8161000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8162000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8163000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8164000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8171000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8172000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8173000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8174000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8181000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8182000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8183000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8184000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8210000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8220000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8230000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8240000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '82dcc2e0e8ca1c0f8bd4c424a5dda5c5'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8310000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8320000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8330000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8340000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8398b906bfd903c55d3bab220d95d050'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8410000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8420000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8430000'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '8aae802c5e76c10fb00c342870aec822'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '92e0817e3f1f38b2f307a12dfb57f973'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '9758015e84ca9001f54651db3e215f22'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '997766d975fecca2bbc6d87a1ffa33bc'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '99de545b2709c749074253793b0b3579'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '9a41a7f503b9ee40f552b8c39d6860a4'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = '9de3c01d50199f569d58979adba94190'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a020c0eda490bc5acbbdab8aed5de286'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a18a3759214257e8ad00b4e8e250b097'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a1b97d911a253bbb7984d914423edab1'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a1d5a717939198826962f7621f86f53d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a24da84b7de0787d578d5f60c3803d2f'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'a263bd8cbe22bceab8ecd4f6e2b827b4'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'b7fb7e7511057c292f4e7a567803e49c'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'bec7e2f7cf4ad760bbe550fa05f7eec4'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'bf99fd6d49b5db3a107848984daff0ba'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'c0ed022164dbf21954ef79dfb3d70ed3'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'c35e06fc6b2b474a14aff1d7a69aaef9'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'd56d79f193c43e90ef3bef5b1d19bee8'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'd9c8041c62c50f859b049e86357d1c41'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'e6ae905517592d8b3f30a0a5a1bfb65b'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'eace6e697073de815152466b9c493c4d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'ec18bd25c6ed99faaa7cc8190fa23db1'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'ec1b689468ac0eab23713ce41273ae4b'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'ec21dec4d5791af4e30c34f7d077f06d'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'edcaca9d4cc487aefe2c0c043bb95c30'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'f277b11dd1a5bafe707e4694eb506899'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'f4eae5341815bec413ef64e3e6524385'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'fda6b16ece50ede8a7a5bb16213753d0'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'fe8074f23fa08934775bc7d06401fd3f'; 
DELETE FROM `base_upms`.`sys_menu` WHERE `id` = 'fef4dfeeb78c4f299e0e6e1a43be3585'; 

insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('0776fdb3afe857025aefeb6500f67fb6','商城订单列表','mall:orderinfo:index',NULL,'70dbfd7911a393a3c50d1eef09a8708f',NULL,NULL,'1','0','1','2019-10-18 21:41:30','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('088b9371d30c932f9ba555a457a165cb','素材删除','mall:material:del',NULL,'e2ab8932bc86ad98b917c22731205caa',NULL,NULL,'1','0','1','2019-10-26 19:36:09','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('097f4947ac78faf5074c357f5b1ee8f1','商品评价列表','mall:goodsappraises:index',NULL,'c247293edbfc0ee004f413cb6ee654cd',NULL,NULL,'1','0','1','2019-10-18 21:41:07','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('0d1cfcbd88e13d75ea9796d08b4964a0','退款单查询','mall:orderrefunds:get',NULL,'6e46351de075ab9febf8ee6ec17bdced',NULL,NULL,'1','0','1','2019-12-13 19:23:25','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('10e7a8ca4da4f8cd9e36a375d7266f6a','商品评价修改','mall:goodsappraises:edit',NULL,'c247293edbfc0ee004f413cb6ee654cd',NULL,NULL,'1','0','1','2019-09-25 20:30:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1101000','用户新增','sys:user:add',NULL,'1100000',NULL,NULL,'1','0','1','2017-11-08 09:52:09','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1102000','用户修改','sys:user:edit',NULL,'1100000',NULL,NULL,'1','0','1','2017-11-08 09:52:48','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1103000','用户删除','sys:user:del',NULL,'1100000',NULL,NULL,'1','0','1','2017-11-08 09:54:01','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1104000','用户详情','sys:user:get',NULL,'1100000',NULL,NULL,'1','0','1','2019-07-27 11:34:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1105000','用户密码修改','sys:user:password',NULL,'1100000',NULL,NULL,'1','0','1','2019-07-27 18:07:08','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1201000','菜单新增','sys:menu:add',NULL,'1200000',NULL,NULL,'1','0','1','2017-11-08 10:15:53','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1202000','菜单修改','sys:menu:edit',NULL,'1200000',NULL,NULL,'1','0','1','2017-11-08 10:16:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1203000','菜单删除','sys:menu:del',NULL,'1200000',NULL,NULL,'1','0','1','2017-11-08 10:16:43','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1224306628599173121','文件存储配置查询','sys:configstorage:get',NULL,'1224306298712969218',NULL,NULL,'0','0','1','2020-02-03 20:20:21','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1224307007944609794','文件存储配置修改','sys:configstorage:edit',NULL,'1224306298712969218',NULL,NULL,'0','0','1','2020-02-03 20:21:51','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1225254548244733953','菜单查询','sys:menu:get','','1200000','','','0','0','1','2020-02-06 11:07:03','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226514140274573313','发货地查询','mall:deliveryplace:get','','1226513939979780098','','','0','0','1','2020-02-09 22:32:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226514304217333762','发货地列表','mall:deliveryplace:index','','1226513939979780098','','','0','0','1','2020-02-09 22:32:52','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226514436212080641','发货地新增','mall:deliveryplace:add','','1226513939979780098','','','0','0','1','2020-02-09 22:33:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226514630622265346','发货地修改','mall:deliveryplace:edit','','1226513939979780098','','','0','0','1','2020-02-09 22:34:10','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226514858779820034','发货地删除','mall:deliveryplace:del','','1226513939979780098','','','0','0','1','2020-02-09 22:35:04','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226525576396750849','保障服务列表','mall:ensure:index','','1226525445274419201','','','0','0','1','2020-02-09 23:17:39','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226525700103553025','保障服务查询','mall:ensure:get','','1226525445274419201','','','0','0','1','2020-02-09 23:18:09','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226525832920383490','保障服务新增','mall:ensure:add','','1226525445274419201','','','0','0','1','2020-02-09 23:18:40','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226525967616262146','保障服务修改','mall:ensure:edit','','1226525445274419201','','','0','0','1','2020-02-09 23:19:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226526082179481602','保障服务删除','mall:ensure:del','','1226525445274419201','','','0','0','1','2020-02-09 23:19:40','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226759834914713601','文本编辑器查询','sys:configeditor:get','','1226759608866893825','','','0','0','1','2020-02-10 14:48:31','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1226760046232137729','文本编辑器修改','sys:configeditor:edit','','1226759608866893825','','','0','0','1','2020-02-10 14:49:21','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1227203300044042241','发送邮件','sys:email:add','','1227202599968567298','','','0','0','1','2020-02-11 20:10:41','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1227524435889614850','发短信','sys:sms:add','','1227524298689736706','','','0','0','1','2020-02-12 17:26:46','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1227568833306177537','规格查询','mall:goodsspec:get','','8400000','','','0','0','1','2020-02-12 20:23:11','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1228676665992794113','小程序用户列表','wxmp:wxuser:index','','1228676296659161089','','','0','0','1','2020-02-15 21:45:19','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1228676836369616897','小程序用户详情','wxmp:wxuser:get','','1228676296659161089','','','0','0','1','2020-02-15 21:46:00','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229323003373785090','数据统计列表','wxmp:wxsummary:index','','1229322873778180098','','','0','0','1','2020-02-17 16:33:38','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229750619910119425','通知详情','mall:noticeitem:get','','1229750327311278082','','','0','0','1','2020-02-18 20:52:50','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229750717675151362','通知修改','mall:noticeitem:edit','','1229750327311278082','','','0','0','1','2020-02-18 20:53:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229750794644824065','通知新增','mall:noticeitem:add','','1229750327311278082','','','0','0','1','2020-02-18 20:53:31','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229750881777295362','通知列表','mall:noticeitem:index','','1229750327311278082','','','0','0','1','2020-02-18 20:53:52','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1229750964161814530','通知删除','mall:noticeitem:del','','1229750327311278082','','','0','0','1','2020-02-18 20:54:12','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1231205330852978690','菜单分组列表','wxmp:wxmenurule:index','','8140000','','','0','0','1','2020-02-22 21:13:20','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1233957499251326978','查询服务监控','sys:server:index','','1233957113861967874','','','0','0','1','2020-03-01 11:29:28','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239766958087323650','拼团列表','mall:grouponinfo:index','','1239766777090523137','','','0','0','1','2020-03-17 12:14:11','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767051196678145','拼团查询','mall:grouponinfo:get','','1239766777090523137','','','0','0','1','2020-03-17 12:14:33','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767117328269313','拼团新增','mall:grouponinfo:add','','1239766777090523137','','','0','0','1','2020-03-17 12:14:49','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767195036139522','拼团修改','mall:grouponinfo:edit','','1239766777090523137','','','0','0','1','2020-03-17 12:15:07','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767264883884034','拼团删除','mall:grouponinfo:del','','1239766777090523137','','','0','0','1','2020-03-17 12:15:24','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1239767627204640770','拼团记录列表','mall:grouponuser:index','','1239767506106695682','','','0','0','1','2020-03-17 12:16:50','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('129a20e268846e9f03e7c7317d34763a','明细修改','mall:pointsrecord:edit',NULL,'04c920605926e47982b7ede7fa5c3ec6',NULL,NULL,'1','0','1','2019-12-11 11:30:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1301000','角色新增','sys:role:add',NULL,'1300000',NULL,NULL,'1','0','1','2017-11-08 10:14:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1302000','角色修改','sys:role:edit',NULL,'1300000',NULL,NULL,'1','0','1','2017-11-08 10:14:41','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1303000','角色删除','sys:role:del',NULL,'1300000',NULL,NULL,'1','0','1','2017-11-08 10:14:59','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1304000','角色详情','sys:role:get',NULL,'1300000',NULL,NULL,'1','0','1','2019-07-27 11:43:06','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1305000','分配权限','sys:role:perm',NULL,'1300000',NULL,NULL,'1','0','1','2018-04-20 07:22:55','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1401000','机构新增','sys:organ:add',NULL,'1400000',NULL,NULL,'1','0','1','2018-01-20 14:56:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1402000','机构修改','sys:organ:edit',NULL,'1400000',NULL,NULL,'1','0','1','2018-01-20 14:56:59','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1403000','机构删除','sys:organ:del',NULL,'1400000',NULL,NULL,'1','0','1','2018-01-20 14:57:28','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1404000','机构详情','sys:organ:get',NULL,'1400000',NULL,NULL,'1','0','1','2019-07-27 11:31:26','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('158da8253a07848d3087e27742b90ecf','积分设置查询','mall:pointsconfig:get',NULL,'f9ef6fbe98f64ab0f0076d9d51a34f2e',NULL,NULL,'1','0','1','2019-12-11 22:21:02','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('17e1ead2dacd1d312105b396b3517032','砍价删除','mall:bargaininfo:del',NULL,'33f86fb25ecde4e2bc5ec0bdf9229e28',NULL,NULL,'1','0','1','2019-12-28 19:04:39','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1803c5717dce433515683a91bb6d704b','领券记录查询','mall:couponuser:get',NULL,'6dcb14bf76e04a4f5345a1ec6bd7eacc',NULL,NULL,'1','0','1','2019-12-18 18:30:09','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1923c874226184c056658a742f295534','菜单列表','sys:menu:index',NULL,'1200000',NULL,NULL,'1','0','1','2019-10-18 21:20:04','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1a722cc14550813e4caeb5dee7bd5597','电子券删除','mall:couponinfo:del',NULL,'ba1273686386588c78b20b93fcb1779f',NULL,NULL,'1','0','1','2019-12-14 12:01:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('1ee60d42f423c8e0b1b0438ab69aaba4','查询用户','mall:userinfo:get',NULL,'db95df20df51a46afa7935f38efb7360',NULL,NULL,'1','0','1','2019-12-10 20:58:21','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2101000','日志删除','sys:log:del',NULL,'2100000',NULL,NULL,'1','0','1','2017-11-20 20:37:37','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2185077a9a1a8c4ed3ae2d892e17db1a','租户修改','sys:tenant:edit',NULL,'e9c150621e6bcfd7f0cea479d2ab9236',NULL,NULL,'1','0','1','2019-10-12 20:34:20','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2201000','字典删除','sys:dict:del',NULL,'2200000',NULL,NULL,'1','0','1','2017-11-29 11:30:11','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2202000','字典新增','sys:dict:add',NULL,'2200000',NULL,NULL,'1','0','1','2018-05-11 22:34:55','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2203000','字典修改','sys:dict:edit',NULL,'2200000',NULL,NULL,'1','0','1','2018-05-11 22:36:03','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('22dad641b94b70574166204f014e462d','字典详情','sys:dict:get',NULL,'2200000',NULL,NULL,'1','0','1','2019-10-18 21:16:48','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2304aae6101ef1b56160e5cfe63b997a','租户列表','sys:tenant:index',NULL,'e9c150621e6bcfd7f0cea479d2ab9236',NULL,NULL,'1','0','1','2019-10-18 21:26:34','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2401000','客户端新增','sys:client:add',NULL,'2400000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2402000','客户端修改','sys:client:edit',NULL,'2400000',NULL,NULL,'1','0','1','2018-05-15 21:37:06','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2403000','客户端删除','sys:client:del',NULL,'2400000',NULL,NULL,'1','0','1','2018-05-15 21:39:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('241b84a3f3ae7876271634c094dee5cc','客户端详情','sys:client:get',NULL,'2400000',NULL,NULL,'1','0','1','2019-10-18 21:22:14','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2501000','第三方登录新增','sys:third:party:add',NULL,'2500000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2502000','第三方登录修改','sys:third:party:edit',NULL,'2500000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2503000','第三方登录删除','sys:third:party:del',NULL,'2500000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2601000','令牌删除','sys:token:del',NULL,'2600000',NULL,NULL,'1','0','1','2018-09-04 05:59:50','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('266d5676deeafd74df0febe6a45cc209','租户新增','sys:tenant:add',NULL,'e9c150621e6bcfd7f0cea479d2ab9236',NULL,NULL,'1','0','1','2019-10-12 20:35:00','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2a437bae8175be76cb075ea2888f412d','商品类目列表','mall:goodscategory:index',NULL,'8200000',NULL,NULL,'1','0','1','2019-10-18 21:40:11','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2b8fa95ebcb3111266ba38f833faf4db','租户删除','sys:tenant:del',NULL,'e9c150621e6bcfd7f0cea479d2ab9236',NULL,NULL,'1','0','1','2019-10-12 20:33:59','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('2f5d0e7eda34754af13a3d3f519dce19','小程序配置详情','wxmp:wxapp:get',NULL,'a96328161a39450238e8cafa243b7c35',NULL,NULL,'1','0','1','2019-09-25 17:09:49','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('3412a37e6cdc2de99fd534b9327c6288','素材修改','mall:material:edit',NULL,'e2ab8932bc86ad98b917c22731205caa',NULL,NULL,'1','0','1','2019-10-26 19:35:49','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('39855c97d751071177ab9e88680b78aa','小程序配置修改','wxmp:wxapp:edit',NULL,'a96328161a39450238e8cafa243b7c35',NULL,NULL,'1','0','1','2019-09-25 17:12:12','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('3d73a5a1971f8485850e387e00da4381','明细查询','mall:pointsrecord:get',NULL,'04c920605926e47982b7ede7fa5c3ec6',NULL,NULL,'1','0','1','2019-12-11 11:29:44','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('3fe13d13918b4386750fbc470cb74689','素材列表','mall:material:index',NULL,'e2ab8932bc86ad98b917c22731205caa',NULL,NULL,'1','0','1','2019-10-26 19:35:02','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('43886e8bc03aa4dbbd56b88bc7616b3f','小程序配置新增','wxmp:wxapp:add',NULL,'a96328161a39450238e8cafa243b7c35',NULL,NULL,'1','0','1','2019-09-25 17:10:24','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('494aad894d5dd963bbb2b59642ab78a6','第三方登录详情','sys:third:party:get',NULL,'2500000',NULL,NULL,'1','0','1','2019-10-18 21:28:08','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('4b382a3090885d2783f1f8a1c8e72da9','电子券查询','mall:couponinfo:get',NULL,'ba1273686386588c78b20b93fcb1779f',NULL,NULL,'1','0','1','2019-12-14 12:00:05','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('4d4ee31e793ab205bd1b0c0796ab464a','明细列表','mall:pointsrecord:index',NULL,'04c920605926e47982b7ede7fa5c3ec6',NULL,NULL,'1','0','1','2019-12-11 11:29:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('4e2f07380b3a3a3c83491b44becc87d1','砍价记录列表','mall:bargainuser:index',NULL,'cb3e50350c8d982ee23474146281f7ed',NULL,NULL,'0','0','1','2020-01-03 19:50:15','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('56b7671766135eedb032de157694aecb','通知列表','mall:noticeitem:index',NULL,'8bd8eabf33a6b8b56c04392063bade07',NULL,NULL,'1','0','1','2019-11-10 19:09:08','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5905015167129a3fd2ac4440132f06e2','通知新增','mall:noticeitem:add',NULL,'8bd8eabf33a6b8b56c04392063bade07',NULL,NULL,'1','0','1','2019-11-10 19:09:56','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5a46087c7799d15b3f7dec6373459c18','砍价列表','mall:bargaininfo:index',NULL,'33f86fb25ecde4e2bc5ec0bdf9229e28',NULL,NULL,'1','0','1','2019-12-28 19:03:15','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5b234a22036af9fcacaad011a3a5a207','电子券新增','mall:couponinfo:add',NULL,'ba1273686386588c78b20b93fcb1779f',NULL,NULL,'1','0','1','2019-12-14 12:00:29','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5c4d7e01c344912ab0f55f4c951995f7','用户修改','mall:userinfo:edit',NULL,'db95df20df51a46afa7935f38efb7360',NULL,NULL,'1','0','1','2019-12-10 20:59:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5d7eca401883fb565e5dfc7b855ed566','客户端列表','sys:client:index',NULL,'2400000',NULL,NULL,'1','0','1','2019-10-18 21:21:26','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('5f3b1faf8a0b5179bf32acd9759f2bfc','角色列表','sys:role:index',NULL,'1300000',NULL,NULL,'1','0','1','2019-10-18 21:25:28','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('62165a91efcb202e65c55cde8a0ef62a','用户消息列表','wxmp:wxmsg:index',NULL,'8170000',NULL,NULL,'1','0','1','2019-10-18 21:36:15','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('682cd9c287b1f8eddb5ffaa88711be49','通知修改','mall:noticeitem:edit',NULL,'8bd8eabf33a6b8b56c04392063bade07',NULL,NULL,'1','0','1','2019-11-10 19:10:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('6a6eed170bf8d0d02e2c551992e8dc2d','第三方登录列表','sys:third:party:index',NULL,'2500000',NULL,NULL,'1','0','1','2019-10-18 21:27:42','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('6c717f6f1ddc913366e4ba902f84aecf','商城订单详情','mall:orderinfo:get',NULL,'70dbfd7911a393a3c50d1eef09a8708f',NULL,NULL,'1','0','1','2019-09-10 15:13:44','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('6ce1a6e397603915346dc39d2cf2cd8e','用户列表','mall:userinfo:index',NULL,'db95df20df51a46afa7935f38efb7360',NULL,NULL,'1','0','1','2019-12-10 20:57:48','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('70429f02ff42a948929190d7363cb8ae','积分设置修改','mall:pointsconfig:edit',NULL,'f9ef6fbe98f64ab0f0076d9d51a34f2e',NULL,NULL,'1','0','1','2019-12-06 16:37:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('70a667199c0b5af89a9aec25395de035','电子券列表','mall:couponinfo:index',NULL,'ba1273686386588c78b20b93fcb1779f',NULL,NULL,'1','0','1','2019-12-14 11:59:33','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('71efc9c14c1c83dd30c826e82ea33eda','明细新增','mall:pointsrecord:add',NULL,'04c920605926e47982b7ede7fa5c3ec6',NULL,NULL,'1','0','1','2019-12-11 11:30:03','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('74008da0be270ec7c90d9b44d5336e24','小程序配置列表','wxmp:wxapp:index',NULL,'a96328161a39450238e8cafa243b7c35',NULL,NULL,'1','0','1','2019-10-18 21:32:44','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('7b507e991dc0d6364e9ede4a3e3c0f75','规格列表','mall:goodsspec:index',NULL,'8400000',NULL,NULL,'1','0','1','2019-10-18 21:40:42','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('7b7dce33eb721f48c81c4c245345bf2d','商品列表','mall:goodsspu:index',NULL,'8300000',NULL,NULL,'1','0','1','2019-10-18 21:39:43','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('7dcb5d797e46db0bfbc8a1bd2b2079b8','消息自动回复列表','wxmp:wxautoreply:index',NULL,'8160000',NULL,NULL,'1','0','1','2019-10-18 21:33:43','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('7f9dde9bc5f370716d2295302b78a5c1','商品评价详情','mall:goodsappraises:get',NULL,'c247293edbfc0ee004f413cb6ee654cd',NULL,NULL,'1','0','1','2019-09-25 20:30:28','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8111000','公众号配置新增','wxmp:wxapp:add',NULL,'8110000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8112000','公众号配置修改','wxmp:wxapp:edit',NULL,'8110000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8113000','公众号配置删除','wxmp:wxapp:del',NULL,'8110000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8114000','公众号配置详情','wxmp:wxapp:get',NULL,'8110000','1',NULL,'3','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8121000','公众号用户新增','wxmp:wxuser:add',NULL,'8120000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8122000','公众号用户修改','wxmp:wxuser:edit',NULL,'8120000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8123000','公众号用户删除','wxmp:wxuser:del',NULL,'8120000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8124000','公众号用户详情','wxmp:wxuser:get',NULL,'8120000','1',NULL,'3','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8125000','公众号用户同步','wxmp:wxuser:synchro',NULL,'8120000',NULL,NULL,'1','0','1','2019-04-14 17:35:01','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8126000','公众号用户备注修改','wxmp:wxuser:edit:remark',NULL,'8120000',NULL,NULL,'1','0','1','2019-04-17 14:35:14','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8127000','公众号用户打标签','wxmp:wxuser:tagging',NULL,'8120000',NULL,NULL,'1','0','1','2019-04-17 15:26:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8131000','素材新增','wxmp:wxmaterial:add',NULL,'8130000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8132000','素材修改','wxmp:wxmaterial:edit',NULL,'8130000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8133000','素材删除','wxmp:wxmaterial:del',NULL,'8130000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8134000','素材详情','wxmp:wxmaterial:get',NULL,'8130000','1',NULL,'3','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8141000','自定义菜单发布','wxmp:wxmenu:add',NULL,'8140000','1',NULL,'4','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8142000','自定义菜单详情','wxmp:wxmenu:get',NULL,'8140000','1',NULL,'5','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8151000','用户标签列表','wxmp:wxusertags:list',NULL,'8150000',NULL,NULL,'1','0','1','2019-04-16 17:30:24','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8152000','用户标签新增','wxmp:wxusertags:add',NULL,'8150000',NULL,NULL,'1','0','1','2019-04-17 09:03:56','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8153000','用户标签修改','wxmp:wxusertags:edit',NULL,'8150000',NULL,NULL,'1','0','1','2019-04-17 09:04:33','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8154000','用户标签删除','wxmp:wxusertags:del',NULL,'8150000',NULL,NULL,'1','0','1','2019-04-17 09:05:08','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8161000','消息自动回复新增','wxmp:wxautoreply:add',NULL,'8160000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8162000','消息自动回复修改','wxmp:wxautoreply:edit',NULL,'8160000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8163000','消息自动回复删除','wxmp:wxautoreply:del',NULL,'8160000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8164000','消息自动回复详情','wxmp:wxautoreply:get',NULL,'8160000','1',NULL,'3','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8171000','用户消息新增','wxmp:wxmsg:add',NULL,'8170000',NULL,NULL,'1','0','1','2019-05-28 16:05:48','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8172000','用户消息修改','wxmp:wxmsg:edit',NULL,'8170000',NULL,NULL,'1','0','1','2019-05-28 16:06:15','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8173000','用户消息删除','wxmp:wxmsg:del',NULL,'8170000',NULL,NULL,'1','0','1','2019-05-28 16:11:06','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8174000','用户消息详情','wxmp:wxmsg:get',NULL,'8170000',NULL,NULL,'1','0','1','2019-05-28 16:11:35','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8181000','微信群发消息新增','wxmp:wxmassmsg:add',NULL,'8180000','1',NULL,'0','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8182000','微信群发消息修改','wxmp:wxmassmsg:edit',NULL,'8180000','1',NULL,'1','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8183000','微信群发消息删除','wxmp:wxmassmsg:del',NULL,'8180000','1',NULL,'2','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8184000','微信群发消息详情','wxmp:wxmassmsg:get',NULL,'8180000','1',NULL,'3','0','1','2018-05-15 21:35:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8210000','商品类目新增','mall:goodscategory:add',NULL,'8200000',NULL,NULL,'1','0','1','2019-08-12 14:21:10','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8220000','商品类目修改','mall:goodscategory:edit',NULL,'8200000',NULL,NULL,'1','0','1','2019-08-12 14:21:46','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8230000','商品类目删除','mall:goodscategory:del',NULL,'8200000',NULL,NULL,'1','0','1','2019-08-12 14:22:12','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8240000','商品类目详情','mall:goodscategory:get',NULL,'8200000',NULL,NULL,'1','0','1','2019-08-12 14:22:43','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('82dcc2e0e8ca1c0f8bd4c424a5dda5c5','退款单列表','mall:orderrefunds:index',NULL,'6e46351de075ab9febf8ee6ec17bdced',NULL,NULL,'1','0','1','2019-12-13 19:23:01','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8310000','商品新增','mall:goodsspu:add',NULL,'8300000',NULL,NULL,'1','0','1','2019-08-12 16:34:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8320000','商品修改','mall:goodsspu:edit',NULL,'8300000',NULL,NULL,'1','0','1','2019-08-12 16:34:53','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8330000','商品删除','mall:goodsspu:del',NULL,'8300000',NULL,NULL,'1','0','1','2019-08-12 16:35:22','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8340000','商品详情','mall:goodsspu:get',NULL,'8300000',NULL,NULL,'1','0','1','2019-08-12 16:35:42','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8398b906bfd903c55d3bab220d95d050','运费模板查询','mall:freighttemplat:get',NULL,'e6a73489db9ef754101570b0e6d0d2e5',NULL,NULL,'1','0','1','2019-12-24 16:27:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8410000','规格新增','mall:goodsspec:add',NULL,'8400000',NULL,NULL,'1','0','1','2019-08-13 16:21:01','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8420000','规格删除','mall:goodsspec:del',NULL,'8400000',NULL,NULL,'1','0','1','2019-08-13 16:21:28','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8430000','规格修改','mall:goodsspec:edit',NULL,'8400000',NULL,NULL,'1','0','1','2019-08-13 16:28:26','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('8aae802c5e76c10fb00c342870aec822','砍价新增','mall:bargaininfo:add',NULL,'33f86fb25ecde4e2bc5ec0bdf9229e28',NULL,NULL,'1','0','1','2019-12-28 19:03:56','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('92e0817e3f1f38b2f307a12dfb57f973','退款单修改','mall:orderrefunds:edit',NULL,'6e46351de075ab9febf8ee6ec17bdced',NULL,NULL,'1','0','1','2019-12-13 19:24:01','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('9758015e84ca9001f54651db3e215f22','微信群发消息列表','wxmp:wxmassmsg:index',NULL,'8180000',NULL,NULL,'1','0','1','2019-10-18 21:34:25','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('997766d975fecca2bbc6d87a1ffa33bc','领券记录列表','mall:couponuser:index',NULL,'6dcb14bf76e04a4f5345a1ec6bd7eacc',NULL,NULL,'1','0','1','2019-12-18 18:29:32','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('99de545b2709c749074253793b0b3579','砍价修改','mall:bargaininfo:edit',NULL,'33f86fb25ecde4e2bc5ec0bdf9229e28',NULL,NULL,'1','0','1','2019-12-28 19:04:18','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('9a41a7f503b9ee40f552b8c39d6860a4','通知详情','mall:noticeitem:get',NULL,'8bd8eabf33a6b8b56c04392063bade07',NULL,NULL,'1','0','1','2019-11-10 19:09:30','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('9de3c01d50199f569d58979adba94190','租户详情','sys:tenant:get',NULL,'e9c150621e6bcfd7f0cea479d2ab9236',NULL,NULL,'1','0','1','2019-10-12 20:34:40','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a020c0eda490bc5acbbdab8aed5de286','商城订单修改','mall:orderinfo:edit',NULL,'70dbfd7911a393a3c50d1eef09a8708f',NULL,NULL,'1','0','1','2019-09-10 15:12:47','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a18a3759214257e8ad00b4e8e250b097','运费模板删除','mall:freighttemplat:del',NULL,'e6a73489db9ef754101570b0e6d0d2e5',NULL,NULL,'1','0','1','2019-12-24 16:28:12','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a1b97d911a253bbb7984d914423edab1','运费模板修改','mall:freighttemplat:edit',NULL,'e6a73489db9ef754101570b0e6d0d2e5',NULL,NULL,'1','0','1','2019-12-24 16:27:43','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a1d5a717939198826962f7621f86f53d','商品评价新增','mall:goodsappraises:add',NULL,'c247293edbfc0ee004f413cb6ee654cd',NULL,NULL,'1','0','1','2019-09-25 20:30:58','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a24da84b7de0787d578d5f60c3803d2f','公众号用户列表','wxmp:wxuser:index',NULL,'8120000',NULL,NULL,'1','0','1','2019-10-18 21:37:10','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('a263bd8cbe22bceab8ecd4f6e2b827b4','明细删除','mall:pointsrecord:del',NULL,'04c920605926e47982b7ede7fa5c3ec6',NULL,NULL,'1','0','1','2019-12-11 11:30:40','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('b7fb7e7511057c292f4e7a567803e49c','用户列表','sys:user:index',NULL,'1100000',NULL,NULL,'1','0','1','2019-10-18 21:29:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('bec7e2f7cf4ad760bbe550fa05f7eec4','砍价查询','mall:bargaininfo:get',NULL,'33f86fb25ecde4e2bc5ec0bdf9229e28',NULL,NULL,'1','0','1','2019-12-28 19:03:36','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('bf99fd6d49b5db3a107848984daff0ba','商城订单新增','mall:orderinfo:add',NULL,'70dbfd7911a393a3c50d1eef09a8708f',NULL,NULL,'1','0','1','2019-09-10 15:12:13','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('c0ed022164dbf21954ef79dfb3d70ed3','素材新增','mall:material:add',NULL,'e2ab8932bc86ad98b917c22731205caa',NULL,NULL,'1','0','1','2019-10-26 19:35:23','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('c35e06fc6b2b474a14aff1d7a69aaef9','商城订单删除','mall:orderinfo:del',NULL,'70dbfd7911a393a3c50d1eef09a8708f',NULL,NULL,'1','0','1','2019-09-10 15:13:12','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('d56d79f193c43e90ef3bef5b1d19bee8','令牌列表','sys:token:index',NULL,'2600000',NULL,NULL,'1','0','1','2019-10-18 21:30:11','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('d9c8041c62c50f859b049e86357d1c41','日志列表','sys:log:index',NULL,'2100000',NULL,NULL,'1','0','1','2019-10-18 21:18:38','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('e6ae905517592d8b3f30a0a5a1bfb65b','商品评价删除','mall:goodsappraises:del',NULL,'c247293edbfc0ee004f413cb6ee654cd',NULL,NULL,'1','0','1','2019-09-25 20:29:32','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('eace6e697073de815152466b9c493c4d','素材详情','mall:material:get',NULL,'e2ab8932bc86ad98b917c22731205caa',NULL,NULL,'1','0','1','2019-10-26 19:36:25','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('ec18bd25c6ed99faaa7cc8190fa23db1','素材列表','wxmp:wxmaterial:index',NULL,'8130000',NULL,NULL,'1','0','1','2019-10-18 21:35:34','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('ec1b689468ac0eab23713ce41273ae4b','机构列表','sys:organ:index',NULL,'1400000',NULL,NULL,'1','0','1','2019-10-18 21:24:07','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('ec21dec4d5791af4e30c34f7d077f06d','运费模板新增','mall:freighttemplat:add',NULL,'e6a73489db9ef754101570b0e6d0d2e5',NULL,NULL,'1','0','1','2019-12-24 16:23:17','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('edcaca9d4cc487aefe2c0c043bb95c30','字典列表','sys:dict:index',NULL,'2200000',NULL,NULL,'1','0','1','2019-10-18 21:17:36','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('f277b11dd1a5bafe707e4694eb506899','通知删除','mall:noticeitem:del',NULL,'8bd8eabf33a6b8b56c04392063bade07',NULL,NULL,'1','0','1','2019-11-10 19:10:41','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('f4eae5341815bec413ef64e3e6524385','小程序配置删除','wxmp:wxapp:del',NULL,'a96328161a39450238e8cafa243b7c35',NULL,NULL,'1','0','1','2019-09-25 17:11:16','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('fda6b16ece50ede8a7a5bb16213753d0','电子券修改','mall:couponinfo:edit',NULL,'ba1273686386588c78b20b93fcb1779f',NULL,NULL,'1','0','1','2019-12-14 12:00:49','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('fe8074f23fa08934775bc7d06401fd3f','运费模板列表','mall:freighttemplat:index',NULL,'e6a73489db9ef754101570b0e6d0d2e5',NULL,NULL,'1','0','1','2019-12-24 16:26:39','2020-03-24 20:19:17','0');
insert into `base_upms`.`sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) values('fef4dfeeb78c4f299e0e6e1a43be3585','公众号列表','wxmp:wxapp:index',NULL,'8110000',NULL,NULL,'1','0','1','2019-10-18 21:31:53','2020-03-24 20:19:17','0');
