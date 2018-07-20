package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Data
@ApiModel("编辑费用项参数")
public class EditBasicExpenseParams {

    @ApiModelProperty(value = "费用项ID", notes = "费用项ID")
    @NotNull(message = "费用项ID不能为空")
    private Long Id;

    @ApiModelProperty(value = "费用项名称", notes = "费用项名称")
    private String expenseName;

    @ApiModelProperty(value = "备注", notes = "备注")
    private String remark;

}
