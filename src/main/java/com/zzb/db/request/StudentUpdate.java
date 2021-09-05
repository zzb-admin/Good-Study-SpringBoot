package com.zzb.db.request;

import lombok.Data;

/**
 * @program: Good-Study-SpringBoot
 * @description: 修改学生表请求参数
 * @author: monkey.zhao
 * @create: 2021-07-12 10:59
 **/
@Data
public class StudentUpdate {
    private String sid;

    private String name;
}
