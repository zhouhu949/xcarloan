package com.fintecher.common.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户数据权限信息")
public class UserDataAuthInfo {
    @ApiModelProperty("机构/部门ID")
    private Long orgId;
    @ApiModelProperty("机构/部门树编码")
    private String orgTreeCode;
}
