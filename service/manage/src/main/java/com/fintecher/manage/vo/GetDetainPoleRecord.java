package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: GetDetainPoleRecord
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/26 0026下午 20:41
 */

@Data
@ApiModel("返回质押押品详情记录")
public class GetDetainPoleRecord {



   @ApiModelProperty("质押地点")
   private String pledgePlace;

   @ApiModelProperty("质押位置")
   private String pledgePosition;

   @ApiModelProperty("质押编号")
   private String pledgeNo;

   @ApiModelProperty("操作时间")
   private Date operatorTime;

   @ApiModelProperty("操作人")
   private String operator;

   @ApiModelProperty("入库日期")
   private Date stockInDate;

   @ApiModelProperty("入库日期")
   private Date stockOutDate;
}
