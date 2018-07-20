package com.fintecher.entity;

import io.swagger.annotations.Api;
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

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 10:44
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("放款记录")
@Table(name = "finance_loan")
public class FinanceLoan extends BaseEntity {
    @ApiModelProperty(value = "机构id")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty(value = "供应商id")
    @Column(name = "supplier_id")
    private Long supplierId;
    @ApiModelProperty(value = "订单id")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty(value = "放款类型")
    @Column(name = "loan_type")
    private Integer loanType;
    @ApiModelProperty(value = "放款金额")
    @Column(name = "loan_money")
    private BigDecimal loanMoney;
    @ApiModelProperty(value = "放款日期")
    @Column(name = "loan_date")
    private Date loanDate;
    @ApiModelProperty(value = "是否收到发票")
    @Column(name = "has_invoice")
    private Integer hasInvoice;
    @ApiModelProperty(value = "是否收到收据")
    @Column(name = "has_receipt")
    private Integer hasReceipt;
    @ApiModelProperty(value = "路径")
    @Column(name = "file_url")
    private String fileUrl;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value = "操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    public enum LoanType{
        CUSTOMER_LOAN(10149,"客户放款"),
        SUPPLIER_LOAN(10101,"供应商放款"),
        SUPPLIER_YES(10002,"是"),
        SUPPLIER_NO(10003,"否");
        private Integer value;
        private String name;
        LoanType(Integer type,String name){
            this.value=type;
            this.name=name;
        }
        public Integer getValue(){
          return   value;
        }

    }
}
