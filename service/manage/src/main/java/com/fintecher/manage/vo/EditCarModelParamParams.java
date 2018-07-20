package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: EditCarModelParamParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 13:42
 */

@Data
@ApiModel(value = "修改配置参数")
public class EditCarModelParamParams {

   @ApiModelProperty(value = "参数ID")
   private Long id;

   @ApiModelProperty(value = "参数名称")
   private String  carParamName;

   @ApiModelProperty(value = "参数值")
   private String  carParamValue;


   @ApiModelProperty(value = "参考价格")
   private BigDecimal referencePrice;

   @ApiModelProperty(value = "备注")
   private String  remark;
}
