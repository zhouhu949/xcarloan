package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("新增费用项参数")
public class AddBasicExpenseParams {

    @ApiModelProperty(value = "费用项名称", notes = "费用项名称")
    @NotBlank(message = "费用项名称不能为空")
    private String expenseName;

    @ApiModelProperty(value = "费用项编码", notes = "费用项编码")
    @NotNull(message = "费用项编码不能为空")
    private String expenseCode;

    @ApiModelProperty(value = "备注", notes = "备注")
    private String remark;

}
