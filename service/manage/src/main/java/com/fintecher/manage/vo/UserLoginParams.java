package com.fintecher.manage.vo;

import com.fintecher.entity.SysUserDevice;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户登陆请求参数")
public class UserLoginParams {
//    @ApiModelProperty("登陆类型：10017-密码登陆，10018-二维码登陆，10019-人脸识别")
//    private Integer loginType;
    @ApiModelProperty(notes = "登陆设备：10020-PC，10021-APP")
    @NotNull
    @EnumValue(enumClass=SysUserDevice.DeviceType.class, enumMethod="isValidValue", message = "登陆设备有误")
    private Integer loginDevice;
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userUsername;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String userPassword;
}
