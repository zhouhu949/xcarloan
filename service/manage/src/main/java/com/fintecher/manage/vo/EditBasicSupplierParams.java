package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: EditBasicSupplierParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 14:53
 */

@Data
@ApiModel(value = "修改供应商")
public class EditBasicSupplierParams {

   @ApiModelProperty(value = "供应商ID")
   private Long id;

   @ApiModelProperty(value = "供应商名称")
   private String supplierName;


   @ApiModelProperty(value = "供应商地址")
   private String  supplierAddress;

   @ApiModelProperty(value = "供应商电话")
   private String  supplierPhone;

   @ApiModelProperty(value = "备注")
   private String  remark;

}
