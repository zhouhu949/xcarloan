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
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("订单还款详情")
@Table(name = "basic_order_repay_scheme")
public class BasicOrderRepayScheme extends BaseEntity {
    @ApiModelProperty("费用项ID")
    @Column(name = "expense_id")//测试
    private Long expenseId;
    @ApiModelProperty("还款计划ID")
    @Column(name = "repay_template_id")
    private Long repayTemplateId;
    @ApiModelProperty("订单ID")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty("期数")
    @Column(name = "periods")
    private Integer periods;
    @ApiModelProperty("还款金额")
    @Column(name = "repay_money")
    private BigDecimal repayMoney;
    @ApiModelProperty("已还金额")
    @Column(name = "is_repay_money")
    private BigDecimal isRepayMoney;
    @ApiModelProperty("还款状态")
    @Column(name = "repay_status")
    private Integer repayStatus;
    @ApiModelProperty("费用项名称")
    @Column(name = "expense_name")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    @Column(name = "expense_code")
    private String expenseCode;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;
    @Column(name = "repayment_date")
    private Date repaymentDate;

    public BasicOrderRepayScheme(Long expenseId,
                                 Long orderId, Integer periods,
                                 BigDecimal repayMoney, BigDecimal isRepayMoney,
                                 Integer repayStatus, String expenseName, String expenseCode,
                                 Long operator, Date operatorTime) {
        this.expenseId = expenseId;
        this.orderId = orderId;
        this.periods = periods;
        this.repayMoney = repayMoney;
        this.isRepayMoney = isRepayMoney;
        this.repayStatus = repayStatus;
        this.expenseName = expenseName;
        this.expenseCode = expenseCode;
        this.operator = operator;
        this.operatorTime = operatorTime;
    }

    /**
     * 还款状态
     */
    public enum RepayStatus {
        WAIT(10117, "待还"),
        SETTLE(10118,"结清"),
        OVER(10119, "逾期"),
        PART(10120, "部分还款"),
        BEFORE(10121, "提前结清"),
        RETURN(10122, "提前收回");


        private Integer value;
        private String remark;

        RepayStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (RepayStatus type : RepayStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
