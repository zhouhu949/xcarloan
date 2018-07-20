package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerCarAssessment;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 11:30
 * @Description:
 */

public interface BasicCustomerCarAssessmentService extends BaseService<BasicCustomerCarAssessment>{
    void addBasicCustomerAssessment(BasicCustomerCarAssessmentModel customerCarAssessmentModel,SysUser sysUser);

    List<CustomerAssessmentListModel> findCustomerAssessmentList(String customerName, String orderNo,Integer assessmentStatus);

    List<CustomerAssessmentReportModel> findAssessmentReportList(Long carId);
    List findAssessmentConfigList();

    CustomerAssessmentInfoModel findAssessmentReportInfo(Long assessmentId);

    List<CarAttributeModel> findAllCarAttrValue(Long assessmentId);

}
