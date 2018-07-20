package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomerHouse;
import com.fintecher.manage.util.MyMapper;

import java.util.List;

public interface BasicCustomHouseMapper extends MyMapper<BasicCustomerHouse> {
    List<BasicCustomerHouse>  findCustomHouseList(Long custemId);
}
