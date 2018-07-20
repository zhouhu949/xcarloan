package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerSearch
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/19 0019下午 21:25
 */
@Data
@ApiModel(value = "客户搜索")
public class BasicCustomerSearch {

   @ApiModelProperty(value = "客户姓名")
   private String customerName;
   @ApiModelProperty(value = "手机号")
   private String customerPhone;
   @ApiModelProperty(value = "身份证号")
   private String idCard;
}
