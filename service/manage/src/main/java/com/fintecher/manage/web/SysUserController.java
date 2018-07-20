package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.MD5;
import com.fintecher.util.Status;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/sysUserController")
@Api(description = "用户管理")
public class SysUserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private OrganizationService organizationService;
    @Resource
    private RoleService roleService;
    @Resource
    private SysParameterService sysParameterService;
    @Resource
    private SysUserDeviceService sysUserDeviceService;

    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public ResponseResult addUser(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                  @Validated @RequestBody UserParams userParams) {
        logger.debug("Rest request to add user {}", userParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(SysUser.class);
            example.createCriteria().andEqualTo("userUsername", userParams.getUserUsername());
            if (userService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "用户名已存在");
            }
            userService.addUser(user, userParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping(value = "/updateUser")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public ResponseResult updateUser(@Validated @RequestBody EditUserParams editUserParams,
                                     @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request to updateUser {}", editUserParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.equals(editUserParams.getId(), Constants.ADMINISTRATOR_USER_ID)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "不允许修改超级管理员");
            }
            Example example = new Example(SysUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andNotEqualTo("id", editUserParams.getId());
            criteria.andEqualTo("userUsername", editUserParams.getUserUsername());
            if (userService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "用户名已存在");
            }
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(editUserParams, sysUser);
            SysOrg sysOrg = organizationService.findSysOrg(editUserParams.getDeptId());
            sysUser.setOrgId(sysOrg.getId());
            sysUser.setOperatorTime(new Date());
            sysUser.setOperator(user.getId());
            userService.update(sysUser);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping(value = "/userRole")
    @ApiOperation(value = "用户分配角色", notes = "用户分配角色")
    public ResponseResult userRole(@Validated @RequestBody UserRoleParams userRoleParams,
                                   @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request to userRole {}", userRoleParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (userRoleParams.getRoleIds().isEmpty()) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "请选择角色");
            }
            sysUserRoleService.saveUserRole(userRoleParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "分配成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findUserRole")
    @ApiOperation(value = "分页获取数据权限下的所有的启用角色，并标出该用户已选中的角色", notes = "分页获取数据权限下的所有启用的角色，并标出该用户已选中的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<UserRoleModel>> findUserRole(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                @RequestParam @ApiParam(value = "用户ID", required = true) Long id,
                                                                @RequestParam(required = false) @ApiParam("角色名称") String roleName,
                                                                @ApiIgnore PageParam pageParam) {
        logger.debug("Rest request findUserRole by userId {}" , id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(id);
            List<SysUserRole> sysUserRoles = sysUserRoleService.findListByWhere(sysUserRole);
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<SysRole> all = roleService.findAllEnableRoleByAuth(dataAuth, exceptDataAuth, roleName);
            List<UserRoleModel> userRoleModelList = Lists.newArrayList();
            for (SysRole sysRoles : all) {
                UserRoleModel userRoleModel = new UserRoleModel();
                userRoleModel.setRoleId(sysRoles.getId());
                userRoleModel.setRoleName(sysRoles.getRoleName());
                userRoleModel.setRemark(sysRoles.getRoleDesc());
                for (SysUserRole userRole : sysUserRoles) {
                    if (Objects.equals(sysRoles.getId(), userRole.getRoleId())) {
                        userRoleModel.setSelected(true);
                        break;
                    }
                }
                userRoleModelList.add(userRoleModel);
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(userRoleModelList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

    @PutMapping("/resetPassword/{id}")
    @ApiOperation(value = "用户重置密码", notes = "用户重置密码")
    public ResponseResult resetPassword(@PathVariable("id") @ApiParam("用户ID") Long id,
                                        @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request to resetPassword {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysParameter sysParameter = new SysParameter();
            sysParameter.setParamCode(Constants.PERSONAL_DEFAULT_PASSWORD_CODE);
            SysParameter one = sysParameterService.findOne(sysParameter);
            String defaultPwd = Optional.ofNullable(one).map(SysParameter::getParamValue).orElse(Constants.PERSONAL_DEFAULT_PASSWORD);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String userPassword = passwordEncoder.encode(MD5.MD5Encode(defaultPwd));
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setUserPassword(userPassword);
            userService.updateSelective(sysUser);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "密码重置成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping(value = "/findUserByOrgAuth")
    @ApiOperation(value = "根据组织机构分页查询数据权限下的用户", notes = "根据组织机构分页查询数据权限下的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SearchUserModel>> findUserByOrgAuth(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                       @ApiIgnore PageParam pageParam,
                                                                       SearchUserParams searchUserParams) {
        logger.debug("Rest request to findUserByOrg");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<SearchUserModel> userByOrgAuth = userService.findUserByOrgAuth(dataAuth, exceptDataAuth, searchUserParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(userByOrgAuth));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping(value = "/findUserByOrg")
    @ApiOperation(value = "根据组织机构分页查询用户", notes = "根据组织机构分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    @ApiIgnore
    public ResponseResult<PageInfo<SearchUserModel>> findUserByOrg(SearchUserParams searchUserParams,
                                                                   @ApiIgnore PageParam pageParam) {
        logger.debug("Rest request to findUserByOrg");
        try {
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(userService.findUserByOrg(searchUserParams)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/findUserDevice")
    @ApiOperation(value = "获取用户设备", notes = "获取用户设备")
    public ResponseResult<List<UserDeviceModel>> findUserDevice(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                @Validated @RequestBody FindUserDeviceParams findUserDeviceParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<UserDeviceModel> userDevice = sysUserDeviceService.findUserDevice(findUserDeviceParams.getUserIds());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, userDevice);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/updateUserDevice")
    @ApiOperation(value = "单个用户/批量用户设备停用/启用/重置", notes = "用户设备停用/启用/重置")
    public ResponseResult updateUserDevice(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                           @Validated @RequestBody UpdateUserDeviceParams updateUserDeviceParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysUserDevice sysUserDevice = new SysUserDevice();
            sysUserDevice.setOperator(user.getId());
            sysUserDevice.setOperatorTime(new Date());
            if(Objects.equals(updateUserDeviceParams.getType(), 0)) {
                sysUserDevice.setDeviceStatus(updateUserDeviceParams.getDeviceStatus());
            } else if(Objects.equals(updateUserDeviceParams.getType(), 1)) {
                sysUserDevice.setDeviceValidate(updateUserDeviceParams.getDeviceStatus());
            }else if (Objects.equals(updateUserDeviceParams.getType(), 2)) {
                sysUserDevice.setDeviceValidate(Status.Enable.getValue());
                sysUserDevice.setDeviceStatus(Status.Enable.getValue());
                sysUserDevice.setDeviceMac(null);
            } else {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "参数错误");
            }
            sysUserDeviceService.updateBatchUserDevice(sysUserDevice, updateUserDeviceParams.getIds());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping(value = "/userBatchAllocateRoles")
    @ApiOperation(value = "用户批量分配角色", notes = "用户批量分配角色")
    public ResponseResult userBatchAllocateRoles(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                 @RequestBody UserBatchAllocateRolesModel userBatchAllocateRolesModel) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            userService.userBatchAllocateRoles(user, userBatchAllocateRolesModel);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
