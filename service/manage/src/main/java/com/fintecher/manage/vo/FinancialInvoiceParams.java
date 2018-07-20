package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class FinancialInvoiceParams {
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("客户手机号")
    private String customerPhone;
    @ApiModelProperty(value = "是否已开票",required = true)
    @NotNull(message = "是否开票不能为空")
    private Integer isInvoice;
}
