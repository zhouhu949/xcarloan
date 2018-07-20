package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: EditBasicCarModelPhotoParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/21 0021下午 21:25
 */

@Data
@ApiModel(value = "修改车辆图片")
public class EditBasicCarModelPhotoParams {


   @ApiModelProperty(value = "车辆图片id")
   private Long id;


   @ApiModelProperty(value = "车辆图片路径")
   private List<String> photoUrl;

   @ApiModelProperty(value = "备注")
   private String remark;
}
