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
@ApiModel("数据范围")
@Entity
@Table(name = "sys_role_orgs")
public class SysRoleOrgs extends BaseEntity{
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("角色ID")
    private Long roleId;
}
