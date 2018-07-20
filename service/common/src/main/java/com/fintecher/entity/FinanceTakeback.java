package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 15:21
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("提前收回记录")
@Table(name = "finance_takeback")
public class FinanceTakeback extends BaseEntity{
    @ApiModelProperty(value = "收款记录ID")
    @Column(name = "receivable_id")
    private Long receivableId;
    @ApiModelProperty(value = "订单ID")
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "org_id")
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    @ApiModelProperty(value = "提前收回时间")
    @Column(name = "takeback_date")
    private Date takebackDate;
    @ApiModelProperty(value = "提前收回金额")
    @Column(name = "takeback_money")
    private BigDecimal takebackMoney;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;

    public FinanceTakeback(Long receivableId, Long orderId, Long orgId, Date takebackDate, BigDecimal takebackMoney, Long operator, Date operatorTime) {
        this.receivableId = receivableId;
        this.orderId = orderId;
        this.orgId = orgId;
        this.takebackDate = takebackDate;
        this.takebackMoney = takebackMoney;
        this.operator = operator;
        this.operatorTime = operatorTime;
    }
}
