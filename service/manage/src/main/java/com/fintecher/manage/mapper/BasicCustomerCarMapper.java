package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomerCar;
import com.fintecher.entity.BasicCustomerCarAssessment;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicCustomerCarAssessmentModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 18:19
 * @Description:
 */
public interface BasicCustomerCarMapper extends MyMapper<BasicCustomerCar> {
    BasicCustomerCarAssessment findByStatus(@Param("id")Long id);
    List<Map<String,Object>> getBasicCustomerCarList(@Param("id")Long id);

}
