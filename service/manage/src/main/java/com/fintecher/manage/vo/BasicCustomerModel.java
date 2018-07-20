package com.fintecher.manage.vo;

import com.fintecher.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomer
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 13:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("客户信息")
public class BasicCustomerModel extends BaseEntity {

   @ApiModelProperty(value = "客户id")
   private Long customerId;

   @ApiModelProperty(value = "组织机构id")
   private Long orgId;

   @ApiModelProperty(value = "客户姓名")
   private String customerName;

   @ApiModelProperty(value = "客户状态")
   private Integer customerStatus;

   @ApiModelProperty(value = "客户性别")
   private Integer customerSex;


   @ApiModelProperty(value = "身份证")
   private String idCard;
   @ApiModelProperty(value = "手机号")
   private String customerPhone;


   @ApiModelProperty(value = "操作人")
   private Long operator;

   @ApiModelProperty(value = "操作人姓名")
   private String operatorName;

   @ApiModelProperty(value = "开户状态")
   private Integer accountStatus;



}
