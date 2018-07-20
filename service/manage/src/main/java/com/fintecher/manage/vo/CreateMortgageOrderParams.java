package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@ApiModel("创建抵押贷款订单请求参数实体")
public class CreateMortgageOrderParams {
    @ApiModelProperty("还款方案ID")
    @NotNull(message = "还款方案ID必须")
    private Long schemeId;
    @ApiModelProperty("贷款金额：类型时抵押贷款时必须")
    @NotNull(message = "贷款金额不能为空")
    @Min(value = 0, message = "贷款金额不能小于0")
    private BigDecimal amount;
    @ApiModelProperty("客户车产ID集合：类型是抵押贷款时必须")
    private Set<Long> carIds;
    @ApiModelProperty("客户ID")
    @NotNull(message = "客户ID必须")
    private Long customerId;

}
