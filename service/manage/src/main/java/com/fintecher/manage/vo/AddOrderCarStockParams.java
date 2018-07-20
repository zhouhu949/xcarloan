package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: AddOrderCarStockParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/25 0025下午 16:35
 */

@Data
@ApiModel(value = "外采时添加库存参数")
public class AddOrderCarStockParams {

   @ApiModelProperty(value = "库存id")
   private Long stockId;

   @ApiModelProperty(value = "供应商id")
   private Long supplierId;

   @ApiModelProperty(value = "车架号")
   private String stockCarNo;

   @ApiModelProperty(value = "发动机号")
   private String stockEngineNo;

   @ApiModelProperty(value = "车辆颜色")
   private String stockCarColor;

   @ApiModelProperty(value = "采购价格")
   private BigDecimal stockPrice;

   @ApiModelProperty(value = "备注")
   private String remark;
}
