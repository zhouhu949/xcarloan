package com.fintecher.manage.mapper;

import com.fintecher.entity.SysRole;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.SearchUserModel;
import com.fintecher.manage.vo.SearchUserParams;
import com.fintecher.manage.vo.WFUserModel;
import com.fintecher.manage.vo.WFUserParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends MyMapper<SysUser> {
    /**
     * 查询机构下的用户
     */
    List<SysUser> findDeptAllUsers(@Param("deptCode") String deptCode);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findByUserName(@Param("username") String username);

    List<SearchUserModel> findUserByOrg(SearchUserParams searchUserParams);

    List<String> findAllDataAuth(@Param("userId") Long userId);

    List<String> findAllExceptDataAuth(@Param("userId") Long userId);

    List<SysRole> findRoleByUserId(@Param("userId") Long userId);

    List<SearchUserModel> findUserByOrgAuth(Map params);

    /**
     * 工作流查询所有用户
     *
     * @param wfUserParams
     * @return
     */
    List<WFUserModel> queryAllUser(WFUserParams wfUserParams);

}
