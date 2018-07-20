package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/sysRoleController")
@Api(description = "角色管理")
public class SysRoleController extends BaseController {

    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysRoleOrgService sysRoleOrgService;
    @Resource
    private SysRoleExceptOrgService sysRoleExceptOrgService;
    @Resource
    private OrganizationService organizationService;
    @Resource
    private SysResourceService sysResourceService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    /*@ApiIgnore
    @GetMapping("/findAllRole")
    @ApiOperation(value = "获取所有的角色", notes = "获取所有的角色")
    public ResponseResult findAllRole() {
        logger.debug("Rest request findAllRole");
        try {
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, roleService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }*/

    @GetMapping("/findAllRoleByAuth")
    @ApiOperation(value = "多条件查询-获取数据权限内的所有角色", notes = "多条件查询-获取数据权限内的所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SysRoleModel>> findAllRoleByAuth(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                    @ApiIgnore PageParam pageParam,
                                                                    SearchRoleParams searchRoleParams) {
        logger.debug("Rest request findAllRoleByAuth");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<SysRoleModel> allSysRole = roleService.findAllSysRole(dataAuth, exceptDataAuth, searchRoleParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allSysRole));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findAllEnableRoleByAuth")
    @ApiOperation(value = "获取数据权限内的所有启用的角色", notes = "获取数据权限内的所有启用的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SysRole>> findAllEnableRoleByAuth(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                     @ApiIgnore PageParam pageParam,
                                                                     @RequestParam(required = false) @ApiParam("角色名称") String roleName) {
        logger.debug("Rest request findAllEnableRoleByAuth");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<SysRole> allEnableRoleByAuth = roleService.findAllEnableRoleByAuth(dataAuth, exceptDataAuth, roleName);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allEnableRoleByAuth));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/roleResource")
    @ApiOperation(value = "角色分配资源", notes = "角色分配资源")
    public ResponseResult roleResource(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                       @Validated @RequestBody RoleResourceParams roleResourceParams) {
        logger.debug("Rest request roleResource {}", roleResourceParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(roleResourceParams.getResourceIds()) || roleResourceParams.getResourceIds().isEmpty()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "请先选择资源");
            }
            roleService.roleResource(roleResourceParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "分配成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/roleOrg")
    @ApiOperation(value = "角色配置机构数据权限", notes = "角色配置机构数据权限")
    public ResponseResult roleOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                  @Validated @RequestBody RoleOrgParams roleOrgParams) {
        logger.debug("Rest request roleOrg {}", roleOrgParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(roleOrgParams.getOrgIds()) || roleOrgParams.getOrgIds().isEmpty()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "请先选择机构");
            }
            Example example = new Example(SysRoleOrgs.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", roleOrgParams.getRoleId());
            criteria.andIn("orgId", roleOrgParams.getOrgIds());
            if (sysRoleOrgService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构已存在");
            }
            roleService.roleOrg(roleOrgParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "分配成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/roleExceptOrg")
    @ApiOperation(value = "角色排除机构数据权限", notes = "角色排除机构数据权限")
    public ResponseResult roleExceptOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                        @Validated @RequestBody RoleExceptOrgParams roleExceptOrgParams) {
        logger.debug("Rest request roleExceptOrg {}", roleExceptOrgParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(roleExceptOrgParams.getOrgIds()) || roleExceptOrgParams.getOrgIds().isEmpty()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "请先选择机构");
            }
            Example example = new Example(SysRoleExceptOrgs.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", roleExceptOrgParams.getRoleId());
            criteria.andIn("orgId", roleExceptOrgParams.getOrgIds());
            if (sysRoleExceptOrgService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "机构已存在");
            }
            roleService.roleExceptOrg(roleExceptOrgParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "分配成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteRoleOrg/{id}")
    @ApiOperation(value = "删除角色授权机构", notes = "删除角色授权机构")
    public ResponseResult deleteRoleOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                        @PathVariable("id") @ApiParam("授权ID") Long id) {
        logger.debug("Rest request deleteRoleOrg {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            sysRoleOrgService.deleteById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @DeleteMapping("/deleteRoleExceptOrg/{id}")
    @ApiOperation(value = "删除角色排除机构", notes = "删除角色排除机构")
    public ResponseResult deleteRoleExceptOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                              @PathVariable("id") @ApiParam("授权ID") Long id) {
        logger.debug("Rest request deleteRoleExceptOrg {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            sysRoleExceptOrgService.deleteById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findRoleOrg/{id}")
    @ApiOperation(value = "获取角色拥有的机构权限信息", notes = "获取角色拥有的机构权限信息")
    public ResponseResult<List<RoleOrgModel>> findRoleOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                          @PathVariable("id") @ApiParam("角色ID") Long id) {
        logger.debug("Rest request findRoleOrg {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<RoleOrgModel> roleOrg = sysRoleOrgService.findRoleOrg(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, roleOrg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findRoleExceptOrg/{id}")
    @ApiOperation(value = "获取角色排除的机构权限信息", notes = "获取角色排除的机构权限信息")
    public ResponseResult<List<RoleOrgModel>> findRoleExceptOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                @PathVariable("id") @ApiParam("角色ID") Long id) {
        logger.debug("Rest request findRoleExceptOrg {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<RoleOrgModel> roleExceptOrg = sysRoleExceptOrgService.findRoleExceptOrg(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, roleExceptOrg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/addRole")
    @ApiOperation(value = "创建角色", notes = "创建角色")
    public ResponseResult addRole(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                  @Validated @RequestBody AddRoleParams addRoleParams) {
        logger.debug("Rest request addRole {}", addRoleParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(SysRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleName", addRoleParams.getRoleName());
            if (roleService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "角色名称已存在");
            }
            SysRole roles = new SysRole();
            BeanUtils.copyProperties(addRoleParams, roles);
            roles.setOperator(user.getId());
            roles.setOperatorTime(new Date());
            SysOrg sysOrg = organizationService.findSysOrg(roles.getDeptId());
            roles.setOrgId(sysOrg.getId());
            roleService.save(roles);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteRole/{id}")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public ResponseResult deleteRole(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                     @PathVariable("id") @ApiParam("角色ID") Long id) {
        logger.debug("Rest request deleteRole {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.equals(id, Constants.ADMINISTRATOR_ROLE_ID)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "超级管理员角色不允许删除");
            }
            Example example = new Example(SysUserRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", id);
            if (sysUserRoleService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该角色正在使用不允许删除");
            }
            roleService.deleteRole(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/updateRole")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public ResponseResult updateRole(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                     @Validated @RequestBody UpdateRoleParams updateRoleParams) {
        logger.debug("Rest request updateRole {}", updateRoleParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.equals(updateRoleParams.getId(), Constants.ADMINISTRATOR_ROLE_ID)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "超级管理员不允许修改");
            }
            Example example = new Example(SysRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleName", updateRoleParams.getRoleName());
            criteria.andNotEqualTo("id", updateRoleParams.getId());
            if (roleService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "角色名称已存在");
            }
            if(!Objects.equals(updateRoleParams.getRoleStatus(), SysRole.RoleStatus.ENABLE.getValue())) {
                Example userRoleExample = new Example(SysUserRole.class);
                Example.Criteria c = userRoleExample.createCriteria();
                c.andEqualTo("roleId", updateRoleParams.getId());
                if (sysUserRoleService.selectCountByExample(userRoleExample) > 0) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "该角色正在使用不允许禁用");
                }
            }
            roleService.updateRole(user, updateRoleParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "开发使用角色分配资源", notes = "开发使用角色分配资源")
    @GetMapping(value = "/roleAllocateResourcesDevelopUse")
    public ResponseResult roleAllocateResourcesDevelopUse(@RequestParam(value = "roleId") @ApiParam(value = "角色id") Long roleId) {
        try {
            List<SysResource> roleResoList = sysResourceService.findAll();
            sysRoleResourceService.deleteRoleResourceByRoleId(roleId);
            for (SysResource roleReso : roleResoList) {
                SysRoleResource roleResource = new SysRoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(roleReso.getId());
                sysRoleResourceService.save(roleResource);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping(value = "/queryUserByRoleIdPage")
    @ApiOperation(value = "通过角色id查询用户分页", notes = "通过角色id查询用户分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<RoleIdFindUsersModel>> queryUserByRoleIdPage(@RequestParam(value = "roleId") @ApiParam(value = "角色id") Long roleId,
                                              @RequestParam(value = "userName", required = false) @ApiParam(value = "用户名") String userName,
                                              @RequestParam(value = "realName", required = false) @ApiParam(value = "用户姓名") String realName,
                                              @ApiIgnore PageParam pageParam) {
        try {
            SysRole role = roleService.findById(roleId);
            if (Objects.isNull(role)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "该角色已被删除，请重新选择");
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize(), true);
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleId);
            map.put("userName", userName);
            map.put("realName", realName);
            List<RoleIdFindUsersModel> mapList = roleService.getUserByRoleId(map);
            PageInfo<RoleIdFindUsersModel> pageInfo = new PageInfo<>(mapList);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
