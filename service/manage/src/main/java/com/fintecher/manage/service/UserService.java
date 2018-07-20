package com.fintecher.manage.service;


import com.fintecher.common.vo.manage.UserInfo;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.util.List;

public interface UserService extends BaseService<SysUser> {

    /**
     * 查询部门下所有用户
     * @param searchUserParams
     * @return
     */
    List<SearchUserModel> findUserByOrg(SearchUserParams searchUserParams);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

    /**
     * 根据authorization获取用户
     * @param authorization userId_token
     * @return
     */
    SysUser getUserByToken(String authorization);

    /**
     * 获取用户所有的数据权限
     * @param userId 用户ID
     * @return 该用户所拥有的机构数据权限机构ID，树形编码集合
     */
    List<String> findAllDataAuth(Long userId);

    /**
     * 获取用户所有排除的数据权限
     * @param userId 用户ID
     * @return 该用户所排除的数据权限机构树形编码集合
     */
    List<String> findAllExceptDataAuth(Long userId);

    /**
     * 根据token获取用户信息
     * @param authorization
     * @return
     */
    UserInfo getUserInfoByToken(String authorization);

    /**
     * 数据权限下用户查询
     * @param dataAuth
     * @param exceptDataAuth
     * @param searchUserParams
     * @return
     */
    List<SearchUserModel> findUserByOrgAuth(List<String> dataAuth, List<String> exceptDataAuth, SearchUserParams searchUserParams);

    /**
     * 增加用户
     * @param user
     * @param userParams
     */
    void addUser(SysUser user, UserParams userParams);

    /**
     * 密码过期提醒
     * @param user
     * @return
     */
    boolean userPasswordReset(SysUser user);

    /**
     * 用户批量分配角色
     *
     * @param user
     * @param userBatchAllocateRolesModel
     */
    void userBatchAllocateRoles(SysUser user, UserBatchAllocateRolesModel userBatchAllocateRolesModel);

    /**
     * 工作流获取所有的用户
     *
     * @param wfUserParams
     * @return
     */
    List<WFUserModel> queryAllUser(WFUserParams wfUserParams);



}
