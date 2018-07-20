package com.fintecher.manage.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("退款Model")
public class RefundExpenseModel {
    @ApiParam("退款费用项名称")
    private String expenseName;
    @ApiParam("退款金额")
    private BigDecimal money;
}
