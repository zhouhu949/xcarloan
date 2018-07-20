package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Author: CaoPu
 * @Description:
 * @Date 10:21 2018/04/13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "车辆属性名称实体类")
@Table(name = "basic_car_attribute")
public class BasicCarAttribute extends BaseEntity{

    @ApiModelProperty(value = "属性类型", notes = "属性类型")
    private Integer attrType;

    @ApiModelProperty(value = "属性名称", notes = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "属性值", notes = "属性值")
    private String attrValue;


    public enum AttrType{
        OUTWARD(10204,"外观"),
        INWARD(10205,"内饰"),
        BASE(10206,"底座/机舱");
        private Integer value;
        private String name;
        AttrType(Integer value,String name){
            this.name = name;
            this.value = value;
        }
        public Integer getValue(){
            return value;
        }
        public String getName(){
            return name;
        }

    }


}
