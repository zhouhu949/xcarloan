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
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 14:04
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("操作订单记录")
@Table(name = "basic_order_record")
public class BasicOrderRecord extends BaseEntity {

    @ApiModelProperty("订单ID")
    @Column(name = "order_id")
    private Long orderId;

    @ApiModelProperty(value = "订单环节")
    @Column(name = "order_link")
    private Integer orderLink;

    @ApiModelProperty(value = "订单状态")
    @Column(name = "order_status")
    private Integer orderStatus;

    @ApiModelProperty(value ="关联id")
    @Column(name = "relate_id")
    private Long relateId;

    @ApiModelProperty(value ="关联表名称")
    @Column(name = "link_table_name")
    private String linkTableName;

    @ApiModelProperty(value ="备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty(value ="操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value ="操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 订单环节
     */
    public enum OrderLink {
        APPLICATION(10095, "进件申请"),
        APPROVAL(10096, "审核"),
        RECEIPT(10097, "收款"),
        LENDING(10098, "放款"),
        REPAYMENT(10099, "还款"),
        SUPPLEMENT(10100, "补填资料"),
        SUPPLIER_LENDING(10101, "供应商放款"),
        SETTLE(10102, "提前结清"),
        RECOVER(10103, "提前收回"),
        RELIEF(10104, "减免"),
        FREEZE(10105, "冻结"),
        END(10106, "结案");

        private Integer value;
        private String remark;

        OrderLink(Integer value, String remark) {
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
            for (OrderLink type : OrderLink.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }




}