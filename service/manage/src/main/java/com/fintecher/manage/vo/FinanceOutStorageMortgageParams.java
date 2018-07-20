package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: FinanceOutStorageMortgageParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/24 0024下午 22:04
 */


@Data
@ApiModel("抵押出库需要参数")
public class FinanceOutStorageMortgageParams {

   @ApiModelProperty("押品出入库id")
   private Long id;

   @ApiModelProperty("订单id")
   private Long orderId;

   @ApiModelProperty("出库时间")
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date stockOutDate;


}
