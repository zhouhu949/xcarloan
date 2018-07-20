package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomerHouse;
import com.fintecher.manage.service.BasicCustomHouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCustomHouseServiceImpl extends BaseServiceImpl<BasicCustomerHouse> implements BasicCustomHouseService {


    @Override
    public List<BasicCustomerHouse> findCustomHouseList(Long customId) {
        Example example1 = new Example(BasicCustomerHouse.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("customerId", customId);
        List<BasicCustomerHouse> customHouses = this.selectByExample(example1);
        return customHouses;
    }


}