package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysRoleExceptOrgs;
import com.fintecher.manage.mapper.SysRoleExceptOrgMapper;
import com.fintecher.manage.service.SysRoleExceptOrgService;
import com.fintecher.manage.vo.RoleOrgModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleExceptOrgServiceImpl extends BaseServiceImpl<SysRoleExceptOrgs> implements SysRoleExceptOrgService {
    @Autowired
    private SysRoleExceptOrgMapper sysRoleExceptOrgMapper;

    @Override
    public List<RoleOrgModel> findRoleExceptOrg(Long roleId) {
        return sysRoleExceptOrgMapper.findRoleExceptOrg(roleId);
    }
}
