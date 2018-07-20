package com.fintecher.manage.vo;

import com.fintecher.entity.SysUser;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户多条件查询参数")
public class SearchUserParams {
    @ApiModelProperty( "机构ID")
    private Long orgId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("姓名")
    private String realName;
    @ApiModelProperty("用户状态:10022-启用，100023-停用")
    @EnumValue(enumClass = SysUser.UserStatus.class, enumMethod="isValidValue", message = "用户状态有误")
    private Integer userStatus;
}
