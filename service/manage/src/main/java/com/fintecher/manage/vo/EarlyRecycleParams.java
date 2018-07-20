package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("提前收回传参")
public class EarlyRecycleParams {
    @ApiModelProperty("订单Id")
    private Long OrderId;
    @ApiModelProperty("收款金额")
    private BigDecimal money;
}
