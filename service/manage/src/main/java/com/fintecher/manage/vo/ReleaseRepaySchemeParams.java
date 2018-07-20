package com.fintecher.manage.vo;

import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("发布/取消发布还款方案")
public class ReleaseRepaySchemeParams {
    @ApiModelProperty("还款方案ID")
    @NotNull
    private Long id;
    @ApiModelProperty("状态：10057-发布，10056-取消发布")
    @NotNull
    @EnumValue(enumClass = BasicRepayScheme.SchemeStatus.class, enumMethod = "isValidValue", message = "状态错误")
    private Integer schemeStatus;
}
