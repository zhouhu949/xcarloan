package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomContact;
import com.fintecher.entity.BasicCustomerJob;
import com.fintecher.manage.mapper.BasicCustomerJobMapper;
import com.fintecher.manage.service.BasicCustomerJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCustomerJobServiceImpl extends BaseServiceImpl<BasicCustomerJob> implements BasicCustomerJobService {

    @Resource
    private BasicCustomerJobMapper basicPersionalJobMapper;



    @Override
    public List<BasicCustomerJob> findPersonalJobList(Long customId) {

        Example example1 = new Example(BasicCustomerJob.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("customerId", customId);
        List<BasicCustomerJob> personalJob = this.selectByExample(example1);
        return personalJob;
    }


}