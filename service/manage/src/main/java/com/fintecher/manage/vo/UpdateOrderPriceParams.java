package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("修改订单金额请求参数")
public class UpdateOrderPriceParams {
    @ApiModelProperty("orderId")
    @NotNull
    private Long orderId;
    @ApiModelProperty("订单金额")
    @NotNull(message = "订单金额不能为空")
    private BigDecimal price;
}
