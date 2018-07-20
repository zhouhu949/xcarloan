package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;


@Data
@ApiModel("修改车辆品牌")
public class EditCarParams {

   @ApiModelProperty("品牌id")
   private Long id;


   @ApiModelProperty("品牌名称")
   private String brandName;

   @ApiModelProperty(value = "品牌图标")
   private String brandPhotoUrl;
   @ApiModelProperty("备注")
   private String remark;



}
