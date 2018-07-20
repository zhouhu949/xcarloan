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

/**
 * @Author: CaoPu
 * @Description:
 * @Date 10:18 2018/04/13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel(description = "车辆属性值实体类")
@Table(name = "basic_car_attrvalue")
public class BasicCarAttrvalue extends BaseEntity{

    @ApiModelProperty(value = "评估基本信息关联id", notes = "评估基本信息关联id")
    @Column(name = "basic_id")
    private Long basicId;

    @ApiModelProperty(value = "属性id", notes = "属性id")
    @Column(name = "attr_name")
    private Integer attrName;

    @ApiModelProperty(value = "属性值", notes = "属性值")
    @Column(name = "attr_value")
    private String attrValue;


}
