package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/7/5 15:57
 * @Description:
 */
@Data
public class FindCarByFinanceStorageModel {
    @ApiModelProperty(value = "车辆id")
     private Long carId;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
   @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
}
