package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysUserRole;
import com.fintecher.manage.mapper.SysUserRoleMapper;
import com.fintecher.manage.service.SysUserRoleService;
import com.fintecher.manage.vo.UserRoleParams;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<Long> findUserRoleIds(Long userId) {
        return sysUserRoleMapper.findUserRoleIds(userId);
    }

    @Override
    public void saveUserRole(UserRoleParams userRoleParams) {
        // 删除原有的角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userRoleParams.getUserId());
        this.deleteByWhere(sysUserRole);
        List<SysUserRole> sysUserRoleList = Lists.newArrayList();
        for (Long id : userRoleParams.getRoleIds()) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userRoleParams.getUserId());
            userRole.setRoleId(id);
            sysUserRoleList.add(userRole);
        }
        this.saveList(sysUserRoleList);
    }
}
