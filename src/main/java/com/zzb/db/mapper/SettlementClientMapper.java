package com.zzb.db.mapper;

import com.zzb.db.model.SettlementClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import redis.clients.jedis.Client;

import java.util.List;

@Mapper
public interface SettlementClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettlementClient record);

    int insertSelective(SettlementClient record);

    SettlementClient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettlementClient record);

    int updateByPrimaryKey(SettlementClient record);

    List<SettlementClient> searchClientByClientNameList(@Param("clientNameList") List<String> clientNameList);
}