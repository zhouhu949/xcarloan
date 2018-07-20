package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("生成产品还款计划模板")
public class AddBasicProductParams {

    @ApiModelProperty("车型产品id")
    @NotNull(message = "车型产品id不能为空")
    private Long id;

    @ApiModelProperty("备注")
    private String remark;
}
