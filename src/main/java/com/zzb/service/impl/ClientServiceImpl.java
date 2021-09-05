package com.zzb.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zzb.db.mapper.SettlementClientMapper;
import com.zzb.db.mapper.SettlementFinanceAbbreviationMapper;
import com.zzb.db.model.SettlementClient;
import com.zzb.db.model.SettlementFinanceAbbreviation;
import com.zzb.db.request.ClientUpdate;
import com.zzb.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: Good-Study-SpringBoot
 * @description: 签约主体修改
 * @author: monkey.zhao
 * @create: 2021-07-26 10:45
 **/
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private SettlementClientMapper clientMapper;

    @Autowired
    private SettlementFinanceAbbreviationMapper settlementFinanceAbbreviationMapper;

    @Override
    public List<String> fixClientUpdate(ClientUpdate clientUpdate) {
        List<String> clientNameList = clientUpdate.getClientNameList();
        List<SettlementClient> clientList = clientMapper.searchClientByClientNameList(clientNameList);
        // 获取要修改得签约主体id
        List<Integer> idList = clientList.stream().map(SettlementClient::getId).collect(Collectors.toList());
        Set<Integer> idSet = Sets.newHashSet(idList);
        if(idList.size()!=idSet.size()){
            return Lists.newArrayList("数量错误");
        }
        // 获取财务简称
        SettlementFinanceAbbreviation settlementFinanceAbbreviation = settlementFinanceAbbreviationMapper.searchFianceAbbreviationByName(clientUpdate.getFinanceAbbreviationName());
        Integer financeId = settlementFinanceAbbreviation.getId();
        String finaceName = settlementFinanceAbbreviation.getName();
        List<String> result = Lists.newArrayList();
        String index1 = "update settlement_client set finance_abbreviation_id=";
        String index2 = ",finance_abbreviation_name='";
        String index3 = "' where id=";
        String index4 = ";";
        for (Integer id : idList) {
            String resultString = index1.concat(financeId.toString())
                    .concat(index2).concat(finaceName)
                    .concat(index3).concat(id.toString())
                    .concat(index4);
            result.add(resultString);
        }
        return result;
    }
}
