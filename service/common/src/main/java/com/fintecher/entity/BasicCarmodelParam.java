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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: zhangyajun
 * @Description: 参数实体类
 * @Date:Created
 * @Modified By:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "参数类")
@Entity
@Table(name = "basic_carmodel_param")
public class BasicCarmodelParam extends   BaseEntity {

   @ApiModelProperty(value = "车型id")
   @Column(name = "model_id")
   private Long modelId;


   @ApiModelProperty(value = "参数名称")
   @Column(name = "car_param_name")
   private String carParamName;

   @ApiModelProperty(value = "参数值")
   @Column(name = "car_param_value")
   private String carParamValue;


   @ApiModelProperty(value = "长x宽x高", notes = "长x宽x高")
   @Column(name = "model_volume")
   private String modelVolume;

   @ApiModelProperty(value = "车身结构", notes = "车身结构")
   @Column(name = "structure")
   private String structure;

   @ApiModelProperty(value = "驱动方式", notes = "驱动方式")
   @Column(name = "diveway")
   private String diveway;

   @ApiModelProperty(value = "燃料形式", notes = "燃料形式")
   @Column(name = "fulyway")
   private String fulyway;

   @ApiModelProperty(value = "综合耗油", notes = "综合耗油")
   @Column(name = "model_fuel")
   private String modelFuel;

   @ApiModelProperty(value = "排量", notes = "排量")
   @Column(name = "displacement")
   private String displacement;

   @ApiModelProperty(value = "内饰颜色", notes = "内饰颜色")
   @Column(name = "inner_color")
   private String innerColor;

   @ApiModelProperty(value = "是否基本参数", notes = "是否基本参数")
   @Column(name = "is_sys_param")
   private Integer isSysParam;

   @ApiModelProperty(value = "品牌描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;


   public enum IsSysParam {
      YES("是", 10002),
      NO("否", 10003);


      private String name;
      private Integer value;

      IsSysParam(String name, Integer value) {
         this.value = value;
         this.name = name;
      }

      public Integer getValue() {
         return value;
      }

      public String getName(String name) {
         return name;
      }
   }



}
