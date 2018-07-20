package com.fintecher.manage.mapper;

import com.fintecher.entity.SysOrg;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.OrganizationModel;
import com.fintecher.manage.vo.WFOrgModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrganizationMapper extends MyMapper<SysOrg> {

    List<OrganizationModel> findDepartmentList();

    SysOrg findSysOrg(@Param("orgId") Long orgId, @Param("orgType") Integer orgType);

    List<SysOrg> findAllOrganizationByAuth(Map<String, Object> params);


    /**
     * 工作流查询所有机构
     *
     * @param orgName
     * @param orgStatus
     * @return
     */
    List<WFOrgModel> queryAllOrg(@Param("orgName") String orgName, @Param("orgStatus") Integer orgStatus);
}
