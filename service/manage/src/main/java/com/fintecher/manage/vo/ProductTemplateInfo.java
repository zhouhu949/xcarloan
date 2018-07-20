package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductTemplateInfo {

    @ApiModelProperty("还款计划ID")
    private Long id;
    @ApiModelProperty("产品ID")
    private Long productId;
    @ApiModelProperty("还款方案比例详情Id")
    private Long schemeExpenseId;
    @ApiModelProperty("费用项ID")
    private Long expenseId;
    @ApiModelProperty("费用项Code")
    private String expenseCode;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("期数")
    private int periods;
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney;
    @ApiModelProperty("还款日期")
    private int accountDay;
}
