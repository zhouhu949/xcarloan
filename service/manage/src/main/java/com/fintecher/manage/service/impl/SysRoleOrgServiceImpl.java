package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysRoleOrgs;
import com.fintecher.manage.mapper.SysRoleOrgMapper;
import com.fintecher.manage.service.SysRoleOrgService;
import com.fintecher.manage.vo.RoleOrgModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleOrgServiceImpl extends BaseServiceImpl<SysRoleOrgs> implements SysRoleOrgService {

    @Autowired
    private SysRoleOrgMapper sysRoleOrgMapper;

    @Override
    public List<RoleOrgModel> findRoleOrg(Long roleId) {
        return sysRoleOrgMapper.findRoleOrg(roleId);
    }
}
