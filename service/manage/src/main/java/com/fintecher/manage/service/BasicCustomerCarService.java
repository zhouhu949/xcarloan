package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCustomerCarModel;

import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 15:25
 * @Description:
 */
public interface BasicCustomerCarService extends BaseService<BasicCustomerCar>{
    /**
     * @auther: dwx
     * @Description:新增客户车产
     */
    void addAssessmentApplication(BasicCustomerCarModel basicCustomerCarModel, SysUser sysUser);

    /**
     * @auther: dwx
     * @Description:修改客户车产
     */
    void updateAssessmentApplication(BasicCustomerCarModel basicCustomerCarModel,SysUser sysUser);

    /**
     * @auther: dwx
     * @Description:获取所有客户车产列表
     */
    List<Map<String,Object>> findCustomerCarList(Long id);

}
