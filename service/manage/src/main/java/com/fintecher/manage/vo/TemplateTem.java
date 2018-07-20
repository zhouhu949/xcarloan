package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class TemplateTem {

   private String tableName;
    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private Long productId;//产品ID
    /**
     * 还款方案比例详情Id
     */
    @ApiModelProperty("还款方案比例详情Id")
    private Long repaySchemeExpenseId;
    /**
     * 期数
     */
    @ApiModelProperty("期数")
    private int periods;//期数
    /**
     * 还款金额
     */
    @ApiModelProperty("还款金额")
    private BigDecimal repayMoney; //还款金额
    /**
     * 还款日期
     */
    @ApiModelProperty("还款日期")
    private int accountDay; //还款日期
    public TemplateTem(){}
    public TemplateTem(String TableName,Long repaySchemeExpenseId, int periods, BigDecimal repayMoney, int accountDay) {
        this.repaySchemeExpenseId = repaySchemeExpenseId;
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.accountDay = accountDay;
        this.tableName=TableName;

    }
}
