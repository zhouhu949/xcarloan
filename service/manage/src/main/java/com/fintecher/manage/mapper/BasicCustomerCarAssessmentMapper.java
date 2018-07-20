package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCarAttribute;
import com.fintecher.entity.BasicCarAttrvalue;
import com.fintecher.entity.BasicCustomerCarAssessment;
import com.fintecher.entity.FinanceStorage;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 11:18
 * @Description:
 */

public interface BasicCustomerCarAssessmentMapper extends MyMapper<BasicCustomerCarAssessment> {


    /**
     * @auther: dwx
     * @Description:客户评估列表
     */
    List<CustomerAssessmentListModel>findCustomerAssessmentList(@Param("customerName")String customerName,@Param("carNo") String carNo,@Param("assessmentStatus")Integer assessmentStatus);
    List findAssessmentConfigList();

    List<CustomerAssessmentReportModel>findAssessmentReportList(@Param("carId")Long carId);

    CustomerAssessmentInfoModel findAssessmentReportInfo(@Param("assessmentId")Long assessmentId);
    CustomerCarInfoModel findCustomerCarInfo(@Param("carId")Long carId);
    List<CarAttributeModel> findAllCarAttrValue(@Param("assessmentId")Long assessmentId);
    FindCarByFinanceStorageModel findOrderByFinanceStorage(@Param("carId")Long carId);

    FinanceStorage findCarByFianceStorage(@Param("carId")Long carId);


}
