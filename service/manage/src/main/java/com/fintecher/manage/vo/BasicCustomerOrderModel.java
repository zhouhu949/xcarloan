package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/22 19:06
 * @Description:
 */
@Data
public class BasicCustomerOrderModel {

    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "贷款金额")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "还款方式")
    private Integer orderRepayType;
    @ApiModelProperty("客户ID")
    private Long customerId;
    @ApiModelProperty(value = "操作人")
    private Long operator;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

}
