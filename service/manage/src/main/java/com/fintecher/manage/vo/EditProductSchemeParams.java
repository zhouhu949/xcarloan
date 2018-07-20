package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "还款方案配置")
public class EditProductSchemeParams {
    @ApiModelProperty("车型产品ID主键")
    @NotNull(message="主键不能为空")
    Long id;

    @ApiModelProperty("方案ID")
    private Long schemeId;
}
