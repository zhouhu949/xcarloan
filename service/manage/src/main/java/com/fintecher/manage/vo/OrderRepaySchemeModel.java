package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;

@Data
@ApiModel("还款详情Model")
public class OrderRepaySchemeModel {
    @ApiModelProperty("费用项ID")
    private Long expenseId;
    @ApiModelProperty("还款计划ID")
    private Long repayTemplateId;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney;
    @ApiModelProperty("已还金额")
    private BigDecimal isRepayMoney;
    @ApiModelProperty("还款状态")
    private Integer repayStatus;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("未还金额")
    private BigDecimal money;

}
