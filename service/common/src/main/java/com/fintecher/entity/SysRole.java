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
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色实体类")
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色描述")
    private String roleDesc;
    @ApiModelProperty("角色状态")
    private Integer roleStatus;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作日期")
    private Date operatorTime;

    /**
     * 角色状态
     */
    public enum RoleStatus{
        ENABLE(10022, "启用"),
        DISABLE(10023, "禁用");

        private Integer value;
        private String remark;

        RoleStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (RoleStatus eroleStatus : RoleStatus.values()) {
                if (Objects.equals(eroleStatus.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
