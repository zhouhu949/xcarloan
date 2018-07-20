package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 角色待办事项中间表实体类
 * @Date:Created on 2018/1/8/008 11:15
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "角色待办事项中间表实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "role_backlog")
public class RoleBacklog extends BaseEntity {
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", notes = "角色id")
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 待办事项id
     */
    @ApiModelProperty(value = "待办事项id", notes = "待办事项id")
    @Column(name = "backlog_id")
    private Long backlogId;
}
