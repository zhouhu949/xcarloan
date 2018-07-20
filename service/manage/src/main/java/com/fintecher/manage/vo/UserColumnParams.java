package com.fintecher.manage.vo;

import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UserColumnParams {
    @ApiModelProperty("用户列ID")
    @NotNull(message = "用户列ID不能为空")
    private Long id;
    @ApiModelProperty("是否勾选：10002-是，10003-否")
    @NotNull(message = "是否勾选不能为空")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否勾选参数错误")
    private Integer columnCheck;
    @ApiModelProperty("列排序")
    private Integer columnSort;
}
