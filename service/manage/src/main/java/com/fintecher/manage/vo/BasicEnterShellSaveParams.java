package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: BasicEnterShellSaveParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/22 0022下午 20:04
 */
@Data
@ApiModel(value = "进销存列表字段")
public class BasicEnterShellSaveParams {

   @ApiModelProperty(value = "订单id")
   private Long orderId;

   @ApiModelProperty(value = "订单编号")
   private String orderNo;

   @ApiModelProperty(value = "客户姓名")
   private String customerName;

   @ApiModelProperty(value = "车型名称")
   private String modelName;

   @ApiModelProperty(value = "车型颜色")
   private String modelColors;

   @ApiModelProperty(value = "订单车型id")
   private Long ordermodelId;

   @ApiModelProperty(value = "车型id")
   private Long modelId;

   @ApiModelProperty(value = "库存id")
   private Long stockId;

   @ApiModelProperty(value = "状态")
   private Integer stockStatus;

   @ApiModelProperty(value = "采购价格")
   private BigDecimal stockPrice;

}
