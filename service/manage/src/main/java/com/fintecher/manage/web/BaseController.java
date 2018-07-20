package com.fintecher.manage.web;


import com.fintecher.entity.SysUser;
import com.fintecher.manage.config.auth.TokenManager;
import com.fintecher.manage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jwdstef
 * @Description: 基类
 * @Date 2017/5/31
 */
public class BaseController {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;

    protected SysUser getUserByAuth(String authorization) {
        if (tokenManager.checkToken(authorization)) {
            String[] split = authorization.split("_");
            return userService.findById(Long.parseLong(split[0]));
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }

}
