package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: EditBasicCustomerFollowParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/20 0020下午 19:38
 */

@Data
@ApiModel(value = "修改跟进记录信息")
public class EditBasicCustomerFollowParams {

   @ApiModelProperty(value = "记录id")
   private Long id;


   @ApiModelProperty(value = "跟进方式 ", notes = "10073:电话,10074:邮件")
   private Integer followType;

   @ApiModelProperty(value = "跟进结果 ", notes = "10070:继续跟进,10071:不在跟进,10072:已预约成功")
   private Integer followResult;

   @ApiModelProperty(value = "备注 ")
   private String remark;
}
