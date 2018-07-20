package com.fintecher.manage.service;

import com.fintecher.entity.SysRoleOrgs;
import com.fintecher.manage.vo.RoleOrgModel;

import java.util.List;

public interface SysRoleOrgService extends BaseService<SysRoleOrgs> {

    /**
     * 获取角色机构信息
     * @param roleId
     * @return
     */
    List<RoleOrgModel> findRoleOrg(Long roleId);
}
