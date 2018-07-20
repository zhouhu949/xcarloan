package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 14:05
 * @Description:
 */
@Data
public class CustomerFinanceReceivableModel {
    @ApiModelProperty(value = "订单还款计划ID")
    private Long orderRepayId;
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal frozenMoney;
    @ApiModelProperty(value = "减免金额")
    private BigDecimal remitMoney;
    @ApiModelProperty(value = "提前结清ID")
    private Long  settleId;
    @ApiModelProperty(value = "提前收回ID")
    private Long  takebackId;
    @ApiModelProperty(value = "银行卡ID")
    private Long  cardId ;
    @ApiModelProperty(value = "收款类型")
    private Integer  receivableType;
    @ApiModelProperty(value = "收款金额")
    private BigDecimal receivableDetialMoney;
    @ApiModelProperty(value = "收款日期")
    private Date receivableDate;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作人")
    private Integer operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;
}
