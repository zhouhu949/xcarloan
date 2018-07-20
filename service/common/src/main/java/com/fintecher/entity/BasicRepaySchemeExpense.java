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
@Table(name = "basic_repay_scheme_expense")
@ApiModel(description = "还款方案比例详情实体")
public class BasicRepaySchemeExpense extends BaseEntity {
    @ApiModelProperty("方案ID")
    @Column(name = "scheme_id")
    private Long schemeId;
    @ApiModelProperty("费用项ID")
    @Column(name = "expense_id")
    private Long expenseId;
    @ApiModelProperty("是否首付款")
    @Column(name = "is_first")
    private Integer isFirst;
    @ApiModelProperty("收取总额比例")
    @Column(name = "repay_proportion")
    private BigDecimal repayProportion;
    @ApiModelProperty("固定费用")
    @Column(name = "fixed_cost")
    private BigDecimal fixedCost;
    @ApiModelProperty("还款方式")
    @Column(name = "repay_type")
    private Integer repayType;
    @ApiModelProperty("是否尾款")
    @Column(name = "is_last")
    private Integer isLast;
    @ApiModelProperty("是否退款")
    @Column(name = "is_refund")
    private Integer isRefund;
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 费用项还款方式
     */
    public enum RepayType {
        ONCE(10063, "一次性收取"),
        MULTIPLE(10064, "分期");

        private Integer value;
        private String remark;

        RepayType(Integer value, String remark) {
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
            for (RepayType type : RepayType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
