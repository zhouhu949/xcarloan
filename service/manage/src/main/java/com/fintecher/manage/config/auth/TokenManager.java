package com.fintecher.manage.config.auth;

import com.fintecher.entity.SysUser;

/**
 * @Author: jwdstef
 * @Description:  对Token进行操作的接口
 * @Date 2017/5/31
 */
public interface TokenManager {

    /**
     * 生成token
     * @param user
     * @return
     */
    String createToken(SysUser user);

    /**
     * token存在则更新时间
     * @param authentication
     * @return
     */
    boolean checkToken(String authentication);

    /**
     * token删除
     * @param authentication
     */
    void deleteToken(String authentication);
}
