package com.fintecher.web;


import com.fintecher.util.Constants;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author : sunyanping
 * @Description :
 * @Date : 2017/6/27.
 */
public class SystemLogUtil {

    private final static String EXCEPTION = "exception";

    public static Map<String, Object> saveSystemLog(JoinPoint joinPoint, String type, Throwable e, ThreadLocal<Long> startTime) throws Exception{
        //sysMap用来存放返回的参数
        Map<String, Object> sysMap = new HashMap<>();
        // 获取到请求的参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取Header中“X-UserToken”的值
        String token = request.getHeader(Constants.AUTHORIZATION);
        if (Objects.isNull(token) || StringUtils.isBlank(token)) {
            return sysMap;
        }
        // 获取到请求地址
        String remoteAddr = getAddr(request);
        // 请求执行时间
        Long exeTime = System.currentTimeMillis() - startTime.get();
        String targetName = joinPoint.getTarget().getClass().getName(); // 获取到切入点名称（类全名）
        String methodName = joinPoint.getSignature().getName(); // 方法名
        Object[] arguments = joinPoint.getArgs(); // 方法参数
        Class targetClass =  Class.forName(targetName);
        Method[] methods = targetClass.getMethods(); // 获取到所有公有方法
        String remark = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) { // 方法名和参数数量相同认为是同一个方法
                    remark = method.getAnnotation(ApiOperation.class).value();
                    break;
                }
            }
        }
        if (EXCEPTION.equals(type)) {
            remark += e.getClass().getName() + e.getMessage();
        }
        String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        sysMap.put("type", type);
        sysMap.put("createTime", new Date());
        sysMap.put("remark", remark);
        sysMap.put("exeTime", exeTime);
        sysMap.put("reqIp", remoteAddr);
        sysMap.put("methods", method);
        sysMap.put("params", Arrays.toString(arguments));
        sysMap.put("token", token);
        return sysMap;
    }
    private static String getAddr(@NotNull HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
            }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
            }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
            }
        return ip;
        }
}
