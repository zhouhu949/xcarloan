package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysOrg;
import com.fintecher.entity.SysParameter;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.OrganizationService;
import com.fintecher.manage.service.SysParameterService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.AddOrganizationParams;
import com.fintecher.manage.vo.EditOrganizationParams;
import com.fintecher.util.Constants;
import com.fintecher.util.ShortUUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sysOrgController")
@Api(description = "组织机构管理")
public class SysOrgController extends BaseController {

    @Resource
    private OrganizationService organizationService;
    @Resource
    private UserService userService;
    @Resource
    private SysParameterService sysParameterService;

    @ApiIgnore
    @GetMapping("/findAllOrganization")
    @ApiOperation(value = "获取所有的组织机构", notes = "获取所有的组织机构")
    public ResponseResult findAllOrganization() {
        logger.debug("Rest request findAllOrganization");
        try {
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, organizationService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findAllOrganizationByAuth")
    @ApiOperation(value = "获取数据权限下所有的组织机构", notes = "获取数据权限下所有的组织机构")
    public ResponseResult<List<SysOrg>> findAllOrganizationByAuth(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request findAllOrganizationByAuth");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            List<SysOrg> allOrganizationByAuth = organizationService.findAllOrganizationByAuth(dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, allOrganizationByAuth);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/addOrganization")
    @ApiOperation(value = "创建组织机构", notes = "创建组织机构")
    public ResponseResult addOrganization(@Validated @RequestBody AddOrganizationParams addOrganizationParams,
                                          @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to addOrganization {}", addOrganizationParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysOrg sysOrg = new SysOrg();
            sysOrg.setOrgName(addOrganizationParams.getOrgName());
            if (Objects.nonNull(organizationService.findOne(sysOrg))) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构名称已存在");
            }
            SysOrg sysOrgParent = organizationService.findById(addOrganizationParams.getOrgPid());
            if (!Objects.equals(sysOrgParent.getOrgStatus(), SysOrg.OrgStatus.ENABLE.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "父级机构已停用");
            }
            if (sysOrgParent.getOrgLevel() < SysOrg.OrgLevel.ONE.getValue() || sysOrgParent.getOrgLevel() > SysOrg.OrgLevel.EIGHT.getValue()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构等级在1~8级");
            }
            SysParameter sysParameter = new SysParameter();
            sysParameter.setParamCode(Constants.PROJECT_DEFAULT_ENV_CODE);
            SysParameter one = sysParameterService.findOne(sysParameter);
            if (Objects.isNull(one)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "系统参数中未配置系统环境");
            }
            if ((!Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.GROUP.getValue())
                    || !Objects.equals(sysOrgParent.getId(), Constants.ADMINISTRATOR_DEPARTMENT_ID)
                    || !Objects.equals(one.getParamValue(), String.valueOf(Constants.ProjectEnv.SAAS.getValue())))
                    && Objects.equals(addOrganizationParams.getOrgType(), SysOrg.OrgType.GROUP.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该机构下不能创建集团");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.PARENT_COMPANY.getValue())
                    && Objects.equals(addOrganizationParams.getOrgType(), SysOrg.OrgType.PARENT_COMPANY.getValue())) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "总公司下只能创建分公司和部门");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.CHILD_COMPANY.getValue())
                    && Objects.equals(addOrganizationParams.getOrgType(), SysOrg.OrgType.CHILD_COMPANY.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "分公司下只能创建部门");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())
                    && !Objects.equals(addOrganizationParams.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "部门下不能创建总公司或者分公司");
            }
            String code = String.format("%s_%s", sysOrgParent.getOrgTreeCode(), ShortUUID.generateShortUuid());
            SysOrg sysOrg1 = new SysOrg();
            BeanUtils.copyProperties(addOrganizationParams, sysOrg1);
            sysOrg1.setOrgCode(code);
            sysOrg1.setOrgTreeCode(code);
            sysOrg1.setOrgLevel(sysOrgParent.getOrgLevel() + 1);
            sysOrg1.setOperator(user.getId());
            sysOrg1.setOperatorTime(new Date());
            organizationService.addOrganization(sysOrg1, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/editOrganization")
    @ApiOperation(value = "修改组织机构", notes = "修改组织机构")
    public ResponseResult editOrganization(@Validated @RequestBody EditOrganizationParams editOrganizationParams,
                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to editOrganization {}", editOrganizationParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(SysOrg.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orgName", editOrganizationParams.getOrgName());
            criteria.andNotEqualTo("id", editOrganizationParams.getId());
            if (organizationService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构名称已存在");
            }
            SysOrg sysOrg = organizationService.findById(editOrganizationParams.getId());
            if (Objects.equals(sysOrg.getId(), Constants.ADMINISTRATOR_DEPARTMENT_ID)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "顶级机构不允许修改");
            }
            SysOrg sysOrgParent = organizationService.findById(editOrganizationParams.getOrgPid());
            if (!Objects.equals(sysOrgParent.getOrgStatus(), SysOrg.OrgStatus.ENABLE.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "父级机构已停用");
            }
            if (sysOrgParent.getOrgLevel() < SysOrg.OrgLevel.ONE.getValue() || sysOrgParent.getOrgLevel() > SysOrg.OrgLevel.EIGHT.getValue()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构等级在1~8级");
            }
            SysParameter sysParameter = new SysParameter();
            sysParameter.setParamCode(Constants.PROJECT_DEFAULT_ENV_CODE);
            SysParameter one = sysParameterService.findOne(sysParameter);
            if (Objects.isNull(one)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "系统参数中未配置系统环境");
            }
            if ((!Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.GROUP.getValue())
                    || !Objects.equals(sysOrgParent.getId(), Constants.ADMINISTRATOR_DEPARTMENT_ID)
                    || !Objects.equals(one.getParamValue(), String.valueOf(Constants.ProjectEnv.SAAS.getValue())))
                    && Objects.equals(editOrganizationParams.getOrgType(), SysOrg.OrgType.GROUP.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该机构下不能创建集团");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.PARENT_COMPANY.getValue())
                    && Objects.equals(editOrganizationParams.getOrgType(), SysOrg.OrgType.PARENT_COMPANY.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "总公司下只能创建分公司和部门");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.CHILD_COMPANY.getValue())
                    && Objects.equals(editOrganizationParams.getOrgType(), SysOrg.OrgType.CHILD_COMPANY.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "分公司下只能创建部门");
            }
            if (Objects.equals(sysOrgParent.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())
                    && !Objects.equals(editOrganizationParams.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "部门下不能创建总公司或者分公司");
            }
            String code = String.format("%s_%s", sysOrgParent.getOrgTreeCode(), ShortUUID.generateShortUuid());
            SysOrg sysOrg1 = new SysOrg();
            BeanUtils.copyProperties(editOrganizationParams, sysOrg1);
            sysOrg1.setOrgCode(code);
            sysOrg1.setOrgTreeCode(code);
            sysOrg1.setOrgLevel(sysOrgParent.getOrgLevel() + 1);
            sysOrg1.setOperator(user.getId());
            sysOrg1.setOperatorTime(new Date());
            organizationService.update(sysOrg1);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteOrganization/{id}")
    @ApiOperation(value = "删除组织机构", notes = "删除组织机构")
    public ResponseResult deleteOrganization(@PathVariable("id") @ApiParam("机构ID") Long id,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to deleteOrganization {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysOrg sysOrg = organizationService.findById(id);
            if (Objects.equals(sysOrg.getOrgPid(), 0L)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "顶级机构不能删除");
            }
            if (!Objects.equals(sysOrg.getOrgType(), SysOrg.OrgType.DEPARTMENT.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "非部门机构不能删除");
            }
            Example example = new Example(SysOrg.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orgPid", sysOrg.getId());
            if(organizationService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该机构下存在子机构不允许删除");
            }
            Example userExample = new Example(SysUser.class);
            Example.Criteria criteria1 = userExample.createCriteria();
            criteria1.andEqualTo("orgId", sysOrg.getId());
            if (userService.selectCountByExample(userExample) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该机构下存在用户不允许删除");
            }
            organizationService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
