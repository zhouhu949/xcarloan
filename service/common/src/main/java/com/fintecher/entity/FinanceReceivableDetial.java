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

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "收款记录实体类")
@Entity
@Table(name = "finance_receivable_detial")
public class FinanceReceivableDetial extends BaseEntity{
    @ApiModelProperty(value = "订单还款计划ID")
    @Column(name = "order_repay_id")
    private Long orderRepayId;//订单还款计划ID',
    @ApiModelProperty(value = "收款记录ID")
    @Column(name = "receivable_id")
    private Long receivableId ;//收款记录ID',
    @ApiModelProperty(value = "收款金额")
    @Column(name = "receivable_detial_money")
    private BigDecimal receivableDetialMoney;//收款金额',
}
