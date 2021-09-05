package com.zzb.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: Good-Study-SpringBoot
 * @description: 返回缓存的实体对象
 * @author: monkey.zhao
 * @create: 2021-07-09 14:23
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sId;

    private String sName;

    private String sBirth;

    private String sSex;

}
