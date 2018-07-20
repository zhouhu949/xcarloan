package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("用户分配角色参数")
public class UserRoleParams {
    @ApiModelProperty("用户ID")
    @NotNull
    private Long userId;
    @ApiModelProperty("角色ID集合")
    @NotEmpty
    private List<Long> roleIds;
}
