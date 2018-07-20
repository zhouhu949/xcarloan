package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class CarModelInfo {
    @ApiModelProperty("品牌ID")
    private Long brandId;
    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("车系ID")
    private Long seriesId;
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

    @ApiModelProperty("车型参数")
    List<Map> carmodelParams;

    @ApiModelProperty("车型介绍")
    List<Map> carIntroduceList;
}
