package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("工作流查询机构返回数据")
public class WFOrgModel {
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("机构性质:10036-集团，10004-总公司，10005-分公司，10006-部门")
    private Integer orgType;
    @ApiModelProperty("上级机构ID")
    private Long orgPid;
    @ApiModelProperty("机构树形编码")
    private String orgTreeCode;
    @ApiModelProperty("机构等级:10007~10014-一级-八级机构")
    private Integer orgLevel;
    @ApiModelProperty("机构状态:10022-启用，10023-停用")
    private Integer orgStatus;
    @ApiModelProperty("备注")
    private String orgRemark;
}
