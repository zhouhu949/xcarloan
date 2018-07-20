package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerJob;

import java.util.List;

public interface BasicCustomerJobService extends BaseService<BasicCustomerJob> {


    /**
     * 客户联系人信息列表
     * @param
     */

    List<BasicCustomerJob> findPersonalJobList(Long customId);

}
