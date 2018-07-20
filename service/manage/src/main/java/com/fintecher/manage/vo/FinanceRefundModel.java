package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@Data
public class FinanceRefundModel {
    @ApiModelProperty("退款记录明细id")
    private Long id;
    @ApiModelProperty("退款金额")
    private BigDecimal refundDetialMoney;
    @ApiModelProperty("退款日期")
    private Date refundDate;
    @ApiModelProperty("退款费用项名称")
    private String expenseName;
    @ApiModelProperty("退款费用项编码")
    private String expenseCode;

}
