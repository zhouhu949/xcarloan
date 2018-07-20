package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author wxc
 * @Data 2018/1/25 17:08
 * @Description
 */

@Data
@ApiModel(value = "车辆查询参数")
public class BasicCarParams {


   @ApiModelProperty(value = "品牌ID")
   private Long brandId;

   @ApiModelProperty(value = "组织ID")
   private Long orgId;

   @ApiModelProperty(value = "部门ID")
   private Long deptId;

   @ApiModelProperty(value = "车型ID")
   private Long modelId;

   @ApiModelProperty(value = "系列ID")
   private Long seriesId;

   @ApiModelProperty(value = "配置ID")
   private Long configId;

   @ApiModelProperty(value = "品牌名称")
   private String brandName;


}
