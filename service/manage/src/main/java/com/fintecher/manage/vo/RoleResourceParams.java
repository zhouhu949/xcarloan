package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("角色分配资源参数")
public class RoleResourceParams {
    @ApiModelProperty("角色ID")
    @NotNull
    private Long roleId;
    @ApiModelProperty("资源ID集合")
    @NotEmpty(message = "请选择资源")
    private List<Long> resourceIds;
}
