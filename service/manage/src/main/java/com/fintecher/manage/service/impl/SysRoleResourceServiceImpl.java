package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysRoleResource;
import com.fintecher.manage.mapper.SysRoleResourceMapper;
import com.fintecher.manage.service.SysRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService {
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public void deleteRoleResourceByRoleId(Long roleId) {
        sysRoleResourceMapper.deleteRoleResourceByRoleId(roleId);
    }

    @Override
    public List<SysResource> findRoleMenuResourceByRoleIds(List<Long> roleIds) {
        return sysRoleResourceMapper.findRoleMenuResourceByRoleIds(roleIds);
    }

    @Override
    public List<Map> findRoleResourceByRoleIds(List<Long> roleIds) {
        return sysRoleResourceMapper.findRoleResourceByRoleIds(roleIds);
    }
}
