package com.fintecher.manage.config.auth;

import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.UserMapper;
import com.fintecher.util.Constants;
import com.fintecher.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author: jwdstef
 * @Description: 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @Date 2017/5/31
 */
@Component("currentUserMethodArgumentResolver")
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(SysUser.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        //取出鉴权时存入的登录用户Id
        Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            //从数据库中查询并返回
            return userMapper.selectByPrimaryKey(currentUserId);
        }
        //throw new TokenException(Constant.CURRENT_USER_ID);
        return userMapper.selectByPrimaryKey(1l);
    }
}
