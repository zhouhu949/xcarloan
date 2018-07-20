package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: BasicCarmodelParamValue
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/25 0025下午 15:15
 */

@Data
@ApiModel(value = "返回非基本参数")
public class BasicCarmodelParamValue {

   @ApiModelProperty(value = "参数id")
   private Long id;

   @ApiModelProperty(value = "参数名称")
   private String carParamName;

   @ApiModelProperty(value = "参数值")
   private String carParamValue;


}
