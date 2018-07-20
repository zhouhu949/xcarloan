package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色资源中间表实体类")
@Entity
@Table(name = "sys_role_resource")
public class SysRoleResource extends BaseEntity{
    @ApiModelProperty("资源id")
    private Long resourceId;
    @ApiModelProperty("角色id")
    private Long roleId;

}
