package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: EditBasicCarIntroduceParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/20 0020下午 15:55
 */

@Data
@ApiModel(value = "修改车型介绍")
public class EditBasicCarIntroduceParams {

   @ApiModelProperty(value = "车型介绍id")
   private Long id;

   @ApiModelProperty(value = "车型介绍名称")
   private String introduceName;

   @ApiModelProperty(value = "车型介绍图片")
   private List<String> introduceUrl;


   @ApiModelProperty(value = "品牌描述")
   private String remark;

}
