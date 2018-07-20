package com.fintecher.manage.vo;

import com.fintecher.entity.SysRole;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("多条件查询角色请求参数")
public class SearchRoleParams {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色状态")
    @EnumValue(enumClass = SysRole.RoleStatus.class, enumMethod = "isValidValue", message = "角色状态有误")
    private Integer roleStatus;
}
