package com.fintecher.manage.service;


import com.fintecher.entity.BasicCustomerHouse;

import java.util.List;

public interface BasicCustomHouseService extends BaseService<BasicCustomerHouse> {

    /**
     * 客户联系人信息列表
     * @param
     */

    List<BasicCustomerHouse> findCustomHouseList(Long customId);

}
