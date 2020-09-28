ALTER TABLE `base_mall`.`goods_sku` ADD COLUMN `version` INT DEFAULT 0 NULL COMMENT '版本号' AFTER `del_flag`; 


ALTER TABLE `base_mall`.`goods_sku_spec_value` ADD COLUMN `sort` INT NULL COMMENT '排序字段' AFTER `update_time`; 

ALTER TABLE `base_mall`.`goods_spu_spec` ADD COLUMN `sort` INT NULL COMMENT '排序字段' AFTER `update_time`; 