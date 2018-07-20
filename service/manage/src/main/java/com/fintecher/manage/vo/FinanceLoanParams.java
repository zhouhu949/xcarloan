package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("供应商开票查询参数")
public class FinanceLoanParams {
    private Long orderId;
    @ApiModelProperty("编号")
    private String orderNo;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("客户手机")
    private Long customerPhone;
    @ApiModelProperty("身份证号")
    private Long customerCode;


}
