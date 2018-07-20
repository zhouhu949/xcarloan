package com.fintecher.manage.vo;

/**
 * @author ZhangYaJun
 * @Title: BasicSupplierSearch
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015上午 11:49
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "供应商搜索条件")
public class BasicSupplierSearch {


   @ApiModelProperty(value = "供应商名称")
   private String supplierName;
}
