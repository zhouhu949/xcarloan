package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: QueryMortgageTypeParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 16:52
 */

@Data
@ApiModel("入库时返回对象")
public class QueryMortgageTypeParams {

   @ApiModelProperty("订单id")
   private Long orderId;

   @ApiModelProperty("车产id")
   private Long carId;

   @ApiModelProperty("记录id")
   private Long mortgageRecordId;


   @ApiModelProperty("组织id")
   private Long orgId;


   @ApiModelProperty("押品状态")
   private Integer mortgageStatus;

   @ApiModelProperty("抵押方式")
   private Integer orderMortgageType;

   @ApiModelProperty(value = "操作人")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   private Date operatorTime;

   @ApiModelProperty(value = "入库时间")
   private Date stockInDate;


}
