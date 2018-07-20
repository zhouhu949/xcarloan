package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("客户还款参数")
public class ClientRepaymentParams {
   @ApiModelProperty("订单Id")
   @NotNull(message = "订单Id不能为空")
   private Long orderId;
   @ApiModelProperty("期数")
   @NotNull
   private Integer periods;
   @ApiModelProperty("还款金额")
   @Min(value = 0, message = "还款金额不能小于0")
   private BigDecimal money;
}
