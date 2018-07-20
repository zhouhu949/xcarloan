package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@ApiModel("更新用户列配置请求参数")
public class UpdateUserColumnParams {
    @ApiModelProperty("用户列配置信息集合")
    @NotEmpty(message = "参数不能为空")
    private List<UserColumnParams> userColumnList;
}
