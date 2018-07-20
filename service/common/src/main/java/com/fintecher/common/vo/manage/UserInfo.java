package com.fintecher.common.vo.manage;

import com.fintecher.entity.SysOrg;
import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysRole;
import com.fintecher.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户信息")
public class UserInfo {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("用户")
    private SysUser user;
    @ApiModelProperty("角色")
    private List<SysRole> rolesList;
    @ApiModelProperty("部门")
    private SysOrg sysOrg;
    @ApiModelProperty("菜单")
    private List<SysResource> menu;
    @ApiModelProperty("资源")
    private List resource;
}
