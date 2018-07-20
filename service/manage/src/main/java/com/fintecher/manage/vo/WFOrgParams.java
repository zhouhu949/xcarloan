package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("工作流工作请求参数")
public class WFOrgParams {
    @ApiModelProperty("部门名称")
    private String orgName;
    @ApiModelProperty("部门状态：10022-启用，10023-停用")
    private Integer orgStatus;


    @ApiModelProperty(value = "页码", required = true)
    private Integer page;
    @ApiModelProperty(value = "页数", required = true)
    private Integer limit;
}
