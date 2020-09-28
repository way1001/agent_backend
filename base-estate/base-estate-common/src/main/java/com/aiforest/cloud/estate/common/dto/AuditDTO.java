/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.aiforest.cloud.estate.common.entity.TopicReply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 房产点评
 *
 * @author way
 * @date 2020-04-09 09:05:34
 */
@Data
public class AuditDTO {

	private String id;

	private String userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createId;

    private String content;

    private Integer guestbooks;

    private Integer likes;

    private Integer views;

    private String isVisited;

    private String isAnonymous;

	private String auditStatus;

	private String affiliationId;

}
