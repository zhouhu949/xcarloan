package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicOffsetItem;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicOffsetItemModel;

import java.util.List;

public interface BasicOffsetItemMapper extends MyMapper<BasicOffsetItem> {

    void insertBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    List<BasicOffsetItem> selectByItemOrderDesc();

    void updateBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    void deleteBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    int deleteCheck(Long expenseId);

    BasicOffsetItem selectById(Long id);


    List<BasicOffsetItemModel> selectByOffsetId(Long offsetId);

    void updateItemOrder(BasicOffsetItem basicOffsetItem);
}
