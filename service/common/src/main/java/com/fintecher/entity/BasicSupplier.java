package com.fintecher.entity;

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
 * @Title: BasicSupplier
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014上午 10:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "供应商实体类")
@Entity
@Table(name = "basic_supplier")
public class BasicSupplier  extends  BaseEntity {

   @ApiModelProperty(value = "机构id")
   @Column(name = "org_id")
   private Long orgId;

   @ApiModelProperty(value = "供应商名称")
   @Column(name = "supplier_name")
   private String supplierName;

   @ApiModelProperty(value = "供应商地址")
   @Column(name = "supplier_address")
   private String supplierAddress;

   @ApiModelProperty(value = "供应商地址")
   @Column(name = "supplier_phone")
   private String supplierPhone;

   @ApiModelProperty(value = "品牌描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;
}
