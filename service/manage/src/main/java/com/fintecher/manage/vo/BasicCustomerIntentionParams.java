package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerIntentionParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 17:01
 */
@Data
@ApiModel(value = "意向客户信息")
public class BasicCustomerIntentionParams {

   @ApiModelProperty(value = "客户id")
   @NotNull
   private Long customerId;
   @ApiModelProperty(value = "意向类型")
   @NotNull
   private Integer intentionType;
   @ApiModelProperty(value = "意向状态")
   @NotNull
   private Integer intentionStatus;
   @ApiModelProperty(value = "意向等级")
   private Double intentionLevel;
   @ApiModelProperty("备注")
   private String remark;


}
