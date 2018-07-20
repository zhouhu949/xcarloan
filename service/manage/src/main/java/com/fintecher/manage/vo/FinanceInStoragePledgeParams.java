package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: financeInStorageParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 17:24
 */

@Data
@ApiModel("质押入库需要参数")
public class FinanceInStoragePledgeParams {

   @ApiModelProperty("押品出入库id")
   private Long id;

   @ApiModelProperty("出入库时间")
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date stockDate;

   @ApiModelProperty("质押地点")
   private String pledgePlace;

   @ApiModelProperty("质押位置")
   private String pledgePosition;


}
