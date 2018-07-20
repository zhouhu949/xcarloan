package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.function.Supplier;

/**
 * @Author: CaoPu
 * @Description:
 * @Date 13:47 2018/04/26
 */
@Data
public class CarAttributeModel{

    @ApiModelProperty(value = "属性值ID", notes = "属性值ID")
    private Long id;

    @ApiModelProperty(value = "评估基本信息Id", notes = "评估基本信息Id")
    private Long basicId;

    @ApiModelProperty(value = "属性类型", notes = "属性类型")
    private Integer attrType;

    @ApiModelProperty(value = "属性编码", notes = "属性编码")
    private Integer attrCode;

    @ApiModelProperty(value = "属性名称", notes = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "属性值", notes = "属性值")
    private String attrValue;

}
