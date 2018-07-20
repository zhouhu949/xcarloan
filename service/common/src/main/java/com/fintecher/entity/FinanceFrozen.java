package com.fintecher.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 13:51
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Api("冻结记录")
@Table(name = "finance_frozen")
public class FinanceFrozen extends BaseEntity {
    @ApiModelProperty(value = "订单还款计划ID")
    private Long orderRepayId;
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal frozenMoney;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作人")
    private Integer operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

}
