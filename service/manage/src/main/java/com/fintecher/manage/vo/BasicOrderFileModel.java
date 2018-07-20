package com.fintecher.manage.vo;

import com.fintecher.entity.BasicCustomerOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 13:39
 * @Description:
 */
@Data
public class BasicOrderFileModel {

    @ApiModelProperty(value = "资料id")
    private Long id;

   @ApiModelProperty(value = "资料类型/url")
   private List<CustomerOrderFileModel> customerOrderFileModels;




}
