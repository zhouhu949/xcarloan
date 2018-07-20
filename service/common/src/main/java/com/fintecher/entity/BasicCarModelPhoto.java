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
 * @Title: BasicCarModelPhoto
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/21 0021下午 20:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("车辆图片信息")
@Table(name = "basic_carmodel_photo")
public class BasicCarModelPhoto extends BaseEntity {

   @ApiModelProperty(value = "车型id")
   @Column(name = "model_id")
   private Long modelId;

   @ApiModelProperty(value = "车辆图片")
   @Column(name = "photo_url")
   private String photoUrl;


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
