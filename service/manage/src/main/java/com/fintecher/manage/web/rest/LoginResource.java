package com.fintecher.manage.web.rest;

import com.fintecher.entity.SysUser;
import com.fintecher.manage.config.auth.TokenManager;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.web.BaseController;
import com.fintecher.util.Constants;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@RestController
@RequestMapping("/loginResource")
public class LoginResource extends BaseController {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;

    @GetMapping("/checkToken")
    @ApiOperation(value = "token验证")
    public Boolean checkToken(@RequestParam String token, HttpServletRequest request) {
        if (!StringUtils.isBlank(token)) {
            if (tokenManager.checkToken(token)) {
                SysUser user = userService.getUserByToken(token);
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_ID, user.getId());
                //生成一个token，保存用户登录状态
                return true;
            }
        }
        return false;
    }
}
