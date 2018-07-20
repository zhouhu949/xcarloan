package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.common.vo.manage.UserInfo;
import com.fintecher.entity.*;
import com.fintecher.manage.config.auth.TokenManager;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.UpdatePasswordParams;
import com.fintecher.manage.vo.UserLoginModel;
import com.fintecher.manage.vo.UserLoginParams;
import com.fintecher.util.Constants;
import com.fintecher.util.GetClientIp;
import com.fintecher.util.MD5;
import com.fintecher.util.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@Api(description = "登录")
public class LoginController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private TokenManager tokenManager;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private OrganizationService organizationService;
    @Resource
    private RoleService roleService;
    @Resource
    private SysUserDeviceService sysUserDeviceService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseResult<UserLoginModel> login(@Validated @RequestBody UserLoginParams userLoginParams,
                                                HttpServletRequest request) {
        try {
            SysUser sysUser = userService.findByUserName(userLoginParams.getUserUsername());
            if (Objects.isNull(sysUser)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户不存在");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(userLoginParams.getUserPassword(), sysUser.getUserPassword())) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "密码错误");
            }
            if (!Objects.equals(sysUser.getUserStatus(), SysUser.UserStatus.ENABLE.getValue())) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户已停用");
            }
            // 用户设备 超级管理员不需要验证设备
            String ip = null;
            SysUserDevice one = null;
            if (!Objects.equals(sysUser.getId(), Constants.ADMINISTRATOR_USER_ID)) {
                ip = GetClientIp.getIp(request);
                SysUserDevice sysUserDevice = new SysUserDevice();
                sysUserDevice.setUserId(sysUser.getId());
                sysUserDevice.setDeviceType(userLoginParams.getLoginDevice());
                one = sysUserDeviceService.findOne(sysUserDevice);
                if (Objects.nonNull(one)) {
                    if(Objects.equals(one.getDeviceStatus(), Status.Disable.getValue())) {
                        return new ResponseResult<>(ResponseResult.Status.FAILURE, "该类型的设备已禁用登陆");
                    }
                    if(Objects.equals(one.getDeviceValidate(), Status.Enable.getValue()) && Objects.equals(one.getDeviceType(), SysUserDevice.DeviceType.PC.getValue())) {
                        ip = GetClientIp.getIp(request);
                        if (Objects.nonNull(one.getDeviceMac()) && !Objects.equals(one.getDeviceMac(), ip)) {
                            return new ResponseResult<>(ResponseResult.Status.FAILURE, "本次和上次登陆地址不一致");
                        }
                    }
                }
            }
            List<SysRole> userRoles = roleService.findRoleByUserId(sysUser.getId());
            if(userRoles.isEmpty()) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户无角色");
            }
            List<Long> roleIds = userRoles.stream().map(BaseEntity::getId).collect(Collectors.toList());
            List<SysResource> roleMenuResource = sysRoleResourceService.findRoleMenuResourceByRoleIds(roleIds);
            if (roleMenuResource.isEmpty()) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户无菜单权限");
            }
            List<Map> roleResource = sysRoleResourceService.findRoleResourceByRoleIds(roleIds);
            String token = tokenManager.createToken(sysUser);
            UserLoginModel model = new UserLoginModel();
            model.setToken(token);
            model.setUser(sysUser);
            model.setMenu(roleMenuResource);
            model.setResource(roleResource);
            model.setSysOrg(organizationService.findById(sysUser.getDeptId()));
            model.setRolesList(userRoles);
            model.setReset(userService.userPasswordReset(sysUser));
            // 更新设备登陆MAC
            sysUserDeviceService.updateUserDevice(sysUser, one , ip);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, model);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, "系统错误，请与管理员联系");
        }
    }

    @PostMapping(value = "/devLogin")
    @ApiOperation(value = "开发登录", notes = "用户登录")
    public ResponseResult devLogin(@Validated @RequestBody UserLoginParams userLoginParams) {
        try {
            SysUser sysUser = userService.findByUserName(userLoginParams.getUserUsername());
            if (Objects.isNull(sysUser)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户不存在");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(MD5.MD5Encode(userLoginParams.getUserPassword()), sysUser.getUserPassword())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "密码错误");
            }
            String token = tokenManager.createToken(sysUser);
            UserLoginModel model = new UserLoginModel();
            model.setToken(token);
            model.setUser(sysUser);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, model);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, "系统错误，请与管理员联系");
        }
    }

    @GetMapping("/getUserInfoByToken")
    @ApiOperation(value = "通过token获取用户信息", notes = "通过token获取用户信息")
    public ResponseResult<UserInfo> getUserInfoByToken(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            UserInfo userInfo = userService.getUserInfoByToken(authorization);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, userInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/logout")
    @ApiOperation(value = "注销当前用户登陆状态（退出）", notes = "注销当前用户登陆状态（退出）")
    public ResponseResult logout(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            tokenManager.deleteToken(authorization);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "退出登录成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/updatePassword")
    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    public ResponseResult updatePassword(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                         @Validated @RequestBody UpdatePasswordParams updatePassParam) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(updatePassParam.getOldPassword().trim(), user.getUserPassword())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "原密码错误");
            }
            SysUser newUser = new SysUser();
            newUser.setId(user.getId());
            newUser.setUserPassword(passwordEncoder.encode(updatePassParam.getNewPassword()));
            newUser.setPasswordTime(new Date());
            userService.updateSelective(newUser);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }




}
