package com.zzb.db.mapper;

import com.zzb.db.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String sId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(@Param("sId") String sId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}