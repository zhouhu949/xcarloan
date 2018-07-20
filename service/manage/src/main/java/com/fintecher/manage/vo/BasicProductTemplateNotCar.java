package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("传入方案id和金额生成还款计划模板Model")
public class BasicProductTemplateNotCar {
    @ApiParam("方案id")
    @NotNull(message = "方案id不能为空")
    Long schemeId;

    @ApiParam("贷款金额")
    @NotNull(message = "贷款金额不能为空")
    BigDecimal money;
}
