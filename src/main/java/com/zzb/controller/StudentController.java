package com.zzb.controller;

import com.zzb.db.model.StudentModel;
import com.zzb.db.request.StudentUpdate;
import com.zzb.service.IStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @GetMapping("/getStudentById/{sid}")
    public StudentModel getStudentById(@PathVariable("sid") String sid) throws Exception {
        return studentService.selectByPrimaryKey(sid);
    }

    @PutMapping("/updateStudentById/{sid}")
    public int updateStudentById(@RequestBody StudentUpdate studentUpdate) {
        return studentService.updateBySid(studentUpdate);
    }



}
