package com.fintecher.manage.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("订单查询返回数据")
public class OrderInfoModel {
    @ApiModelProperty("客户ID")
    private Long customerId;
    @ApiModelProperty("客户姓名")
    private String customerName;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("客户手机号")
    private String customerPhone;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单类型")
    private Integer orderType;
    @ApiModelProperty("订单状态")
    private Integer orderStatus;
    @ApiModelProperty("还款方式")
    private Integer orderRepayType;
    @ApiModelProperty("期数")
    private Integer orderPeriods;
    @ApiModelProperty("订单金额")
    private BigDecimal orderPrice;
    @ApiModelProperty("还款方案ID")
    private Long schemeId;
    @ApiModelProperty("还款方案名称")
    private String schemeName;


}
