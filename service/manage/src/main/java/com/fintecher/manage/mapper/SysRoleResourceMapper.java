package com.fintecher.manage.mapper;

import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysRoleResource;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleResourceMapper extends MyMapper<SysRoleResource> {
    /**
     * 删除角色id对应的资源权限
     */
    @Delete("DELETE from sys_role_resource where role_id = #{roleId}")
    void deleteRoleResourceByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID集合获取角色的菜单资源
     * @param roleIds
     * @return
     */
    List<SysResource> findRoleMenuResourceByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据角色ID集合获取角色的非菜单资源
     * @param roleIds
     * @return
     */
    List<Map> findRoleResourceByRoleIds(@Param("roleIds") List<Long> roleIds);
}
