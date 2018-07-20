package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: getOrderCarDetil
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/25 0025下午 20:46
 */

@Data
@ApiModel(value = "车辆详情返回实体")
public class BasicGetOrderCarDetil {


   @ApiModelProperty(value = "客户姓名")
   private String customerName;

   @ApiModelProperty(value = "车型名称")
   private String modelName;

   @ApiModelProperty(value = "车型颜色")
   private String modelColors;


   @ApiModelProperty(value = "采购价格")
   private String stockPrice;



   @ApiModelProperty(value = "车型描述")
   private String orderCarDesc;

   @ApiModelProperty(value = "车参数描述")
   private String orderCarParamDesc;

   @ApiModelProperty(value = "发动机编号")
   private String stockEngineNo;


   @ApiModelProperty(value = "车架号")
   private String stockCarNo;


   @ApiModelProperty(value = "供应商")
   private String supplierName;


}
