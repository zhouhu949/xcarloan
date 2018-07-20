package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.BasicCustomerBankModel;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 10:38
 * @Description:
 */
@RestController
@RequestMapping("/basicCustomerAccountController")
@Api(description = "客户开户绑卡")
public class BasicCustomerAccountController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private BasicCustomerService basicCustomerService;
    /**
     * @auther: dwx
     * @Description:客户开户绑卡
     */
    @PostMapping("/customerOpenAccount")
    @ApiOperation(value = "客户开户绑卡",notes = "客户开户绑卡")
    public ResponseResult saveCustomerAccount(@RequestBody BasicCustomerBankModel basicCustomerBankModel,
                                              @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerService.saveCustomerAccount(basicCustomerBankModel, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "开户成功");
        } catch (RuntimeException r) {
            logger.error(r.getMessage(), r);
            return new ResponseResult(ResponseResult.Status.FAILURE, r.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

}
