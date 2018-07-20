package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: FinanceStorageSearch
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/26 0026下午 18:27
 */

@Data
@ApiModel("返回押品详情条件")
public class GetDetainDetails {


   @ApiModelProperty("客户姓名")
   private String customerName;

   @ApiModelProperty("车牌号")
   private String carNo;

   @ApiModelProperty("是否二手车")
   private Integer isSecondHand;

   @ApiModelProperty("购买方式")
   private Integer buyType;

   @ApiModelProperty("初次登记时间")
   private Date registerTime;

   @ApiModelProperty("抵押方式")
   private Integer orderMortgageType;

   @ApiModelProperty("客户电话")
   private String customerPhone;


}
