package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepaySchemeExpenseModel {
    @ApiModelProperty("方案详情Id")
    private Long id;
    @ApiModelProperty("费用项ID")
    private Long expenseId;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    private String expenseCode;
    @ApiModelProperty("是否首付款")
    private Integer isFirst;
    @ApiModelProperty("收取总额比例")
    private BigDecimal repayProportion;
    @ApiModelProperty("固定费用")
    private BigDecimal fixedCost;
    @ApiModelProperty("还款方式")
    private Integer repayType;
    @ApiModelProperty("是否尾款")
    private Integer isLast;
    @ApiModelProperty("是否退款")
    private Integer isRefund;
}
