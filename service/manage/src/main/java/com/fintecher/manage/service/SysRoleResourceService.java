package com.fintecher.manage.service;

import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysRoleResource;

import java.util.List;
import java.util.Map;

public interface SysRoleResourceService extends BaseService<SysRoleResource> {
    void deleteRoleResourceByRoleId(Long roleId);

    /**
     * 根据角色ID集合获取角色的菜单资源
     * @param roleIds
     * @return
     */
    List<SysResource> findRoleMenuResourceByRoleIds(List<Long> roleIds);

    /**
     * 根据角色ID集合获取角色的非菜单资源
     * @param roleIds
     * @return
     */
    List<Map> findRoleResourceByRoleIds(List<Long> roleIds);
}
