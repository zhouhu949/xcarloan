package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
@ApiModel("首付款款订单")
public class ReceiptOrder {
    @ApiModelProperty("订单Id")
    @NotNull
    private Long orderId;
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("客户Id")
    private Long customerId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("收款金额")
    private BigDecimal proceedMoney;
    @ApiModelProperty("是否已收款 true 是，false 否")
    private boolean isRepay;
}
