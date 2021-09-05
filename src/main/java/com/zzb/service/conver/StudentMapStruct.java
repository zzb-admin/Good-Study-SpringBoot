package com.zzb.service.conver;

import com.zzb.db.model.Student;
import com.zzb.db.request.StudentUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.Serializable;

/**
 * @program: Good-Study-SpringBoot
 * @description: 映射文件
 * @author: monkey.zhao
 * @create: 2021-07-12 11:09
 **/
@Mapper(componentModel = "spring")
public interface StudentMapStruct extends BaseMapStruct<StudentUpdate, Student>, Serializable {

    @Override
    @Mappings({
            @Mapping(source = "sid", target = "sId"),
            @Mapping(source = "name", target = "sName")
    })
    Student sourceToTarget(StudentUpdate var1);
}
