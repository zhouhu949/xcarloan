package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("还款计划模板Model")
public class BasicProductRepayTemplateModel {

    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private Long productId;

    /**
     * 还款方案比例详情Id
     */
    @ApiModelProperty("还款方案比例详情Id")
    private Long repaySchemeExpenseId;
    /**
     * 期数
     */
    @ApiModelProperty("期数")
    private int periods;

    /**
     * 还款金额
     */
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney;

    /**
     * 还款日期
     */
    @ApiModelProperty("还款日期")
    private int accountDay;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 操作人
     */
    private Long operator;//操作人
    /**
     * 操作日期
     */
    private Date operatorTime;//操作日期

    @ApiModelProperty("费用项名称")
    private String expenseName;

    @ApiModelProperty("费用项Id")
    private Long expenseId;

    @ApiModelProperty("费用项编码")
    private String expenseCode;

    public BasicProductRepayTemplateModel(Long productId, Long repaySchemeExpenseId, int periods, BigDecimal repayMoney, int accountDay, String expenseName, Long expenseId, String expenseCode) {
        this.productId = productId;
        this.repaySchemeExpenseId = repaySchemeExpenseId;
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.accountDay = accountDay;
        this.expenseName = expenseName;
        this.expenseId = expenseId;
        this.expenseCode = expenseCode;
    }

    public BasicProductRepayTemplateModel( Long repaySchemeExpenseId, int periods, BigDecimal repayMoney, int accountDay, String expenseName, Long expenseId, String expenseCode) {
        this.repaySchemeExpenseId = repaySchemeExpenseId;
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.accountDay = accountDay;
        this.expenseName = expenseName;
        this.expenseId = expenseId;
        this.expenseCode = expenseCode;
    }
    public BasicProductRepayTemplateModel() {}
    public BasicProductRepayTemplateModel(Long productId, Long repaySchemeExpenseId, int periods, BigDecimal repayMoney, int accountDay, Long operator, Date operatorTime, String expenseName, Long expenseId, String expenseCode) {
        this.productId = productId;
        this.repaySchemeExpenseId = repaySchemeExpenseId;
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.accountDay = accountDay;
        this.operator = operator;
        this.operatorTime = operatorTime;
        this.expenseName = expenseName;
        this.expenseId = expenseId;
        this.expenseCode = expenseCode;
    }

    public BasicProductRepayTemplateModel(int periods, BigDecimal repayMoney, String expenseName, String expenseCode) {
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.expenseName = expenseName;
        this.expenseCode = expenseCode;
    }
}
