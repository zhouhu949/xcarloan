package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author wxc
 * @Data 2018/2/23 14:01
 * @Description 订单导出模型
 */

@Data
@ApiModel(value = "订单导出模型")
public class ExportModel {

    @ApiModelProperty(value = "模板类型")
    private Integer templateType;

    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "数据配置项")
    private Map<String, List<String>> dataInfo;

    @ApiModelProperty(value = "字段匹配项")
    private Map<String,List<String>> proInfo;

    @ApiModelProperty(value = "下载地址")
    private String url;
}
