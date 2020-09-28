/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
        import java.math.BigDecimal;
    import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 房产商详情
 *
 * @author way
 * @date 2020-04-06 08:39:21
 */
@Data
@TableName("details_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "房产商详情")
public class DetailsInfo extends Model<DetailsInfo> {
    private static final long serialVersionUID=1L;

    /**
     * PK
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "PK")
    private String id;
    /**
     * 所属租户
     */
    @ApiModelProperty(value = "所属租户")
    private String tenantId;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @ApiModelProperty(value = "逻辑删除标记（0：显示；1：隐藏）")
    private String delFlag;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private String createId;
    /**
     * 售楼地址
     */
    @ApiModelProperty(value = "售楼地址")
    private String salesAddress;
    /**
     * 建筑类型 这个可以手输 比方 住宅：高层
     */
    @ApiModelProperty(value = "建筑类型 这个可以手输 比方 住宅：高层")
    private String architecturalTypes;
    /**
     * 装修标准
     */
    @ApiModelProperty(value = "装修标准")
    private String decorationStandard;
    /**
     * 开发商
     */
    @ApiModelProperty(value = "开发商")
    private String developers;
    /**
     * 容积率
     */
    @ApiModelProperty(value = "容积率")
    private BigDecimal plotRatio;
    /**
     * 绿化率
     */
    @ApiModelProperty(value = "绿化率")
    private BigDecimal greeningRate;
    /**
     * 规划用户
     */
    @ApiModelProperty(value = "规划用户")
    private String planningUser;
    /**
     * 占地面积
     */
    @ApiModelProperty(value = "占地面积")
    private String coverArea;
    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积")
    private String floorArea;
    /**
     * 车位数
     */
    @ApiModelProperty(value = "车位数")
    private String parkingNumber;
    /**
     * 车位配比
     */
    @ApiModelProperty(value = "车位配比")
    private String parkingRatio;
    /**
     * 工程进度
     */
    @ApiModelProperty(value = "工程进度")
    private String projectSchedule;
//    /**
//     * 周边
//     */
//    @ApiModelProperty(value = "周边")
//    private String periphery;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
	/**
	 * 关联楼盘ID
	 */
	@ApiModelProperty(value = "关联楼盘ID")
	private String affiliationId;

	@TableField(exist = false)
	private List<DetailsInfoSurrounding> detailsInfoSurroundingList;

}
