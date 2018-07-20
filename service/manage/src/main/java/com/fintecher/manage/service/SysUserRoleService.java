package com.fintecher.manage.service;

import com.fintecher.entity.SysUserRole;
import com.fintecher.manage.vo.UserRoleParams;

import java.util.List;

public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 根据用户ID获取用户所有角色ID集合
     * @param userId
     * @return
     */
    List<Long> findUserRoleIds(Long userId);

    void saveUserRole(UserRoleParams userRoleParams);
}
