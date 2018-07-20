package com.fintecher.manage.vo;

import com.fintecher.entity.SysRole;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改角色请求参数")
public class UpdateRoleParams {
    @ApiModelProperty("角色ID")
    @NotNull
    private Long id;
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @ApiModelProperty("角色状态：10022-启用，10023-禁用")
    @NotNull
    @EnumValue(enumClass = SysRole.RoleStatus.class, enumMethod = "isValidValue", message = "角色状态有误")
    private Integer roleStatus;
    @ApiModelProperty("描述")
    private String roleDesc;
}
