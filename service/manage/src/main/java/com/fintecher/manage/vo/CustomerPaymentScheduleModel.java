package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/26 15:19
 * @Description:
 */
@Data
public class CustomerPaymentScheduleModel {

    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    @ApiModelProperty("客户ID")
    private Long customerId;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "贷款金额")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "订单类型")
    private Integer orderType;
    @ApiModelProperty(value = "还款方式")
    private Integer orderRepayType;
    @ApiModelProperty(value = "利率")
    private BigDecimal orderInterestRate;
    @ApiModelProperty(value = "周期类型")
    private Integer orderCycleType;
    @ApiModelProperty(value = "还款日")
    private Integer orderAccountDay;
    @ApiModelProperty(value = "期数")
    private Integer periods;
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney;
    @ApiModelProperty("已还金额")
    private BigDecimal isRepayMoney;
    @ApiModelProperty("还款状态")
    private Integer repayStatus;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    private String expenseCode;


}
