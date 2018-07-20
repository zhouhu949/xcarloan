package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerData;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCustomerDataListModel;
import com.fintecher.manage.vo.BasicCustomerDataModel;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 13:49
 * @Description:
 */
public interface BasicCustomerDataService extends BaseService<BasicCustomerData> {
    void addCustomerData(BasicCustomerDataListModel basicCustomerDataModel, SysUser sysUser);
}
