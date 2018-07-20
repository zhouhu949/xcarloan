package com.fintecher.manage.vo;

import com.fintecher.entity.SysRole;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("创建角色请求参数")
public class AddRoleParams {
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @ApiModelProperty("角色描述")
    private String roleDesc;
    @ApiModelProperty("角色状态:10022-启用，10023-停用")
    @NotNull(message = "请选择角色状态")
    @EnumValue(enumClass = SysRole.RoleStatus.class, enumMethod = "isValidValue", message = "角色状态有误")
    private Integer roleStatus;
    @ApiModelProperty("部门ID")
    @NotNull(message = "请选择部门")
    private Long deptId;
}
