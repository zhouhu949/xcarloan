package com.fintecher.manage.vo;

import lombok.Data;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/7/2 18:55
 * @Description:
 */
@Data
public class CustomerAssessmentInfo {

    private Integer configType;
    private List<CarAttributeModel> data;
}
