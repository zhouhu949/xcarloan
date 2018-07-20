package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单查询")
public class SearchOrderParams {
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("客户手机号")
    private String customerPhone;
}
