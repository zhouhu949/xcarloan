package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepayTemplateModel {

    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney;
    @ApiModelProperty("费用项Id")
    private Long expenseId;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    private String expenseCode;

}
