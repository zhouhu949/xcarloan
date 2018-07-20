package com.fintecher.manage.service.impl;

import com.fintecher.entity.*;
import com.fintecher.manage.mapper.RoleMapper;
import com.fintecher.manage.service.RoleService;
import com.fintecher.manage.service.SysRoleExceptOrgService;
import com.fintecher.manage.service.SysRoleOrgService;
import com.fintecher.manage.service.SysRoleResourceService;
import com.fintecher.manage.vo.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jwdstef on 2017/2/8.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysRoleOrgService sysRoleOrgService;
    @Resource
    private SysRoleExceptOrgService sysRoleExceptOrgService;

    @Override
    public List<SysRoleModel> findAllSysRole(List<String> dataAuth, List<String> exceptDataAuth, SearchRoleParams params) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        paramsMap.put("params", params);
        return roleMapper.findAllSysRole(paramsMap);
    }
    /**
     * 通过角色id查询用户
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<RoleIdFindUsersModel> getUserByRoleId(Map<String, Object> params) {
        return roleMapper.getUserByRoleId(params);
    }
    @Override
    public List<SysRole> findAllEnableRoleByAuth(List<String> dataAuth, List<String> exceptDataAuth, String roleName) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        paramsMap.put("roleName", roleName);
        return roleMapper.findAllEnableRoleByAuth(paramsMap);
    }

    @Override
    public List<SysRole> findRoleByUserId(Long userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public void roleResource(RoleResourceParams roleResourceParams) {
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(roleResourceParams.getRoleId());
        sysRoleResourceService.deleteByWhere(sysRoleResource);
        List<SysRoleResource> sysRoleResourceList = Lists.newArrayList();
        roleResourceParams.getResourceIds().forEach(e -> {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleResourceParams.getRoleId());
            roleResource.setResourceId(e);
            sysRoleResourceList.add(roleResource);
        });
        sysRoleResourceService.saveList(sysRoleResourceList);
    }

    @Override
    public void roleOrg(RoleOrgParams roleOrgParams) {
        List<SysRoleOrgs> sysRoleOrgsList = Lists.newArrayList();
        roleOrgParams.getOrgIds().forEach(e -> {
            SysRoleOrgs roleOrgs = new SysRoleOrgs();
            roleOrgs.setRoleId(roleOrgParams.getRoleId());
            roleOrgs.setOrgId(e);
            sysRoleOrgsList.add(roleOrgs);
        });
        sysRoleOrgService.saveList(sysRoleOrgsList);
    }

    @Override
    public void roleExceptOrg(RoleExceptOrgParams roleExceptOrgParams) {
        List<SysRoleExceptOrgs> sysRoleExceptOrgsList = Lists.newArrayList();
        roleExceptOrgParams.getOrgIds().forEach(e -> {
            SysRoleExceptOrgs roleExceptOrgs = new SysRoleExceptOrgs();
            roleExceptOrgs.setRoleId(roleExceptOrgParams.getRoleId());
            roleExceptOrgs.setOrgId(e);
            sysRoleExceptOrgsList.add(roleExceptOrgs);
        });
        sysRoleExceptOrgService.saveList(sysRoleExceptOrgsList);
    }

    @Override
    public void deleteRole(Long id) {
        // 先删除角色关联的资源/权限
        // 删除资源
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(id);
        sysRoleResourceService.deleteByWhere(sysRoleResource);
        // 删除权限数据
        SysRoleOrgs sysRoleOrgs = new SysRoleOrgs();
        sysRoleOrgs.setRoleId(id);
        sysRoleOrgService.deleteByWhere(sysRoleOrgs);
        // 删除排除数据
        SysRoleExceptOrgs sysRoleExceptOrgs = new SysRoleExceptOrgs();
        sysRoleExceptOrgs.setRoleId(id);
        sysRoleExceptOrgService.deleteByWhere(sysRoleExceptOrgs);
        // 删除角色
        this.deleteById(id);
    }

    @Override
    public void updateRole(SysUser user, UpdateRoleParams updateRoleParams) {
        // 先删除角色关联的资源/权限
        // 删除资源
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(updateRoleParams.getId());
        sysRoleResourceService.deleteByWhere(sysRoleResource);
        // 删除权限数据
        SysRoleOrgs sysRoleOrgs = new SysRoleOrgs();
        sysRoleOrgs.setRoleId(updateRoleParams.getId());
        sysRoleOrgService.deleteByWhere(sysRoleOrgs);
        // 删除排除数据
        SysRoleExceptOrgs sysRoleExceptOrgs = new SysRoleExceptOrgs();
        sysRoleExceptOrgs.setRoleId(updateRoleParams.getId());
        sysRoleExceptOrgService.deleteByWhere(sysRoleExceptOrgs);
        // 删除角色
        SysRole sysRoles = new SysRole();
        BeanUtils.copyProperties(updateRoleParams, sysRoles);
        sysRoles.setOperatorTime(new Date());
        sysRoles.setOperator(user.getId());
        this.updateSelective(sysRoles);
    }
}
