package com.fintecher.common.vo.manage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleResourceModel implements Serializable {
    @ApiModelProperty("资源ID")
    private Long id;
    @ApiModelProperty("父资源ID")
    private Long pid;
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @ApiModelProperty(value = "资源路径", notes = "资源路径")
    private String path;
    @ApiModelProperty(value = "资源图标", notes = "资源图标")
    private String icon;
    @ApiModelProperty(value = "类型", notes = "类型")
    private Integer resotype;
    @ApiModelProperty(value = "资源文件类型", notes = "421 菜单 422 目录 423 按钮 424 输入框 425 列表")
    private Integer filetype;
    @ApiModelProperty(value = "排序", notes = "排序")
    private Integer sort;
    @ApiModelProperty(value = "资源描述", notes = "资源描述")
    private String resoRemark;
}
