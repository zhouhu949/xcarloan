package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
@Data
@ApiModel("车系参数")
public class BasicCarSeriesParams {

   @ApiModelProperty("品牌id")
   private Long brandId;

   @ApiModelProperty("车系名称")
   private String seriesName;


   @ApiModelProperty("备注")
   private String remark;

}
