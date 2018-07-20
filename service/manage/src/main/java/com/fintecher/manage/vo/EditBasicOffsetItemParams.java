package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@ApiModel("冲抵项")
public class EditBasicOffsetItemParams {

    @ApiModelProperty("冲抵Id")
    @NotBlank(message = "冲抵Id不能为空")
    private Long id;

    @ApiModelProperty("冲抵策略名称")
    private String offsetName;

    @ApiModelProperty("冲抵类型")
    private Integer offsetType;

    @ApiModelProperty("备注")
    private String remark ;
}
