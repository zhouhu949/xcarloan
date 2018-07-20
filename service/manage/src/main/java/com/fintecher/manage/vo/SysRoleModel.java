package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("查询角色返回数据")
public class SysRoleModel {

    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("角色ID")
    private Long id;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色描述")
    private String roleDesc;
    @ApiModelProperty("角色状态:10022-启用，10023-停用")
    private Integer roleStatus;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("部门名称")
    private String deptName;
    @ApiModelProperty("操作人")
    private String operatorName;
    @ApiModelProperty("操作日期")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;
}
