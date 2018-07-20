package com.fintecher.manage.aop;

import com.fintecher.entity.SysLogs;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.SysLogsService;
import com.fintecher.manage.service.UserService;
import com.fintecher.util.ZWDateUtil;
import com.fintecher.web.SystemLogUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: sunyanping
 * @Description: 系统日志
 * @Date 2017/6/23
 */
@Aspect
@Order(1)
@Component
public class SystemLogAop {

    private final Logger log = LoggerFactory.getLogger(SystemLogAop.class);

    private final static String AFTER = "AFTER";
    private final static String EXCEPTION = "exception";

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Resource
    private UserService userService;
    @Resource
    private SysLogsService systemLogsService;
    @Resource
    private HttpServletRequest request; //这里可以获取到request

    // 切入点*Controller  com.fintecher.manage.rest
    @Pointcut("execution(public * com.fintecher.manage.web.*Controller.*(..))")
    public void systemLogAop() {
    }

    @Before("systemLogAop()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
    }

    @After("systemLogAop()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        saveSystemLogsBlock(joinPoint, AFTER, null, startTime);
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "systemLogAop()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        saveSystemLogsBlock(joinPoint, EXCEPTION, e, startTime);
    }

    private void saveSystemLogsBlock(JoinPoint joinPoint, String type, Throwable e, ThreadLocal<Long> startTime) {
        try {
            Map<String, Object> map = SystemLogUtil.saveSystemLog(joinPoint, type, null, startTime);
            if (map.isEmpty()) {
                return;
            }
            Object[] args = joinPoint.getArgs();
            SysLogs systemLogs = new SysLogs();
            systemLogs.setClientIp((String) map.get("reqIp"));
            systemLogs.setExcuteTime((Long) map.get("exeTime"));
            systemLogs.setExcuteMethod((String) map.get("methods"));
            systemLogs.setExcuteType(request.getMethod());
            systemLogs.setOperateTime(ZWDateUtil.getNowDateTime());
            systemLogs.setLogRemark(getMethodDescription(joinPoint));
            systemLogs.setExcuteParams(Arrays.toString(args));
            String token = (String) map.get("token");
            String arr[] = token.split("_");
            Long userId = Long.parseLong(arr[0]);
            SysUser user = userService.findById(userId);
            if (Objects.nonNull(user)) {
                systemLogs.setOperator(user.getUserRealname());
            } else {
                systemLogs.setOperator("用户获取失败");
            }
            systemLogsService.save(systemLogs);
        } catch (Exception e1) {
            e1.printStackTrace();
            log.debug(e1.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String description = "";
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation operationLog = method.getAnnotation(ApiOperation.class);
        if (null != operationLog) {
            description = operationLog.value();
        }
        return description;
    }
}
