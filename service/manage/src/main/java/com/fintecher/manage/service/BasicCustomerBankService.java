package com.fintecher.manage.service;

import com.fintecher.entity.BasicCarBrand;
import com.fintecher.entity.BasicCustomerBank;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/20 18:37
 * @Description:
 */
public interface BasicCustomerBankService extends BaseService<BasicCustomerBank> {
    /**
     * @auther: dwx
     * @Description:查询客户开户信息
     */
    List<BasicCustomerBank> queryCustomerBankInfo(Long customerId);

    BasicCustomerBank selectByCustomerId(Long customerId);
}
