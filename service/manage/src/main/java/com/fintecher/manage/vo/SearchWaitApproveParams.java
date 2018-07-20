package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("多条件查询待审核订单请求参数")
public class SearchWaitApproveParams {
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("客户手机")
    private String customerPhone;
    @ApiModelProperty("订单编号")
    private String orderNo;
}
