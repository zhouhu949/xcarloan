package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("提前结清详情Model")
public class PrepaymentModel {
    @ApiModelProperty("订单Id")
    private Long orderId;
    @ApiModelProperty("应还金额")
    private BigDecimal repayMoney;
    @ApiModelProperty("已还金额")
    private BigDecimal isRepayMoney;
    @ApiModelProperty("剩余未还金额")
    private BigDecimal money;
    @ApiModelProperty("费用项")
    private String expenseName;

}
