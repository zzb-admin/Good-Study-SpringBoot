package com.zzb.db.mapper;

import com.zzb.db.model.SettlementFinanceAbbreviation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettlementFinanceAbbreviationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettlementFinanceAbbreviation record);

    int insertSelective(SettlementFinanceAbbreviation record);

    SettlementFinanceAbbreviation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettlementFinanceAbbreviation record);

    int updateByPrimaryKey(SettlementFinanceAbbreviation record);

    SettlementFinanceAbbreviation searchFianceAbbreviationByName(@Param("financeAbbreviationName") String financeAbbreviationName);
}