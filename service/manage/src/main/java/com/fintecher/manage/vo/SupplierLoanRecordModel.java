package com.fintecher.manage.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("供应商开票查询返回参数")
public class SupplierLoanRecordModel {
    @ApiModelProperty(value = "放款记录Id")
    private Long id;
    @ApiModelProperty(value = "供应商id")
    private Long supplier_id;
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(value = "放款类型")
    private Integer loanType;
    @ApiModelProperty(value = "放款金额")
    private BigDecimal loanMoney;
    @ApiModelProperty(value = "放款日期")
    private Date loanDate;
    @ApiModelProperty(value = "是否收到发票")
    private Integer hasInvoice;
    @ApiModelProperty(value = "是否收到收据")
    private Integer hasReceipt;
    @ApiModelProperty(value = "路径")
    private String fileUrl;
    @ApiModelProperty(value = "操作人")
    private Long operator;
    @ApiModelProperty(value = "操作日期")
    private Date operatorTime;

}
