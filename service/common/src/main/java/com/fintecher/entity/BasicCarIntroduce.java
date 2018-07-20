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
 * @Title: BasicCarIntroduce
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/20 0020下午 15:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "车型介绍实体类")
@Entity
@Table(name = "basic_car_introduce")
public class BasicCarIntroduce extends BaseEntity {

   @ApiModelProperty(value = "车型id")
   @Column(name = "model_id")
   private Long modelId;

   @ApiModelProperty(value = "车型介绍名称")
   @Column(name = "introduce_name")
   private String introduceName;

   @ApiModelProperty(value = "车型介绍图片")
   @Column(name = "introduce_url")
   private String introduceUrl;


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
