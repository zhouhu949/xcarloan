package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("车辆信息")
public class CarInfoModel {
    @ApiModelProperty("品牌名称")
    private String brandName;
    @ApiModelProperty("车系名称")
    private String seriesName;
    @ApiModelProperty("车型ID")
    private Long modelId;
    @ApiModelProperty("车型名称")
    private String modelName;
    @ApiModelProperty("车型颜色")
    private String modelColors;
    @ApiModelProperty("参考价格")
    private BigDecimal referencePrice;
}
