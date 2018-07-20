package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款记录
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "退款记录实体")
@Table(name = "finance_refund")
@Entity
public class FinanceRefund extends BaseEntity{
    @ApiModelProperty(value = "订单id")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty(value = "机构")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty(value = "退款金额")
    @Column(name = "refund_money")
    private BigDecimal refundMoney;
    @ApiModelProperty(value = "退款时间")
    @Column(name = "refund_date")
    private Date refundDate;
    @ApiModelProperty(value = "是否已开发票")
    @Column(name = "is_invoice")
    private Integer isInvoice;
    @ApiModelProperty(value = "是否已开收据")
    @Column(name = "is_receipt")
    private Integer isReceipt;
    @ApiModelProperty(value = "路径")
    @Column(name = "file_url")
    private String fileUrl;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    public enum YesOrNo{
        YES(10002,"是"),
        No(10003,"否");
        private Integer value;
        private String name;
        YesOrNo(Integer value,String name){
            this.value = value;
            this.name = name;
        }
        public Integer getValue(){
            return value;
        }
    }
}
