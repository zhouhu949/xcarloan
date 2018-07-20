package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomContact;
import com.fintecher.manage.service.BasicCustomContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCustomContactServiceImpl extends BaseServiceImpl<BasicCustomContact> implements BasicCustomContactService {


    @Override
    public List<BasicCustomContact> findCustomContactList(Long customId) {
        Example example1 = new Example(BasicCustomContact.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("customerId", customId);
        List<BasicCustomContact> basicCustomContact = this.selectByExample(example1);
        return basicCustomContact;
    }


}