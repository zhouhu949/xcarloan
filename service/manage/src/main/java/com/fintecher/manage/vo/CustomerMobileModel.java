package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/26 18:42
 * @Description:
 */
@Data
public class CustomerMobileModel {
    @ApiModelProperty(value = "客户id")
    private Long customerId;

}
