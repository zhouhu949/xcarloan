package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("首付款费用项Model")
public class GatheringModel {
    @ApiModelProperty("订单Id")
    private Long orderId;
    @ApiModelProperty("费用项Id")
    private Long expenseId;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项名称")
    private String expenseCode;
    @ApiModelProperty("收款金额")
    private BigDecimal repayMoney;
}
