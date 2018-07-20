package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户列配置实体")
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_user_column")
public class SysUserColumn extends BaseEntity {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("列资源id")
    private Long columnId;
    @ApiModelProperty("列资源父页面id")
    private Long columnPid;
    @ApiModelProperty("是否勾选")
    private Integer columnCheck;
    @ApiModelProperty("列排序")
    private Integer columnSort;
    @ApiModelProperty("资源code")
    private String columnCode;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作时间")
    private Date operatorTime;
}
