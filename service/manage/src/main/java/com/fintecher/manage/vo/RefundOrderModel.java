package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("退款订单model")
public class RefundOrderModel {
    @ApiModelProperty("订单Id")
    private Long orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单客户姓名")
    private String customerName;
    @ApiModelProperty("退款金额")
    private BigDecimal repayMoney;
}
