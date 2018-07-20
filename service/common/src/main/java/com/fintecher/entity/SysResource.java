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
@ApiModel("系统资源")
@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseEntity {
    @ApiModelProperty("上级资源ID")
    private Long resourcePid;
    @ApiModelProperty("资源名称")
    private String resourceName;
    @ApiModelProperty("资源码")
    private String resourceCode;
    @ApiModelProperty("资源类型")
    private Integer resourceType;
    @ApiModelProperty("资源级别")
    private Integer resourceLevel;
    @ApiModelProperty("状态")
    private Integer resourceStatus;
    @ApiModelProperty("资源路径")
    private String resourceUrl;
    @ApiModelProperty("图标")
    private String resourceIcoUrl;
    @ApiModelProperty("资源文件类型")
    private Integer resourceFileType;
    @ApiModelProperty("序号")
    private Integer resourceOrder;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作日期")
    private Date operatorTime;
}
