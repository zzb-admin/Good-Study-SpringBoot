package com.zzb.service;

import com.zzb.db.request.ClientUpdate;

import java.util.List;

/**
 * @program: Good-Study-SpringBoot
 * @description: 签约主体修改
 * @author: monkey.zhao
 * @create: 2021-07-26 10:43
 **/
public interface ClientService {
    List<String> fixClientUpdate(ClientUpdate clientUpdate);
}
