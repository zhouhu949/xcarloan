package com.fintecher.manage.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("冲抵项Model")
public class BasicOffsetItemModel {

    @ApiModelProperty(value = "冲抵项id")
    private String id;

    @ApiModelProperty(value = "冲抵项名称")
    private String expenseName;

    @ApiModelProperty(value = "备注")

    private String remark;


}
