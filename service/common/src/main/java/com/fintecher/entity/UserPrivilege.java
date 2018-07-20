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
 * @Description: 用户权限配置实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户权限配置实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "user_privilege")
public class UserPrivilege extends BaseEntity {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", notes = "用户id")
    @Column(name = "user_id")
    private Long userId;
    /**
     * 机构 id
     */
    @ApiModelProperty(value = "机构id", notes = "机构id")
    @Column(name = "dept_id")
    private Long deptId;
    /**
     * 机构编码
     */
    @ApiModelProperty(value = "机构编码", notes = "机构编码")
    @Column(name = "dept_code")
    private String deptCode;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark;
}
