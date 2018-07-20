package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BasicCustomerOrderMapper extends MyMapper<BasicCustomerOrder> {

    /**
     * 订单查询
     * @param map
     * @return
     */
    List<OrderInfoModel> query(Map map);

    List<OrderInfoModel>findCustomerOrderFile(Map map);

    /**
     * @auther: dwx
     * @Description:押品查询
     */
    List<BasicCustomerOrderModel> findCustomerCollateral(@Param("orderId")Long orderId);

    /**
     * @auther: dwx
     * @Description:获取评估记录
     */
    List<BasicCustomerCollateralModel> findAssessmentByCustomerCar(@Param("carId")Long carId);

    /**
     * @auther: dwx
     * @Description:获取抵押记录
     */
    List<BasicFinanceMortgageModel> findMortgageByCustomerCar(@Param("carId")Long carId);

    /**
     * @auther: dwx
     * @Description:获取质押记录
     */
    List<BasicFinancePledgeModel> findPledgeByCustomerCar(@Param("carId")Long carId);

    /**
     * @auther: dwx
     * @Description:还款计划
     */
    List<CustomerPaymentScheduleModel> customerPaymentScheduleList(@Param("orderId")Long orderId);

    /**
     * 获取订单还款情况列表
     * @param orderId
     * @return
     */
    List<HashMap> queryOrderPayList(Long orderId);
    List<HashMap>  queryOrderPayFeeItemList(Map map);
}
