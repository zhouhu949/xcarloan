package com.fintecher.manage.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OrederId {
    @ApiModelProperty("供应商Id")
    private Long supplier;

}
