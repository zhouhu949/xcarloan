package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015下午 14:08
 */

@Data
@ApiModel(value = "库存")
public class BasicStockCarParams {

   @ApiModelProperty(value = "供应商id")
   private Long supplierId;

   @ApiModelProperty(value = "车型id")
   private Long modelId;

   @ApiModelProperty(value = "车架id")
   private String stockCarNo;


   @ApiModelProperty(value = "发动机编号")
   private String stockEngineNo;


   @ApiModelProperty(value = "车辆颜色")
   private String stockCarColor;

   @ApiModelProperty(value = "采购价格")
   private BigDecimal stockPrice;

   @ApiModelProperty(value = "备注")
   private String remark;




}
