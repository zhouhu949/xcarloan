package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarBrand;
import com.fintecher.entity.BasicCustomerBank;
import com.fintecher.manage.mapper.BasicCustomerBankMapper;
import com.fintecher.manage.service.BasicCustomerBankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/20 18:38
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicCustomerBankServiceImpl extends BaseServiceImpl<BasicCustomerBank> implements BasicCustomerBankService {
    @Resource
    private BasicCustomerBankMapper basicCustomerBankMapper;
    @Override
    public List<BasicCustomerBank> queryCustomerBankInfo(Long customerId){
        return basicCustomerBankMapper.queryCustomerBankInfo(customerId);
    }

    @Override
    public BasicCustomerBank selectByCustomerId(Long customerId) {
        BasicCustomerBank basicCustomerBank = new BasicCustomerBank();
        basicCustomerBank.setCustomerId(customerId);
        basicCustomerBank.setAccountStatus(BasicCustomerBank.OpenAccountStatus.SUCCESS.getValue());
        return basicCustomerBankMapper.selectByCustomerId(basicCustomerBank);
    }
}
