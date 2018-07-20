package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@ApiModel("供应商开票")
public class FinanceLoanModel {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("订单Id")
    private Long orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("金额")
    private BigDecimal loanMoney;
    @ApiModelProperty("放款日期")
    private Date loanDate;
    @ApiModelProperty("是否收到发票")
    private Integer hasInvoice;
    @ApiModelProperty("是否收到收据")
    private Integer hasReceipt;

}
