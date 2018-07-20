package com.fintecher.manage.vo;

import com.fintecher.entity.SysUser;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("新增用户请求参数")
public class UserParams {
    @ApiModelProperty("姓名")
    @NotBlank(message = "用户名不能为空")
    private String userRealname;
    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    private Integer userSex;
    @ApiModelProperty("电话")
    @NotBlank(message = "手机号不能为空")
    private String userPhone;
    @ApiModelProperty("邮箱")
    @Email(message = "邮箱不能错误")
    private String userEmail;
    @ApiModelProperty("用户头像")
    private String userPhoto;
    @ApiModelProperty("部门ID")
    @NotNull(message = "所属部门不能为空")
    private Long deptId;
    @ApiModelProperty("状态：10022-启用，10023-停用")
    @NotNull(message = "状态不能为空")
    @EnumValue(enumClass = SysUser.UserStatus.class, enumMethod="isValidValue", message = "用户状态有误")
    private Integer userStatus;
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userUsername;
    @ApiModelProperty("备注")
    private String userRemark;
}
