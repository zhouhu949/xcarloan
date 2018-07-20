package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色机构信息")
@Data
public class RoleOrgModel {
    @ApiModelProperty("角色机构权限ID")
    private Long id;
    @ApiModelProperty("机构名称")
    private String orgName;
//    @ApiModelProperty("机构编号")
//    private String orgCode;
//    @ApiModelProperty("上级机构ID")
//    private Integer orgPid;
//    @ApiModelProperty("机构树形编码")
//    private String orgTreeCode;
//    @ApiModelProperty("是否选中")
//    private boolean selected = false;
}
