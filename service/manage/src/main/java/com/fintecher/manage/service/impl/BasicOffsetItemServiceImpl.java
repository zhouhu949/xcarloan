package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicOffsetItem;
import com.fintecher.manage.mapper.BasicOffsetItemMapper;
import com.fintecher.manage.service.BasicOffsetItemService;
import com.fintecher.manage.vo.BasicOffsetItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional(rollbackFor = Exception.class)
@Service
public class BasicOffsetItemServiceImpl extends BaseServiceImpl<BasicOffsetItem> implements BasicOffsetItemService {

    @Autowired
    BasicOffsetItemMapper basicOffsetItemMapper;
    @Override
    public void insertBasicOffsetItem(BasicOffsetItem basicOffsetItem) {
        basicOffsetItemMapper.insertBasicOffsetItem(basicOffsetItem);
    }

    @Override
    public List<BasicOffsetItem> selectByItemOrderDesc() {
        return basicOffsetItemMapper.selectByItemOrderDesc();
    }

    @Override
    public void udpdateBasicOffsetItem(BasicOffsetItem basicOffsetItem) {

        basicOffsetItemMapper.updateBasicOffsetItem(basicOffsetItem);
    }

    @Override
    public void deleteBasicOffsetItem(BasicOffsetItem basicOffsetItem) {
        basicOffsetItemMapper.deleteBasicOffsetItem(basicOffsetItem);
    }

    @Override
    public int deleteCheck(Long expenseId) {
       int i =  basicOffsetItemMapper.deleteCheck(expenseId);
        return i;
    }

    @Override
    public List<BasicOffsetItemModel> selectByOffsetId(Long offsetId) {

        return basicOffsetItemMapper.selectByOffsetId(offsetId);
    }

    @Override
    public BasicOffsetItem selectById(Long id) {
        return basicOffsetItemMapper.selectById(id);
    }

    @Override
    public void updateItemOrder(BasicOffsetItem basicOffsetItem) {
        basicOffsetItemMapper.updateItemOrder(basicOffsetItem);
    }


}
