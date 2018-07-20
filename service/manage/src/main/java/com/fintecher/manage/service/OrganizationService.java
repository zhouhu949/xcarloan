package com.fintecher.manage.service;


import com.fintecher.entity.SysOrg;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.OrganizationModel;
import com.fintecher.manage.vo.WFOrgModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface OrganizationService extends BaseService<SysOrg> {

    /**
     * 获取所有的组织机构
     * @return
     */
    List<OrganizationModel> findAllOrganization();

    /**
     * 根据组织机构的ID查询机构（总公司/分公司的信息）
     * @param orgId 机构/部门ID
     * @return
     */
    SysOrg findSysOrg(Long orgId);

    /**
     * 获取数据权限下所有的组织机构
     * @param dataAuth
     * @param exceptDataAuth
     * @return
     */
    List<SysOrg> findAllOrganizationByAuth(List<String> dataAuth, List<String> exceptDataAuth);

    void addOrganization(SysOrg sysOrg, SysUser user);

    /**
     * 工作流获取所有机构
     *
     * @param orgName
     * @param orgStatus
     * @return
     */
    List<WFOrgModel> queryAllOrg(String orgName, Integer orgStatus);

}
