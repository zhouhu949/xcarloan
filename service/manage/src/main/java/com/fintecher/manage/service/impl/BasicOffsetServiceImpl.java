package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.BasicOffset;
import com.fintecher.entity.BasicOffsetItem;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicExpenseMapper;
import com.fintecher.manage.mapper.BasicOffsetItemMapper;
import com.fintecher.manage.mapper.BasicOffsetMapper;
import com.fintecher.manage.service.BasicOffsetService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Transactional(rollbackFor = Exception.class)
@Service
public class BasicOffsetServiceImpl extends BaseServiceImpl<BasicOffset> implements BasicOffsetService {

    @Resource
    private BasicOffsetMapper basicOffsetMapper;

    @Autowired
    BasicOffsetItemMapper basicOffsetItemMapper;

    @Resource
    private BasicExpenseMapper basicExpenseMapper;
    @Override
    public void updateBasicOffset(BasicOffset basicOffset) {
        basicOffsetMapper.updateBasicOffset(basicOffset);
    }

    @Override
    public int deletecheck(BasicOffset basicOffset) {
        return basicOffsetMapper.deletecheck(basicOffset);
    }

    @Override
    public List<BasicOffset> findBasicOffsetByAuth(List<String> allDataAuth, List<String> allExceptDataAuth) {
        Map<String, List<String >> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", allDataAuth);
        paramsMap.put("exceptDataAuth", allExceptDataAuth);
        return basicOffsetMapper.findBasicOffsetByAuth(paramsMap);
    }

    @Override
    public void addOffsetAndOffsetItem(BasicOffset basicOffset, SysUser user) {
        this.save(basicOffset);
        Long offsetId = basicOffset.getId();
        List<BasicExpense> basicExpenses = basicExpenseMapper.selectOffsetExpense(user.getOrgId());
        for (BasicExpense basicExpense : basicExpenses) {
            BasicOffsetItem offsetItem = new BasicOffsetItem();
            offsetItem.setOffsetId(offsetId);
            offsetItem.setExpenseId(basicExpense.getId());
            basicOffsetItemMapper.insertBasicOffsetItem(offsetItem);
        }
    }
}
