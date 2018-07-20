package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: FinanceInStorageMortgageParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/24 0024下午 21:30
 */

@Data
@ApiModel("抵押入库需要参数")
public class FinanceInStorageMortgageParams {

   @ApiModelProperty("押品出入库id")
   private Long id;

   @ApiModelProperty("入库时间")
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date stockDate;


   @ApiModelProperty("设备号")
   private String gpsNo;

   @ApiModelProperty("设备厂家")
   private Integer gpsManufactor;


}
