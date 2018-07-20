package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@ApiModel("用户修改密码请求参数")
public class UpdatePasswordParams {
    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
