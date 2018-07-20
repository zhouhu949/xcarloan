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
 * @Description: 车辆车系实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "车辆车系实体类")
@Entity
@Table(name = "basic_car_brand_series")
public class BasicCarBrandSeries extends  BaseEntity {



   @ApiModelProperty(value = "组织id")
   @Column(name = "brand_id")
   private Long brandId;


   @ApiModelProperty(value = "系列名称", notes = "系列名称")
   @Column(name = "series_name")
   private String seriesName;


   @ApiModelProperty(value = "系列编码", notes = "系列编码")
   @Column(name = "series_code")
   private String seriesCode;

   @ApiModelProperty(value = "系列描述", notes = "系列描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人",notes = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "车型集合")
   @Transient
   private List<BasicCarModel> carModel;


   @ApiModelProperty(value = "类型")
   @Transient
   private Integer type;
}
