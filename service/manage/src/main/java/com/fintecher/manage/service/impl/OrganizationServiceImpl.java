package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.BasicExpenseTemplate;
import com.fintecher.entity.SysOrg;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.OrganizationMapper;
import com.fintecher.manage.service.BasicExpenseService;
import com.fintecher.manage.service.BasicExpenseTemplateService;
import com.fintecher.manage.service.OrganizationService;
import com.fintecher.manage.vo.OrganizationModel;
import com.fintecher.manage.vo.WFOrgModel;
import com.fintecher.util.Status;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional(rollbackFor = Exception.class)
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<SysOrg> implements OrganizationService {
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private BasicExpenseTemplateService basicExpenseTemplateService;
    @Resource
    private BasicExpenseService basicExpenseService;

    @Override
    public List<OrganizationModel> findAllOrganization() {
        return organizationMapper.findDepartmentList();
    }

    @Override
    public SysOrg findSysOrg(Long orgId) {
        return organizationMapper.findSysOrg(orgId, SysOrg.OrgType.DEPARTMENT.getValue());
    }

    @Override
    public List<SysOrg> findAllOrganizationByAuth(List<String> dataAuth, List<String> exceptDataAuth) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        return organizationMapper.findAllOrganizationByAuth(paramsMap);
    }

    @Override
    public void addOrganization(SysOrg sysOrg, SysUser user) {
        // 新增机构
        this.save(sysOrg);
        if (Objects.equals(sysOrg.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())) {
            return;
        }
        // 添加费用项
        Long id = sysOrg.getId();
        List<BasicExpenseTemplate> templates = basicExpenseTemplateService.findAll();
        List<BasicExpense> expenseList = Lists.newArrayList();
        for (BasicExpenseTemplate template: templates) {
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setOperator(user.getId());
            basicExpense.setOperatorTime(new Date());
            basicExpense.setIsSystem(Status.Enable.getValue());
            basicExpense.setOrgId(id);
            basicExpense.setExpenseCode(template.getExpenseTemplateCode());
            basicExpense.setExpenseName(template.getExpenseTemplateName());
            basicExpense.setExpenseTemplateId(template.getId());
            basicExpense.setRemark("添加机构时系统自动添加的费用项");
            expenseList.add(basicExpense);
        }
        basicExpenseService.saveList(expenseList);
    }

    @Override
    public List<WFOrgModel> queryAllOrg(String orgName, Integer orgStatus) {
        return organizationMapper.queryAllOrg(orgName, orgStatus);
    }
}
