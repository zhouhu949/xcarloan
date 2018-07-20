package com.fintecher.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
@Data
@Entity
@Table(name = "finance_refund_detial")
public class FinanceRefundDetial extends BaseEntity {
    @ApiModelProperty(value = "银行卡Id")
    @Column(name = "card_id")
    private Long cardId; //'银行卡Id',
    @ApiModelProperty(value = "退款记录ID")
    @Column(name = "refund_id")
    private Long refundId;//'退款记录ID',
    @ApiModelProperty(value = "费用项ID")
    @Column(name = "expense_id")
    private Long expenseId; //'`费用项ID'
    @ApiModelProperty(value = "退款金额")
    @Column(name = "refund_detial_money")
    private BigDecimal refundDetialMoney;
}
