package com.fintecher.manage.service;

import com.fintecher.entity.SysRoleExceptOrgs;
import com.fintecher.manage.vo.RoleOrgModel;

import java.util.List;

public interface SysRoleExceptOrgService extends BaseService<SysRoleExceptOrgs> {
    /**
     * 获取角色排除机构信息
     * @param roleId
     * @return
     */
    List<RoleOrgModel> findRoleExceptOrg(Long roleId);
}
