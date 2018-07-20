package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarSearch
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015上午 9:57
 */
@Data
@ApiModel(value = "库存搜索条件")
public class BasicStockCarSearch {

   @ApiModelProperty(value = "车型id")
   private Long modelId;

   @ApiModelProperty(value = "供应商id")
   private Long supplierId;


}
