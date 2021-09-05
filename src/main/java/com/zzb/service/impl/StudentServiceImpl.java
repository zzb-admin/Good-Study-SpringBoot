package com.zzb.service.impl;

import com.zzb.db.mapper.StudentMapper;
import com.zzb.db.model.Student;
import com.zzb.db.model.StudentModel;
import com.zzb.db.request.StudentUpdate;
import com.zzb.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author monkey
 */
@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description: 实现简单的redis缓存
     * @Param: sid
     * @return: Student
     * @author: monkey.zhao
     **/
    @Override
    @Cacheable(value = "my-redis-cache1", key = "'book'+#sid")
    public StudentModel selectByPrimaryKey(String sid) throws Exception {
        log.info("没有缓存数据!!!!!!!!!!!!!");
        Student student = studentMapper.selectByPrimaryKey(sid);
        if (null == student) {
            throw new Exception("该数据不存在");
        }
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(student, studentModel);
        return studentModel;
    }


    /**
     * @Description: 先更新数据库，再删除缓存
     * @Param: sid
     * @return: int
     * @author: monkey.zhao
     **/
    @Override
    public int updateBySid(StudentUpdate studentUpdate) {
        if (null == studentUpdate) {
            return 0;
        }
        String sid = studentUpdate.getSid();
        String name = studentUpdate.getName();
        if (Strings.isBlank(sid) && Strings.isBlank(name)) {
            return 0;
        }
        Student student = studentMapper.selectByPrimaryKey(sid);
        if (null == student) {
            return 0;
        }
        Student studentModel = Student.builder().sId(sid).sName(name).build();
        //1、先update操作
        int i = studentMapper.updateByPrimaryKeySelective(studentModel);
        //2、删除缓存  下次读取还会更新至缓存
        if (1 == i) {
            deleteCacheMethod("my-redis-cache1" + "::" + "book" + sid);
        }
        return 1;
    }


    /**
     * @Description: 删除缓存实现方法
     * @Param: key
     * @return: Boolean
     * @author: monkey.zhao
     **/
    public Boolean deleteCacheMethod(String... key) {
        if (null != key && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(key);
            }
        }
        return true;
    }
}
