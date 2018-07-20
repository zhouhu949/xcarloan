package com.fintecher.manage.mapper;


import com.fintecher.entity.SysRole;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.RoleIdFindUsersModel;
import com.fintecher.manage.vo.SysRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends MyMapper<SysRole> {

    /**
     * 根据用户ID查询角色对象信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRoleByUserId(@Param("userId") Long userId);
    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 通过角色id查询用户
     * @Modified By:
     */
    List<RoleIdFindUsersModel> getUserByRoleId(Map<String, Object> params);
    /**
     * 获取数据权限下的所有角色
     * @param params
     * @return
     */
    List<SysRoleModel> findAllSysRole(Map<String, Object> params);

    /**
     * 获取数据权限下的所有启用的角色
     * @param params
     * @return
     */
    List<SysRole> findAllEnableRoleByAuth(Map<String, Object> params);
}
