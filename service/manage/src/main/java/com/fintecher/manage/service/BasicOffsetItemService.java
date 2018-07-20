package com.fintecher.manage.service;

import com.fintecher.entity.BasicOffsetItem;
import com.fintecher.manage.vo.BasicOffsetItemModel;

import java.util.List;

public interface BasicOffsetItemService extends BaseService<BasicOffsetItem> {


    void insertBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    List<BasicOffsetItem> selectByItemOrderDesc();

    void udpdateBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    void deleteBasicOffsetItem(BasicOffsetItem basicOffsetItem);

    int deleteCheck(Long expenseId);

    List<BasicOffsetItemModel> selectByOffsetId(Long offsetId);

    BasicOffsetItem selectById(Long id);

    void updateItemOrder(BasicOffsetItem basicOffsetItem);
}
