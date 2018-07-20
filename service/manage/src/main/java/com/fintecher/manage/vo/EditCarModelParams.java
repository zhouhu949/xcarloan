package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: EditCarModelParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 13:49
 */
@Data
@ApiModel(value = "修改车型")
public class EditCarModelParams {

   @ApiModelProperty(value = "车型ID")
   private Long id;

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

   @ApiModelProperty("参考价格")
   private BigDecimal referencePrice;

   @ApiModelProperty("备注")
   private String remark;

}
