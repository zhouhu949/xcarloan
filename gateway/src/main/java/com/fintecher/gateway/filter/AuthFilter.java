package com.fintecher.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jwdstef
 * @Description: 权限验证过滤器
 * @Date 2017/12/14
 */
public class AuthFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    /**
     * 过滤器类型
     * pre 事前
     * routing 路由请求时候调用
     * error 发生错误时候调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否过来
     * 0 不过滤
     * 1 过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 拦截的具体操作
     * 验证token
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        if (StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "fileUploadController/view")
                || StringUtils.containsIgnoreCase(request.getRequestURI(), "fileUploadController/uploadFileGrid")) {
            return null;
        }
        if (StringUtils.contains(request.getRequestURL().toString(), "login/login")
                || StringUtils.contains(request.getRequestURL().toString(), "login/devLogin")
                || StringUtils.contains(request.getRequestURL().toString(), "swagger")
                || StringUtils.contains(request.getRequestURL().toString(), "api-docs")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "HashCode")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "sysDictController")) {
            return null;
        }

        // 放开工作流调用的接口
        if (StringUtils.contains(request.getRequestURI(), "/wf/workFlowResource")) {
            return null;
        }

        //从header中得到token
        String token = request.getHeader("authorization");
        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity("http://service-manage/loginResource/checkToken?token=" + token, Boolean.class);
            if (!responseEntity.hasBody() || !responseEntity.getBody()) {
                //认证失败
                logger.error("token验证失败");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
        } catch (RuntimeException e) {
            logger.error("token验证失败");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        logger.debug("token验证成功");
        return null;
    }
}
