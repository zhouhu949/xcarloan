package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.SysResourceService;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sysResourceController")
@Api(description = "系统资源")
public class SysResourceController extends BaseController {

    @Resource
    private SysResourceService sysResourceService;

    @GetMapping("/getAllSysResource")
    @ApiOperation(value = "获取所有的系统资源", notes = "获取所有的系统资源")
    public ResponseResult<List<SysResource>> getAllSysResource(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, sysResourceService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
