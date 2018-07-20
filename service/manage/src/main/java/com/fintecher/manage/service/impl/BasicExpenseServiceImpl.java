package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpense;
import com.fintecher.manage.mapper.BasicExpenseMapper;
import com.fintecher.manage.service.BasicExpenseService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicExpenseServiceImpl extends BaseServiceImpl<BasicExpense>  implements BasicExpenseService {


    @Resource
    private BasicExpenseMapper basicExpenseMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void insert(BasicExpense basicExpense) {
        basicExpenseMapper.save(basicExpense);
    }

    public void delete(BasicExpense basicExpense){
        basicExpenseMapper.deletePrimaryKey(basicExpense);
    }


    @Override
    public List<BasicExpense> findAll(){
        List<BasicExpense> list =  basicExpenseMapper.selectAll();
        return list;
    }

    @Override
    public void updatePrimaryKey(BasicExpense basicExpense){
      basicExpenseMapper.updatePrimaryKey(basicExpense);

    }

    @Override
    public Integer check(BasicExpense basicExpense){
        Integer i = basicExpenseMapper.check(basicExpense);
        if(i==null)
            i=0;
        return i;
    }

    @Override
    public void copyTemplate(BasicExpense basicExpense) {
        basicExpenseMapper.copyTemplate(basicExpense);
    }

    @Override
    public int addCheck(BasicExpense basicExpense) {
        return basicExpenseMapper.addCheck(basicExpense);
    }

    @Override
    public int updateCheck(BasicExpense basicExpense) {
        return basicExpenseMapper.updateCheck(basicExpense);
    }

    @Override
    public List<BasicExpense> findBasicExpenseByAuth(List<String> dataAuth, List<String> exceptDataAuth) {
        Map<String, List<String >> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        return basicExpenseMapper.findBasicExpenseByAuth(paramsMap);
    }

    @Override
    public void insertOverdueFine(Integer repayStatus, Long operator, Date operatorTime, String expenseCode) {
        Map map = new HashMap();
        map.put("repayStatus",repayStatus);
        map.put("operator",operator);
        map.put("operatorTime",operatorTime);
        map.put("expenseCode",expenseCode);
        basicExpenseMapper.insertOverdueFine(map);
    }

}
