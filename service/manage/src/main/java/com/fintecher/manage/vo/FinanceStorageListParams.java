package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: FinanceStorageListParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 15:45
 */

@Data
@ApiModel("返回出入库记录列表实体")
public class FinanceStorageListParams {


   @ApiModelProperty("出入库id")
   private Integer id;

   @ApiModelProperty("订单id")
   private Long orderId;

   @ApiModelProperty("客户姓名")
   private String customerName;

   @ApiModelProperty("订单编号")
   private String orderNo;

   @ApiModelProperty("车牌号")
   private String carNo;

   @ApiModelProperty("入库时间")
   private Date stockInDate;


   @ApiModelProperty("出库时间")
   private Date stockOutDate;

   @ApiModelProperty("押品状态")
   private Integer mortgageStatus;


   @ApiModelProperty("押品方式")
   private Integer orderMrtgageType;


   @ApiModelProperty("评估状态")
   private Integer assessmentStatus;

}
