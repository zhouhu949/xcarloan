package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@ApiModel("新增冲抵策略参数")
public class AddBasicOffsetParams {

    @ApiModelProperty("冲抵策略名称")
    @NotBlank(message = "冲抵策略名称不能为空")
    private String offsetName;

    @ApiModelProperty("备注")
    private String remark ;

}
