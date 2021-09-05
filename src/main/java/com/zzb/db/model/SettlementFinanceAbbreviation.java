package com.zzb.db.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 财务简称表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementFinanceAbbreviation implements Serializable {
    /**
    * 主键id
    */
    private Integer id;

    /**
    * 编码
    */
    private String code;

    /**
    * 财务简称名
    */
    private String name;

    /**
    * 状态,0=禁用,1=有效
    */
    private Byte dataStatus;

    /**
    * 创建者id
    */
    private Integer creatorId;

    /**
    * 创建者名字
    */
    private String creatorName;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 是否删除,0=正常,1=删除
    */
    private Byte isDeleted;

    private static final long serialVersionUID = 1L;
}