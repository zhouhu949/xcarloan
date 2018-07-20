package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回所有的角色，并标出该用户所选择的角色")
public class UserRoleModel {
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("用户是否选中")
    private boolean selected = false;
}
