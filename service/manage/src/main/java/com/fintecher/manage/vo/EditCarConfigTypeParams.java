package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: EditCarConfigTypeParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 13:47
 */

@Data
@ApiModel(value = "修改配置类型")
public class EditCarConfigTypeParams {


   @ApiModelProperty(value = "配置类型ID")
   private Long id;

   @ApiModelProperty(value = "配置名称")
   private String  carTypeName;

   @ApiModelProperty(value = "配置code")
   private String  carTypeCode;

   @ApiModelProperty(value = "配置价格")
   private BigDecimal referencePrice;

   @ApiModelProperty(value = "备注")
   private String  remark;
}
