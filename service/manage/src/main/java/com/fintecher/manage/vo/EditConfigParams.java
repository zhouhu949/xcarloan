package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: EditConfigParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 13:29
 */

@Data
@ApiModel(value = "修改车型配置")
public class EditConfigParams {
   @ApiModelProperty(value = "配置ID")
   private Long id;
   @ApiModelProperty(value = "配置名称")
   private String configName;
   @ApiModelProperty(value = "配置价格")
   private BigDecimal referencePrice;
   @ApiModelProperty(value = "库存")
   private Integer stockNum;
   @ApiModelProperty("备注")
   private String remark;


}
