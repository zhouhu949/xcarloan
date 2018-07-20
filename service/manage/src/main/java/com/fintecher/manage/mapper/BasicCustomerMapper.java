package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.CustomerIntentionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerMapper
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/19 0019下午 14:53
 */
public interface BasicCustomerMapper extends MyMapper<BasicCustomer> {

    List queryCustomerByCondition(BasicCustomer basicCustomer);
    List findNotBlackCustomerList(BasicCustomer basicCustomer);

    List findBasicCustomerOrderList(@Param("id")Long id);

    List<BasicCustomer> findCustomerSignList(@Param("customerName")String customerName);
    List<CustomerIntentionModel> findCustomerIntentionList(BasicCustomer basicCustomer);

    BasicCustomer findCustomerInfoByOrderId(@Param("orderId") Long orderId);
    BasicCustomer findCustomerInfoByCustomer(@Param("customerPhone")String customerPhone,@Param("idCard")String idCard);
}
