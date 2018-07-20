package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("工作流查询用户返回数据")
public class WFUserModel {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("姓名")
    private String userRealname;
    @ApiModelProperty("用户名")
    private String userUsername;
    @ApiModelProperty("状态：10022-启用，10033-停用")
    private Integer userStatus;
    @ApiModelProperty("性别：10024-男，10025-女")
    private Integer userSex;
    @ApiModelProperty("电话")
    private String userPhone;
    @ApiModelProperty("邮箱")
    private String userEmail;
    @ApiModelProperty("备注")
    private String userRemark;
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("组织机构名称")
    private String orgName;
    @ApiModelProperty("部门名称")
    private String deptName;
}
