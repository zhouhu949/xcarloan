package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

@Data
@ApiModel(value = "配置参数")
public class BasicCarModelParamParams {

   @ApiModelProperty(value = "车型ID")
   private Long modelId;
   @ApiModelProperty(value = "参数名称")
   private String carParamName;

   @ApiModelProperty(value = "参数值")
   private String carParamValue;

   @ApiModelProperty(value = "备注")
   private String remark;

}
