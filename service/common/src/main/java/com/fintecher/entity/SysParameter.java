package com.fintecher.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 系统参数实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_parameter")
@Entity
public class SysParameter extends BaseEntity {


    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称", notes = "参数名称")
    @Column(name = "param_name")
    private String paramName;
    /**
     * 参数自定义code
     */
    @NotNull
    @ApiModelProperty(value = "参数自定义code", notes = "参数自定义code")
    @Column(name = "param_code")
    private String paramCode;
    /**
     * 参数状态 435 启用 436 停用
     */
    @NotNull
    @ApiModelProperty(value = "参数状态", notes = "435 启用 436 停用")
    @Column(name = "param_status")
    private Integer paramStatus;
    /**
     * 参数类型（服务的端口号）
     */
    @NotNull
    @ApiModelProperty(value = "参数类型（服务的端口号）", notes = "参数类型（服务的端口号）")
    @Column(name = "param_type")
    private Integer paramType;
    /**
     * 参数值value
     */
    @ApiModelProperty(value = "参数值value", notes = "参数值value")
    @Column(name = "param_value")
    private String paramValue;

    /**
     * 是否允许修改
     */
    @ApiModelProperty(value = "是否允许修改", notes = "是否允许修改")
    @Column(name = "is_readonly")
    private Integer isReadonly;



    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(notes = "操作员")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(notes = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 系统参数状态
     */
    public enum Status {
        // 0-启用 1-禁用
        ENABLE(0), DISABLE(1);
        private Integer status;

        Status(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }
}
