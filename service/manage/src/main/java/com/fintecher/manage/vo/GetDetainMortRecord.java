package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: GetDetainMortRecord
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/26 0026下午 20:52
 */

@Data
@ApiModel("返回抵押押品详情记录")
public class GetDetainMortRecord {


   @ApiModelProperty("抵押号")
   private String mortgageNo;

   @ApiModelProperty("安装状态")
   private Integer gpsStatus;

   @ApiModelProperty("生产厂家")
   private Integer gpsManufactor;


   @ApiModelProperty("设备号")
   private String gpsNo;

   @ApiModelProperty("操作时间")
   private Date operatorTime;

   @ApiModelProperty("操作人")
   private String operator;

   @ApiModelProperty("操作姓名")
   private String name;

   @ApiModelProperty("入库日期")
   private Date stockInDate;

   @ApiModelProperty("入库日期")
   private Date stockOutDate;

}
