package com.zzb.db.mapper;

import com.zzb.db.model.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScoreMapper {
    int deleteByPrimaryKey(@Param("sId") String sId, @Param("cId") String cId);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(@Param("sId") String sId, @Param("cId") String cId);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}