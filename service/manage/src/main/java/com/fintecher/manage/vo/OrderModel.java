package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderModel {
    @ApiModelProperty("客户ID")
    private Long customerId;
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("客户手机号")
    private String customerPhone;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单类型")
    private Integer orderType;
    @ApiModelProperty("订单状态")
    private Integer orderStatus;
    @ApiModelProperty("还款方式")
    private Integer orderRepayType;
    @ApiModelProperty("期数")
    private Integer orderPeriods;
    @ApiModelProperty("当前还款期数")
    private Integer orderNowPeriods;
    @ApiModelProperty("应还金额")
    private BigDecimal orderPrice;
    @ApiModelProperty("已还金额")
    private BigDecimal finishMoney;
    @ApiModelProperty("要还金额")
    private BigDecimal remainRepayment;
    @ApiModelProperty("还款方案ID")
    private Long schemeId;
    @ApiModelProperty("还款方案名称")
    private String schemeName;
}
