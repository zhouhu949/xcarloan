package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/7/6 17:12
 * @Description:
 */
@Data
public class CustomerOrderPendingClosedModel {
    @ApiModelProperty(value = "订单id")
    private Long orderId;
}
