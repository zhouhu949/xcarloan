package com.fintecher.manage.mapper;

import com.fintecher.entity.SysRoleExceptOrgs;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.RoleOrgModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleExceptOrgMapper extends MyMapper<SysRoleExceptOrgs> {

    /**
     * 获取角色排除机构信息
     * @param roleId
     * @return
     */
    List<RoleOrgModel> findRoleExceptOrg(@Param("roleId")Long roleId);
}
