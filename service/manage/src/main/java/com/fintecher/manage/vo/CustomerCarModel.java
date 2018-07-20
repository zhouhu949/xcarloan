package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("客户车产信息")
public class CustomerCarModel {

    @ApiModelProperty("客户车产ID")
    private Long id;
    @ApiModelProperty("客户ID")
    private Long customerId;
    @ApiModelProperty("车牌号")
    private String carNo;
    @ApiModelProperty("购车价格")
    private BigDecimal carPrice;
}
