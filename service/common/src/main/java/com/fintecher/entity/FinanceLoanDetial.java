package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "放款明细实体")
@Entity
@Table(name = "finance_loan_detial")
public class FinanceLoanDetial extends BaseEntity{
    @ApiModelProperty(value = "放款Id")
    @Column(name = "loan_id")
    private Long loanId;
    @ApiModelProperty(value = "费用id")
    @Column(name = "expense_id")
    private Long expenseId;
    @ApiModelProperty(value = "银行卡ID")
    @Column(name = "card_id")
    private Long cardId;
    @ApiModelProperty(value = "放款金额")
    @Column(name = "loan_money")
    private BigDecimal loanMoney;

    public FinanceLoanDetial() {
    }

    public FinanceLoanDetial(Long loanId, Long expenseId, Long cardId, BigDecimal loanMoney) {
        this.loanId = loanId;
        this.expenseId = expenseId;
        this.cardId = cardId;
        this.loanMoney = loanMoney;
    }
}
