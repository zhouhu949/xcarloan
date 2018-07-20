package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: EditStockSupplierLoanParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015下午 16:33
 */
@Data
@ApiModel(value = "修改供应商是否放款")
public class EditStockSupplierLoanParams {


   @ApiModelProperty(value = "库存ID")
   private Long id;

   @ApiModelProperty(value = "供应商是否放款 10002 :是 10003 :否")
   private Integer hasSupplierLoan;
}
