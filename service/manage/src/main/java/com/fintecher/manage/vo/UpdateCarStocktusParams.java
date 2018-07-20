package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * @author ZhangYaJun
 * @Title: UpdateCarStocktusParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/7/6 0006下午 15:15
 */

@Data
@ApiModel("修改库存状态")
public class UpdateCarStocktusParams {

   @ApiModelProperty("库存id")
   private Long id;

   @ApiModelProperty("订单id")
   private Long orderId;

}
