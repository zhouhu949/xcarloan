package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: EditBasicStockCarParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015下午 15:56
 */
@Data
@ApiModel(value = "修改库存状态")
public class EditBasicStockCarParams {


   @ApiModelProperty(value = "库存ID")
   private Long id;

   @ApiModelProperty(value = "状态ID 100046:整备中  100047:整备完成 100048:已提车,10123 待采购")
   private Integer stockStatus;


}
