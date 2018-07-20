package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SearchCarParams {
    @ApiModelProperty("品牌名称")
    private String brandName;
    @ApiModelProperty("系列名称")
    private String seriesName;
    @ApiModelProperty("车型名称")
    private String modelName;
}
