package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 16:49
 * @Description:
 */
@Data
public class CustomerOrderMortgageModel {
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "抵押方式")
    private Integer orderMortgageType;

    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "购车价格")
    private BigDecimal carPrice;
    @ApiModelProperty(value = "车辆型号")
    private Integer carType;
    @ApiModelProperty(value = "是否二手车")
    private Integer isSecondHand;
    @ApiModelProperty(value = "初次登记时间")
    private Date registerTime;
    @ApiModelProperty(value = "抵押登记次数")
    private Integer mortgageNum;
    @ApiModelProperty(value = "是否贷款已还请")
    private Integer isLoanFinished;
    @ApiModelProperty(value = "抵押状态")
    private Integer carStatus;
    @ApiModelProperty(value = "操作人")
    private Long operator;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

}
