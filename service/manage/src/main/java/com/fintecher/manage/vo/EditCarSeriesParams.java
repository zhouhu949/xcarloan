package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZhangYaJun
 * @Title: EditCarSeriesParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 13:53
 */

@Data
@ApiModel("修改车系参数")
public class EditCarSeriesParams {
   @ApiModelProperty("车系id")
   private Long id;
   @ApiModelProperty("车系名称")
   private String seriesName;

   @ApiModelProperty("备注")
   private String remark;
}
