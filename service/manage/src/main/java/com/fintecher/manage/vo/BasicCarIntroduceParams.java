package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicCarIntroduceParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/20 0020下午 15:53
 */
@Data
@ApiModel(value = "车型介绍")
public class BasicCarIntroduceParams {


   @ApiModelProperty(value = "车型id")
   private Long modelId;

   @ApiModelProperty(value = "车型介绍名称")
   private String introduceName;


   @ApiModelProperty(value = "车型介绍图片")
   private List<String> introduceUrl;


   @ApiModelProperty(value = "品牌描述")
   private String remark;


}
