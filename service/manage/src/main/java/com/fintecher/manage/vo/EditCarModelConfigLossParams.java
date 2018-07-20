package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: EditCarModelConfigLossParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 14:27
 */
@Data
@ApiModel(value = "修改车损")
public class EditCarModelConfigLossParams {


   @ApiModelProperty(value = "车损ID")
   private Long id;

   @ApiModelProperty(value = "车损级别")
   private String  lossLevel;

   @ApiModelProperty(value = "参考价格")
   private BigDecimal lossPrice;

   @ApiModelProperty(value = "备注")
   private String  remark;
}
