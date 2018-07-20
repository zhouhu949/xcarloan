package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@ApiModel("修改资源名称请求参数")
public class EditResourceNameParams {
    @ApiModelProperty(value = "资源ID", required = true)
    private Long resourceId;
    @ApiModelProperty(value = "资源名称", required = true)
    @NotBlank
    private String resourceName;
}
