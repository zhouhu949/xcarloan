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
@ApiModel(description = "系统列资源实体")
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_column_resource")
public class SysColumnResource extends BaseEntity {
    @ApiModelProperty("父页面id")
    private Long resourcePid;
    @ApiModelProperty("资源名称")
    private String resourceName;
    @ApiModelProperty("资源码")
    private String resourceCode;
    @ApiModelProperty("资源级别")
    private Integer resourceLevel;
    @ApiModelProperty("状态")
    private Integer resourceStatus;
    @ApiModelProperty("资源图标")
    private String resourceIcon;
    @ApiModelProperty("资源文件类型")
    private Integer resourceFileType;
    @ApiModelProperty("顺序")
    private Integer resourceSort;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建人")
    private Long operator;
    @ApiModelProperty("创建日期")
    private Date operateTime;
}
