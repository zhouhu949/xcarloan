package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("工作流用户查询")
public class WFUserParams {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("用户状态：10022-启用，10023-停用")
    private Integer userStatus;
    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty(value = "页码", required = true)
    private Integer page;
    @ApiModelProperty(value = "页数", required = true)
    private Integer limit;
}
