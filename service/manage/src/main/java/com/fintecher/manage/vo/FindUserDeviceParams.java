package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@ApiModel("获取所选用户的所有设备")
public class FindUserDeviceParams {
    @ApiModelProperty("用户ID集合")
    @NotEmpty(message = "用户ID集合不能为空")
    private List<Long> userIds;
}
