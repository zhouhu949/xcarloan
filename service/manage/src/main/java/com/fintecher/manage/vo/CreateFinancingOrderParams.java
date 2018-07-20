package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("创建融资租赁订单请求参数实体")
public class CreateFinancingOrderParams {
    @ApiModelProperty("车型ID：类型是融资租赁时必须")
    @NotNull(message = "车型ID不能为空")
    private Long modelId;
    //    @ApiModelProperty("还款方案ID")
//    @NotNull(message = "还款方案必须")
//    private Long schemeId;
    @ApiModelProperty("产品ID")
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    @ApiModelProperty("客户ID")
    @NotNull(message = "客户ID必须")
    private Long customerId;

}
