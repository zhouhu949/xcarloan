package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: UpdateFinanceOutStorageParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/24 0024下午 22:46
 */
@Data
@ApiModel("修改出库需要参数")
public class UpdateFinanceOutStorageParams {

   @ApiModelProperty("出入库id")
   private Long id;

   @ApiModelProperty("出库时间")
   private Date stockOutDate;

   @ApiModelProperty("状态")
   private Integer mortgageStatus;

   @ApiModelProperty("操作人")
   private Long operator;

   @ApiModelProperty("操作时间")
   private Long operatorTime;


}
