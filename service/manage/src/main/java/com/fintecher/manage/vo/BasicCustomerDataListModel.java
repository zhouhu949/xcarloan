package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/7/2 14:20
 * @Description:
 */
@Data
public class BasicCustomerDataListModel {
    @ApiModelProperty(value = "资料id")
    private Long id;

    @ApiModelProperty(value = "资料类型/url")
    private List<BasicCustomerDataModel> basicCustomerDataModels;
}
