package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCarBrand;
import com.fintecher.entity.BasicCustomerBank;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/20 19:18
 * @Description:
 */
public interface BasicCustomerBankMapper extends MyMapper<BasicCustomerBank> {
    /**
     * @auther: dwx
     * @Description:查看客户开户信息
     */
    List<BasicCustomerBank> queryCustomerBankInfo(@Param("customerId")Long customerId);

    BasicCustomerBank selectByCustomerId(BasicCustomerBank basicCustomerBank);

}
