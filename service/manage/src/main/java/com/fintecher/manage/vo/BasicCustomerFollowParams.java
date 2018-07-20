package com.fintecher.manage.vo;

import com.fintecher.entity.BasicCustomerFollow;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerFollowParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 18:05
 */

@Data
@ApiModel(value = "跟进记录信息")
public class BasicCustomerFollowParams {

   @ApiModelProperty(value = "意向id")
   private Long intentionId;

   @ApiModelProperty(value = "跟进类型")
   @NotNull(message = "跟进类型不能为空")
   @EnumValue(enumClass = BasicCustomerFollow.FollowType.class, enumMethod = "isValidValue", message = "跟进类型参数错误")
   private Integer followType;
   @ApiModelProperty(value = "跟进结果")
   @NotNull(message = "跟进结果不能为空")
   @EnumValue(enumClass = BasicCustomerFollow.FollowResult.class, enumMethod = "isValidValue", message = "跟进结果参数错误")
   private Integer followResult;
   @ApiModelProperty(value = "备注 ")
   private String remark;

}
