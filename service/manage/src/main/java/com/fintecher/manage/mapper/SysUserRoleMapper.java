package com.fintecher.manage.mapper;

import com.fintecher.entity.SysUserRole;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    @Select("SELECT DISTINCT role_id FROM sys_user_role WHERE user_id = #{userId}")
    List<Long> findUserRoleIds(@Param("userId") Long userId);

    void deleteUserRoleByUserIds(@Param("userIds") List<Long> userIds);
}
