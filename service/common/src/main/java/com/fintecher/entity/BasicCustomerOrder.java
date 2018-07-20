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

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 14:15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("订单")
@Table(name = "basic_customer_order")
public class BasicCustomerOrder extends BaseEntity {
    @ApiModelProperty("还款方案Id")
    @Column(name = "scheme_id")
    private Long schemeId;
    @ApiModelProperty("还款计划ID")
    @Column(name = "repay_template_id")
    private Long repayTemplateId;
    @ApiModelProperty("产品ID")
    @Column(name = "product_id")
    private Long productId;
    @ApiModelProperty("机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty("机构ID")
    @Column(name = "dept_id")
    private Long deptId;
    @ApiModelProperty("客户ID")
    @Column(name = "customer_id")
    private Long customerId;
    @ApiModelProperty("审核流程KEY")
    @Column(name = "work_flow_key")
    private String workFlowKey;
    @ApiModelProperty("审核流程ID")
    @Column(name = "work_flow_id")
    private String workFlowId;
    @ApiModelProperty("审核环节名称")
    @Column(name = "work_flow_link_name")
    private String workFlowLinkName;
    @ApiModelProperty(value = "订单编号")
    @Column(name = "order_no")
    private String orderNo;
    @ApiModelProperty(value = "贷款金额")
    @Column(name = "order_price")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "订单类型")
    @Column(name = "order_type")
    private Integer orderType;
    @ApiModelProperty(value = "还款方式")
    @Column(name = "order_repay_type")
    private Integer orderRepayType;
    @ApiModelProperty("订单状态")
    @Column(name = "order_status")
    private Integer orderStatus;
    @ApiModelProperty(value = "抵押方式")
    @Column(name = "order_mortgage_type")
    private Integer orderMortgageType;
    @ApiModelProperty(value = "征信保护天数")
    @Column(name = "order_credit_days")
    private Integer orderCreditDays;
    @ApiModelProperty(value = "逾期保护天数")
    @Column(name = "order_overdue_days")
    private Integer orderOverdueDays;
    @ApiModelProperty(value = "利率")
    @Column(name = "order_interest_rate")
    private BigDecimal orderInterestRate;
    @ApiModelProperty(value = "周期类型")
    @Column(name = "order_cycle_type")
    private Integer orderCycleType;
    @ApiModelProperty(value = "还款日")
    @Column(name = "order_account_day")
    private Integer orderAccountDay;
    @ApiModelProperty(value = "期数")
    @Column(name = "order_periods")
    private Integer orderPeriods;
    @ApiModelProperty("订单当前还款期数")
    @Column(name = "order_now_periods")
    private Integer orderNowPeriods;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty(value = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 订单状态
     */
    public enum OrderStatus {
        UNFILLED_DATA(10107, "待补填资料"),
        PENDING(10108, "待审核"),
        PENDING_PAYMENT(10109, "待收款"),
        NOT_MENTIONED_CAR(10110, "待提车"),
        REPAYMENT(10111, "还款中"),
        FINANCE_SETTLEMENT(10112, "提前结清"),
        FINANCE_TAKE_BACK(10113, "提前回收"),
        OVERDUE_REPAYMENT(10114, "逾期还款"),
        PENDING_OVER(10115, "待结案"),
        PENDING_CLOSED(10116, "已结案"),
        UNPAID(10144,"待放款"),
        REFUSE(10145, "拒件");

        private Integer value;
        private String remark;

        OrderStatus(Integer value, String remark) {
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
            for (OrderStatus type : OrderStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum MortgageType{
        PLEDGE(10054,"质押"),
        MORTGAGE(10055,"抵押");

        private Integer value;
        private String remark;

        MortgageType(Integer value,String remark){
            this.value = value;
            this.remark = remark;
        }
        public Integer getValue() {
            return value;
        }
        public String getRemark(){
            return remark;
        }

    }

    /**
     * 还款方式
     */
    public enum OrderRepayType {
        PAYMENT_SCHEDULE(10051, "按期付息还本"),
        PAYMENT_INTEREST(10052, "等额本息"),
        PAYMENT_PRIN(10053, "等额本金");

        private Integer value;
        private String remark;

        OrderRepayType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }
        public Integer getValue(){return value;}
    }

        /**
         * 订单类型
         */
        public enum OrderType {
            FINANCIALLEASE(10049, "融资租赁"),
            MORTGAGE(10050, "抵押贷款");
            private Integer value;
            private String remark;

            OrderType(Integer value, String remark) {
                this.value = value;
                this.remark = remark;
            }
        public Integer getValue() {
            return value;
            }
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (BasicRepayScheme.RepayType type : BasicRepayScheme.RepayType.values())
            {
                if (Objects.equals(type.getValue(), value))
                {
                    return true;
                }
            }
            return false;
        }
    }


