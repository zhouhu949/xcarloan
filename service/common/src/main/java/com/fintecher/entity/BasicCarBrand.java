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
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @System: 车贷金融
 * @Auther: zhangyajun
 * @Description: 车辆品牌实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "车辆品牌实体类")
@Entity
@Table(name = "basic_car_brand")
public class BasicCarBrand extends  BaseEntity{




   @ApiModelProperty(value = "组织id")
   @Column(name = "org_id")
   private Long orgId;

   @ApiModelProperty(value = "品牌名称")
   @Column(name = "brand_name")
   private String brandName;




   @ApiModelProperty(value = "品牌code")
   @Column(name = "brand_code")
   private String brandCode;

   @ApiModelProperty(value = "部门id")
   @Column(name = "dept_id")
   private Long deptId;

   @ApiModelProperty(value = "品牌描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "品牌图标")
   @Column(name = "brand_photo_url")
   private String brandPhotoUrl;

   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "车系集合")
   @Transient
   private List<BasicCarBrandSeries> carBrandSeries;


   @ApiModelProperty(value = "类型")
   @Transient
   private Integer type;



}
