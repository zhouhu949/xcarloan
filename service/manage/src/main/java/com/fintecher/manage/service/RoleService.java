package com.fintecher.manage.service;


import com.fintecher.entity.SysRole;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<SysRole> {

    List<SysRoleModel> findAllSysRole(List<String> dataAuth, List<String> exceptDataAuth, SearchRoleParams params);
    /**
     * @Description: 通过角色id查询用户
     * @Modified By:
     */
    List<RoleIdFindUsersModel> getUserByRoleId(Map<String, Object> params);

    List<SysRole> findAllEnableRoleByAuth(List<String> dataAuth, List<String> exceptDataAuth, String roleName);

    /**
     * 获取用户的所有角色信息
     * @param userId
     * @return
     */
    List<SysRole> findRoleByUserId(Long userId);

    void roleResource(RoleResourceParams roleResourceParams);

    void roleOrg(RoleOrgParams roleOrgParams);

    void roleExceptOrg(RoleExceptOrgParams roleExceptOrgParams);

    void deleteRole(Long id);

    void updateRole(SysUser user, UpdateRoleParams updateRoleParams);

}
