package com.fintecher.manage.vo;

import com.fintecher.entity.SysOrg;
import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysRole;
import com.fintecher.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel("用户登陆返回实体")
public class UserLoginModel {
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
    private List<Map> resource;
    @ApiModelProperty("用户密码是否需要重置")
    private boolean reset;
}
