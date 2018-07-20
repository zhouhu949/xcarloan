package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: CarBrandParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 20:26
 */
@Data
@ApiModel(value = "车辆品牌添加")
public class BasicCarBrandParams {


   @ApiModelProperty("品牌名称")
   private String brandName;

   @ApiModelProperty(value = "车型介绍图片")
   private String brandPhotoUrl;


   @ApiModelProperty("备注")
   private String remark;

}
