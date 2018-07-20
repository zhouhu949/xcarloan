package com.fintecher.manage.service.impl;

import com.fintecher.common.vo.manage.UserInfo;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.SysUserRoleMapper;
import com.fintecher.manage.mapper.UserMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.MD5;
import com.fintecher.util.Status;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysUserDeviceService sysUserDeviceService;
    @Resource
    private SysParameterService sysParameterService;
    @Resource
    private OrganizationService organizationService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    /**
     * 用户管理页面的查询列表
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SearchUserModel> findUserByOrg(SearchUserParams searchUserParams) {
        return userMapper.findUserByOrg(searchUserParams);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public SysUser findByUserName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUserUsername(username);
        return userMapper.selectOne(sysUser);
    }

    @Override
    public SysUser getUserByToken(String authorization) {
        if (StringUtils.isBlank(authorization)) {
            return null;
        }
        String[] split = authorization.split("_");
        Long userId = Long.valueOf(split[0]);
        return Optional.of(userId).map(e -> userMapper.selectByPrimaryKey(userId)).orElse(null);
    }

    @Override
    public List<String> findAllDataAuth(Long userId) {
        return userMapper.findAllDataAuth(userId);
    }

    @Override
    public List<String> findAllExceptDataAuth(Long userId) {
        return userMapper.findAllExceptDataAuth(userId);
    }

    @Override
    public UserInfo getUserInfoByToken(String authorization) {
        UserInfo userInfo = new UserInfo();
        SysUser sysUser = getUserByToken(authorization);
        if (Objects.isNull(sysUser)) {
            return null;
        }
        // 用户
        userInfo.setUser(sysUser);
        // token
        userInfo.setToken(authorization);
        // 用户角色
        List<SysRole> roleByUserId = userMapper.findRoleByUserId(sysUser.getId());
        userInfo.setRolesList(roleByUserId);
        // 菜单
        List<Long> roleIds = roleByUserId.stream().map(BaseEntity::getId).collect(Collectors.toList());
        userInfo.setMenu(sysRoleResourceService.findRoleMenuResourceByRoleIds(roleIds));
        // 资源
        userInfo.setResource(sysRoleResourceService.findRoleResourceByRoleIds(roleIds));
        return userInfo;
    }

    @Override
    public List<SearchUserModel> findUserByOrgAuth(List<String> dataAuth, List<String> exceptDataAuth, SearchUserParams searchUserParams) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        paramsMap.put("params", searchUserParams);
        return userMapper.findUserByOrgAuth(paramsMap);
    }

    @Override
    public void addUser(SysUser user, UserParams userParams) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userParams, sysUser);
        // 若系统参数配置了默认密码 则使用  若没有配置 则默认888888
        SysParameter sysParameter = new SysParameter();
        sysParameter.setParamCode(Constants.PERSONAL_DEFAULT_PASSWORD_CODE);
        SysParameter one = sysParameterService.findOne(sysParameter);
        String defaultPwd = Optional.ofNullable(one).map(SysParameter::getParamValue).orElse(Constants.PERSONAL_DEFAULT_PASSWORD);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setUserPassword(passwordEncoder.encode(MD5.MD5Encode(defaultPwd)));

        SysParameter parameter = new SysParameter();
        parameter.setParamCode(Constants.PROJECT_DEFAULT_ENV_CODE);
        SysParameter parameter1 = sysParameterService.findOne(parameter);
        String paramValue = parameter1.getParamValue();
        if (StringUtils.equals(paramValue, String.valueOf(Constants.ProjectEnv.PROJECT.getValue()))) {
            sysUser.setOrgId(Constants.ADMINISTRATOR_DEPARTMENT_ID);
        } else {
            SysOrg sysOrg = organizationService.findSysOrg(userParams.getDeptId());
            sysUser.setOrgId(sysOrg.getId());
        }
        sysUser.setOperator(user.getId());
        sysUser.setOperatorTime(new Date());
        sysUser.setPasswordTime(new Date());
        this.save(sysUser);
        // 添加设备锁
        addUserDevice(sysUser.getUserUsername(), user);
    }

    private void addUserDevice(String userName, SysUser sysUser) {
        SysUser u = new SysUser();
        u.setUserUsername(userName);
        SysUser serviceOne = this.findOne(u);
        List<SysUserDevice> sysUserDeviceList = Lists.newArrayList();
        SysUserDevice.DeviceType[] values = SysUserDevice.DeviceType.values();
        for (SysUserDevice.DeviceType type : values) {
            SysUserDevice sysUserDevice = new SysUserDevice();
            sysUserDevice.setUserId(serviceOne.getId());
            sysUserDevice.setDeviceType(type.getValue());
            sysUserDevice.setDeviceStatus(Status.Enable.getValue());
            sysUserDevice.setDeviceValidate(Status.Enable.getValue());
            sysUserDevice.setOperator(sysUser.getId());
            sysUserDevice.setOperatorTime(new Date());
            sysUserDeviceList.add(sysUserDevice);
        }
        sysUserDeviceService.saveList(sysUserDeviceList);
    }

    @Override
    public boolean userPasswordReset(SysUser user) {
        // 用户密码时间为空则默认设置当前时间
        if (Objects.isNull(user.getPasswordTime())) {
            SysUser sysUser = new SysUser();
            sysUser.setPasswordTime(new Date());
            sysUser.setOperatorTime(new Date());
            sysUser.setId(user.getId());
            this.updateSelective(sysUser);
            return false;
        }
        SysParameter sysParameter = new SysParameter();
        sysParameter.setParamCode(Constants.PASSWORD_OBSOLETE_DAYS_CODE);
        sysParameter.setParamStatus(Status.Enable.getValue());
        SysParameter one = sysParameterService.findOne(sysParameter);
        // 若系统参数配置了则使用，若没有配置使用默认的30天
        int days = Objects.isNull(one) ? Constants.PASSWORD_DEFAULT_DAYS : Integer.parseInt(one.getParamValue());
        Instant instant = Instant.now();
        Instant passwordTimeInstant = user.getPasswordTime().toInstant();
        return Duration.between(instant, passwordTimeInstant).toDays() > days;
    }

    @Override
    public void userBatchAllocateRoles(SysUser user, UserBatchAllocateRolesModel userBatchAllocateRolesModel) {
        List<Long> roleIds = userBatchAllocateRolesModel.getRoleIds();
        List<Long> userIds = userBatchAllocateRolesModel.getUserIds();
        List<SysUserRole> userRoleList = Lists.newArrayList();
        for (Long userId : userIds) {
            for (Long roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setOperator(user.getId());
                sysUserRole.setOperatorTime(new Date());
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                userRoleList.add(sysUserRole);
            }
        }
        sysUserRoleMapper.deleteUserRoleByUserIds(userIds);
        sysUserRoleMapper.insertList(userRoleList);
    }

    @Override
    public List<WFUserModel> queryAllUser(WFUserParams wfUserParams) {
        return userMapper.queryAllUser(wfUserParams);
    }

}
