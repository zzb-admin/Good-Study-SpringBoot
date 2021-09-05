package com.zzb.db.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 客户财务属性表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementClient implements Serializable {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 关联其他系统client表id
    */
    private Integer clientId;

    /**
    * 签约主体客户名(冗余)
    */
    private String clientName;

    /**
    * 财务简称id,settlement_finance_abbreviation.id
    */
    private Integer financeAbbreviationId;

    /**
    * 财务简称名(冗余),settlement_finance_abbreviation.name
    */
    private String financeAbbreviationName;

    /**
    * 返点简称id,settlement_rebate_abbreviation.id
    */
    private Integer rebateAbbreviationId;

    /**
    * 返点简称名(冗余),settlement_rebate_abbreviation.name
    */
    private String rebateAbbreviationName;

    /**
    * 客户状态,0=禁用,1=活跃
    */
    private Byte dataStatus;

    /**
    * 数据来源,1=历史数据,2= crm创建
    */
    private Byte dataSource;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 是否删除,0=整成,1=删除
    */
    private Byte isDeleted;

    /**
    * 客户规模
    */
    private Byte clientSize;

    /**
    * 客户类型
    */
    private Byte clientKind;

    private static final long serialVersionUID = 1L;
}