package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@ApiModel("用户批量分配角色")
public class UserBatchAllocateRolesModel {
    @ApiModelProperty("用户ID集合")
    @NotEmpty(message = "用户集合不能为空")
    private List<Long> userIds;
    @ApiModelProperty("角色ID集合")
    @NotEmpty(message = "角色集合不能为空")
    private List<Long> roleIds;
}
