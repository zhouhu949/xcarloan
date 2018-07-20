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

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "收款记录实体类")
@Entity
@Table(name = "finance_receivable")
public class FinanceReceivable extends BaseEntity{
    @ApiModelProperty(value = "机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty(value = "提前结清ID")
    @Column(name = "settle_id")
    private Long  settleId;
    @ApiModelProperty(value = "提前收回ID")
    @Column(name = "takeback_id")
    private Long  takebackId;
    @ApiModelProperty(value = "银行卡ID")
    @Column(name = "card_id")
    private Long  cardId ;
    @ApiModelProperty(value = "订单ID")
    @Column(name = "order_id")
    private Long  orderId;
    @ApiModelProperty(value = "收款类型")
    @Column(name = "receivable_type")
    private Integer  receivableType;
    @ApiModelProperty(value = "收款金额")
    @Column(name = "receivable_detial_money")
    private BigDecimal receivableDetialMoney;
    @ApiModelProperty(value = "收款日期")
    @Column(name = "receivable_date")
    private Date receivableDate;
    @ApiModelProperty(value = "是否已开发票")
    @Column(name = "is_invoice")
    private Integer  isInvoice;
    @ApiModelProperty(value = "是否已开收据")
    @Column(name = "is_receipt")
    private Integer  isReceipt;
    @ApiModelProperty(value = "路径")
    @Column(name = "file_url")
    private String  fileUrl;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long  operator;
    @ApiModelProperty(value = "操作日期")
    @Column(name = "operator_time")
    private Date  operatorTime;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String  remark;

    public enum ReceivableType{
        REPATMONEY(10133,"还款"),
        EARLYSETTLEMENT(10134,"提前结清"),
        EARLYWITHDRAWAL(10134,"提前收回"),
        FIRSTPAYMENT(110132,"首付款");
        private Integer value;
        private String remark;

        ReceivableType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }
    }

    public FinanceReceivable() {}
    public FinanceReceivable(Long orgId, Long cardId, Long orderId, Integer receivableType, BigDecimal receivableDetialMoney, Date receivableDate, Integer isInvoice, Integer isReceipt, Long operator, Date operatorTime) {
        this.orgId = orgId;
        this.cardId = cardId;
        this.orderId = orderId;
        this.receivableType = receivableType;
        this.receivableDetialMoney = receivableDetialMoney;
        this.receivableDate = receivableDate;
        this.isInvoice = isInvoice;
        this.isReceipt = isReceipt;
        this.operator = operator;
        this.operatorTime = operatorTime;
    }
}
