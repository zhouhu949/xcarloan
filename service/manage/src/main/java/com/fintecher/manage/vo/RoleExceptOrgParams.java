package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("角色排除机构数据权限参数")
@Data
public class RoleExceptOrgParams {
    @ApiModelProperty("角色ID")
    @NotNull
    private Long roleId;
    @ApiModelProperty("机构ID集合")
    @NotEmpty
    private List<Long> orgIds;
}
