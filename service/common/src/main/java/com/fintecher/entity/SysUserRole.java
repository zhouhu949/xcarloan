package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户角色实体类")
@Entity
@Table(name = "sys_user_role")
public class SysUserRole extends BaseEntity{
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("角色id")
    private Long roleId;
    @ApiModelProperty("创建人")
    private Long operator;
    @ApiModelProperty("创建日期")
    private Date operatorTime;
}
