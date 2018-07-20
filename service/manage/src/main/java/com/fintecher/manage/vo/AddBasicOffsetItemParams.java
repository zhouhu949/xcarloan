package com.fintecher.manage.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("新增冲抵项参数")
public class AddBasicOffsetItemParams {


    @ApiModelProperty("冲抵Id")
    @NotNull(message = "冲抵Id不能为空")
    private Long offsetId;

    @ApiModelProperty("费用项ID")
    @NotNull(message = "费用项ID不能为空")
    private Long expenseId;

}
