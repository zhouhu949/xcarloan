package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicCarModelPhotoParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/21 0021下午 21:27
 */
@Data
@ApiModel(value = "车辆图片")
public class BasicCarModelPhotoParams {


   @ApiModelProperty(value = "车型id")
   private Long modelId;

   @ApiModelProperty(value = "车辆图片路径")
   private List<String> photoUrl;

   @ApiModelProperty(value = "备注")
   private String remark;
}
