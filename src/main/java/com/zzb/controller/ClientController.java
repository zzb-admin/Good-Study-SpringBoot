package com.zzb.controller;

import com.zzb.db.request.ClientUpdate;
import com.zzb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: Good-Study-SpringBoot
 * @description: 签约主体绑定财务简称
 * @author: monkey.zhao
 * @create: 2021-07-26 10:42
 **/
@RestController("/fixClient")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/fixClient")
    public List<String> fixClient(@RequestBody ClientUpdate clientUpdate){
        List<String> result = clientService.fixClientUpdate(clientUpdate);
        return result;
    }

}
