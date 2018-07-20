package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("还款方案比例详情")
public class RepayExpenseInfoModel {
    @ApiModelProperty("还款比例详情ID")
    private Long id;
    @ApiModelProperty("费用项ID")
    private Long expenseId;
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
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("操作人ID")
    private Long operator;
    @ApiModelProperty("操作人名")
    private String operatorName;
    @ApiModelProperty("操作日期")
    private Date operatorTime;
    @ApiModelProperty("费用项Code")
    private String expenseCode;
    @ApiModelProperty("费用项名称")
    private String expenseName;
}
