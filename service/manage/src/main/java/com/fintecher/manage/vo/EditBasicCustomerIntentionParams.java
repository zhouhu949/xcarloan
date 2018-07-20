package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangYaJun
 * @Title: EditBasicCustomerIntentionParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 20:29
 */

@Data
@ApiModel(value = "修改意向记录信息")
public class EditBasicCustomerIntentionParams {

   @ApiModelProperty(value = "意向id")
   private Long id;


   @ApiModelProperty(value = "意向类型")
   private Integer intentionType;

   @ApiModelProperty(value = "意向状态")
   private BigDecimal intentionStatus;

   @ApiModelProperty(value = "意向等级")
   private BigDecimal intentionLevel;


   @ApiModelProperty(value = "车型描述", notes = "车型描述")
   private String remark;

}
