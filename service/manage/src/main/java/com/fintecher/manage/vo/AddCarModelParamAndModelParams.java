package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: AddCarModelParamAndModelParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/22 0022下午 13:55
 */

@Data
@ApiModel(value = "配置参数")
public class AddCarModelParamAndModelParams {


   @ApiModelProperty(value = "车型名称")
   private String brandName;

   @ApiModelProperty(value = "车型名称")
   private String seriesName;

   @ApiModelProperty(value = "车型名称")
   private String modelName;


   @ApiModelProperty(value = "车型颜色")
   private String modelColors;


   @ApiModelProperty(value = "长x宽x高", notes = "长x宽x高")
   private String modelVolume;

   @ApiModelProperty(value = "车身结构", notes = "车身结构")
   private String structure;

   @ApiModelProperty(value = "驱动方式", notes = "驱动方式")
   private String diveway;

   @ApiModelProperty(value = "燃料形式", notes = "燃料形式")
   private String fulyway;

   @ApiModelProperty(value = "综合耗油", notes = "综合耗油")
   private String modelFuel;

   @ApiModelProperty(value = "排量", notes = "排量")
   private String displacement;

   @ApiModelProperty(value = "内饰颜色", notes = "内饰颜色")
   private String innerColor;

   @ApiModelProperty(value = "参考价格")
   private BigDecimal referencePrice;

   @ApiModelProperty(value = "备注")
   private String remark;
}
