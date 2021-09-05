package com.zzb.service;

import com.zzb.db.model.StudentModel;
import com.zzb.db.request.StudentUpdate;

public interface IStudentService {

    StudentModel selectByPrimaryKey(String sid) throws Exception;

    int updateBySid(StudentUpdate studentUpdate);
}
