package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysColumnResource;
import com.fintecher.entity.SysUser;
import com.fintecher.entity.SysUserColumn;
import com.fintecher.manage.service.SysColumnResourceService;
import com.fintecher.manage.service.SysUserColumnService;
import com.fintecher.manage.vo.UpdateUserColumnParams;
import com.fintecher.manage.vo.UserColumnParams;
import com.fintecher.util.Constants;
import com.fintecher.util.Status;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sysUserColumnController")
@Api(description = "系统用户列配置操作")
public class SysUserColumnController extends BaseController {

    @Resource
    private SysUserColumnService sysUserColumnService;
    @Resource
    private SysColumnResourceService sysColumnResourceService;

    @GetMapping("/findUserColumnByPid/{pid}")
    @ApiOperation(value = "获取页面下用户所有列配置", notes = "获取页面下用户所有列配置")
    public ResponseResult<List<SysUserColumn>> findUserColumnByPid(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                   @PathVariable("pid") @ApiParam("当前页面ID") Long pid) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysUserColumn sysUserColumn = new SysUserColumn();
            sysUserColumn.setColumnPid(pid);
            sysUserColumn.setUserId(user.getId());
            List<SysUserColumn> listByWhere = sysUserColumnService.findListByWhere(sysUserColumn);
            if (!listByWhere.isEmpty()) {
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, listByWhere);
            }
            SysColumnResource sysColumnResource = new SysColumnResource();
            sysColumnResource.setResourcePid(pid);
            List<SysColumnResource> sysColumnResources = sysColumnResourceService.findListByWhere(sysColumnResource);
            List<SysUserColumn> sysUserColumnList = Lists.newArrayList();
            for (SysColumnResource resource : sysColumnResources) {
                SysUserColumn column = new SysUserColumn();
                column.setUserId(user.getId());
                column.setOperatorTime(new Date());
                column.setOperator(user.getId());
                column.setColumnSort(resource.getResourceSort());
                column.setColumnCheck(Status.Enable.getValue());
                column.setColumnPid(resource.getResourcePid());
                column.setColumnId(resource.getId());
                column.setColumnCode(resource.getResourceCode());
                sysUserColumnList.add(column);
            }
            sysUserColumnService.saveList(sysUserColumnList);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, sysUserColumnList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/updateUserColumn")
    @ApiOperation(value = "修改页面下用户列配置", notes = "修改页面下用户列配置")
    public ResponseResult updateUserColumn(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                           @Validated @RequestBody UpdateUserColumnParams updateUserColumnParams) {
        logger.debug("Rest request updateUserColumn {}", updateUserColumnParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<UserColumnParams> userColumnList = updateUserColumnParams.getUserColumnList();
            List<SysUserColumn> sysUserColumnList = Lists.newArrayList();
            for (UserColumnParams userColumnParams : userColumnList) {
                SysUserColumn sysUserColumn = new SysUserColumn();
                BeanUtils.copyProperties(userColumnParams, sysUserColumn);
                sysUserColumn.setOperator(user.getId());
                sysUserColumn.setOperatorTime(new Date());
                sysUserColumnList.add(sysUserColumn);
            }
            sysUserColumnService.updateBatchUserColumn(sysUserColumnList);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修该成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
