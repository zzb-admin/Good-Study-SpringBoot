package com.zzb.db.mapper;

import com.zzb.db.model.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(String cId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String cId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}