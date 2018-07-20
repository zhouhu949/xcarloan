package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerOrderParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/21 0021上午 10:48
 */

@Data
@ApiModel(value = "客户申请信息第一步填写资料")
public class BasicCustomerOrderParams {


   @ApiModelProperty(value = "客户姓名")
   @NotBlank
   private String customerName;

   @ApiModelProperty(value = "证件号码  ")
   @NotBlank
   private String certificateNumber;

   @ApiModelProperty(value = "手机号码")
   @NotBlank
   private String customerPhone;









}
