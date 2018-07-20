package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("编辑冲抵策略参数")
public class EditBasicOffsetParams {
    @ApiModelProperty(value = "冲抵Id", notes = "冲抵id")
    @NotNull(message ="冲抵id不能为空")
    private Long id;

    @ApiModelProperty(value = "冲抵策略名称", notes = "冲抵策略名称")
    @NotBlank(message = "冲抵策略名称不能为空")
    private String offsetName;

    @ApiModelProperty(value = "备注", notes = "备注")
    private String remark ;
}
