package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改资源图标请求参数")
public class EditResourceIconParams {
    @ApiModelProperty(value = "资源ID", required = true)
    @NotNull(message = "资源ID不能为空")
    private Long resourceId;
    @ApiModelProperty(value = "资源图标", required = true)
    @NotBlank(message = "资源图标不能为空")
    private String resourceIcon;
}
