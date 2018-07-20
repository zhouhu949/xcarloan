package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: BasicSupplierParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014上午 11:07
 */

@Data
@ApiModel(value = "车损")
public class BasicSupplierParams {

   @ApiModelProperty(value = "供应商名称")
   private String supplierName;

   @ApiModelProperty(value = "供应商地址")
   private String  supplierAddress;

   @ApiModelProperty(value = "供应商地址")
   private String  supplierPhone;

   @ApiModelProperty(value = "备注")
   private String  remark;

}
