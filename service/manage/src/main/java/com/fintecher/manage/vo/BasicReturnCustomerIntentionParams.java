package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerIntentionOrderParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/21 0021下午 15:13
 */

@Data
@ApiModel(value = "返回意向结果")
public class BasicReturnCustomerIntentionParams {

   @ApiModelProperty(value = "客户id")
   private Long id;
   @ApiModelProperty(value = "意向类型")
   private String customerName;

   @ApiModelProperty(value = "身份证")
   private Double idCard;

}
