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
@Table(name = "basic_repay_scheme")
@Entity
@ApiModel("还款方案实体")
public class BasicRepayScheme extends BaseEntity {
    @ApiModelProperty("冲抵ID")
    @Column(name = "offset_id")
    private Long offsetId;
//    @ApiModelProperty("审核ID")
//    @Column(name = "review_id")
//    private Long reviewId;
    @ApiModelProperty("机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty("方案类型: 10049-融资租赁，10050-抵押贷款")
    @Column(name = "scheme_type")
    private Integer schemeType;
    @ApiModelProperty("方案名称")
    @Column(name = "scheme_name")
    private String schemeName;
    @ApiModelProperty("还款方式：10051-按期付息还本,10052-等额本息,10053-等额本金")
    @Column(name = "repay_type")
    private Integer repayType;
    @ApiModelProperty("抵押方式:10054-质押，10055-抵押")
    @Column(name = "mortgage_type")
    private Integer mortgageType;
    @ApiModelProperty("发布状态：10056-未发布，10057-已发布")
    @Column(name = "scheme_status")
    private Integer schemeStatus;
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty("征信保护天数")
    @Column(name = "credit_days")
    private Integer creditDays;
    @ApiModelProperty("逾期保护天数")
    @Column(name = "overdue_days")
    private Integer overdueDays;
    @ApiModelProperty("期数")
    @Column(name = "periods")
    private Integer periods;
    @ApiModelProperty("利率")
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @ApiModelProperty("周期类型:10058-月")
    @Column(name = "cycle_type")
    private Integer cycleType;
    @ApiModelProperty("融资最小金额")
    @Column(name = "money_min")
    private BigDecimal moneyMin;
    @ApiModelProperty("融资最大金额")
    @Column(name = "money_max")
    private BigDecimal moneyMax;
    @ApiModelProperty("账期类型：10059-正常账期，10060-固定账期")
    @Column(name = "account_period_type")
    private Integer accountPeriodType;
    @ApiModelProperty("还款日")
    @Column(name = "account_day")
    private Integer accountDay;
    @ApiModelProperty("审核流程KEY")
    @Column(name = "work_flow_key")
    private String workFlowKey;

    /**
     * 方案类型
     */
    public enum SchemeType {
        FINANCING(10049, "融资租赁"),
        MORTGAGE(10050, "抵押贷款");

        private Integer value;
        private String remark;

        SchemeType(Integer value, String remark) {
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
            for (SchemeType type : SchemeType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 还款方式
     */
    public enum RepayType {
        PAYMENT_SCHEDULE(10051, "一次性还本付息"),
        PAYMENT_INTEREST(10052, "等额本息"),
        PAYMENT_PRIN(10053, "等额本金");

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

    /**
     * 抵押方式
     */
    public enum MortgageType {
        PLEDGE(10054, "质押"),
        MORTGAGE(10055, "抵押");

        private Integer value;
        private String remark;

        MortgageType(Integer value, String remark) {
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
            for (MortgageType type : MortgageType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 发布状态
     */
    public enum SchemeStatus {
        ENABLE(10056, "未发布"),
        DISABLE(10057, "已发布");

        private Integer value;
        private String remark;

        SchemeStatus(Integer value, String remark) {
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
            for (SchemeStatus type : SchemeStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 账期类型
     */
    public enum AccountPeriodType {
        NORMAL(10059, "正常账期"),
        FIX(10060, "固定账期");

        private Integer value;
        private String remark;

        AccountPeriodType(Integer value, String remark) {
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
            for (AccountPeriodType type : AccountPeriodType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 账期类型
     */
    public enum CycleType {
        MONTH(10058, "月");

        private Integer value;
        private String remark;

        CycleType(Integer value, String remark) {
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
            for (CycleType type : CycleType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

}
