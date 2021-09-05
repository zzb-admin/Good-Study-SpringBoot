package com.zzb.db.request;

import lombok.Data;

import java.util.List;

/**
 * @program: Good-Study-SpringBoot
 * @description: 签约主体修改
 * @author: monkey.zhao
 * @create: 2021-07-26 10:39
 **/
@Data
public class ClientUpdate {

    private List<String> clientNameList;

    private String financeAbbreviationName;
}
