package com.fintecher.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "basic_product_repay_template")
@ApiModel("产品还款计划模板实体")
public class BasicProductRepayTemplate extends BaseEntity{
    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    @Column(name = "product_id")
    private Long productId;//产品ID

    /**
     * 还款方案比例详情Id
     */
    @ApiModelProperty("还款方案比例详情Id")
    @Column(name = "repay_scheme_expense_id")
    private Long repaySchemeExpenseId;
    /**
     * 期数
     */
    @ApiModelProperty("期数")
    @Column(name = "periods")
    private int periods;//期数

    /**
     * 还款金额
     */
    @ApiModelProperty("还款金额")
    @Column(name = "repay_money")
    private BigDecimal repayMoney; //还款金额

    /**
     * 还款日期
     */
    @ApiModelProperty("还款日期")
    @Column(name = "account_day")
    private int accountDay; //还款日期
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;//备注

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;//操作人

    /**
     * 操作日期
     */
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;//操作日期

    public BasicProductRepayTemplate() {}

    public BasicProductRepayTemplate( int periods, BigDecimal repayMoney) {
        this.periods = periods;
        this.repayMoney = repayMoney;
    }
}
