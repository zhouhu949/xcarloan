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
import java.math.BigDecimal;
import java.util.Date;

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
@ApiModel(description = "车辆车型实体类")
@Entity
@Table(name = "basic_car_model")
public class BasicCarModel extends  BaseEntity{



   @ApiModelProperty(value = "车系id")
   @Column(name = "series_id")
   private Long seriesId;

   @ApiModelProperty(value = "车型名称", notes = "车型名称")
   @Column(name = "model_name")
   private String modelName;


   @ApiModelProperty(value = "车型颜色")
   @Column(name = "model_colors")
   private String modelColors;

   @ApiModelProperty(value = "车型描述", notes = "车型描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人",notes = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "参考价格")
   @Column(name = "reference_price")
   private BigDecimal referencePrice;


   @ApiModelProperty(value = "类型")
   @Transient
   private Integer type;
}
