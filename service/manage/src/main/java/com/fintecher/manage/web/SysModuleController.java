package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysResource;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.SysResourceService;
import com.fintecher.manage.service.SysRoleResourceService;
import com.fintecher.manage.service.SysUserRoleService;
import com.fintecher.manage.vo.EditResourceIconParams;
import com.fintecher.manage.vo.EditResourceNameParams;
import com.fintecher.manage.vo.PageParam;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/sysModuleController")
@Api(description = "系统模块功能")
public class SysModuleController extends BaseController {

    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysResourceService sysResourceService;

    @GetMapping("/getRoleMenu")
    @ApiOperation(value = "获取角色下的所有的菜单", notes = "获取角色下的所有的菜单")
    public ResponseResult<List<SysResource>> getRoleMenu(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Long> userRoleIds = sysUserRoleService.findUserRoleIds(user.getId());
            List<SysResource> menuResourceByRoleIds = sysRoleResourceService.findRoleMenuResourceByRoleIds(userRoleIds);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, menuResourceByRoleIds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @GetMapping("/findMenuByRoleId/{id}")
    @ApiOperation(value = "根据角色获取菜单", notes = "根据角色获取菜单")
    public ResponseResult<List<SysResource>> findMenuByRoleId(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @PathVariable("id") @ApiParam("roleID") Long id) {
        logger.debug("Rest request findRoleMenu");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Long> userRoleIds = new ArrayList<>();
            userRoleIds.add(id);
            List<SysResource> menuResourceByRoleIds = sysRoleResourceService.findRoleMenuResourceByRoleIds(userRoleIds);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, menuResourceByRoleIds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @GetMapping("/findResourceByRoleId/{id}")
    @ApiOperation(value = "根据角色获取资源", notes = "根据角色获取资源")
    public ResponseResult findResourceByRoleId(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @PathVariable("id") @ApiParam("roleID") Long id) {
        logger.debug("Rest request findRoleMenu");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Long> userRoleIds = new ArrayList<>();
            userRoleIds.add(id);
            List<Map> menuResourceByRoleIds = sysRoleResourceService.findRoleResourceByRoleIds(userRoleIds);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, menuResourceByRoleIds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findChildMenu/{pid}")
    @ApiOperation(value = "获取菜单下的子菜单", notes = "获取菜单下的子菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SysResource>> findChildMenu(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                               @ApiIgnore PageParam pageParam,
                                                               @PathVariable("pid") @ApiParam("父菜单的ID") Long pid) {
        logger.debug("Rest request findChildMenu");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysResource sysResource = new SysResource();
            sysResource.setResourcePid(pid);
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<SysResource> sysResourceList = sysResourceService.findListByWhere(sysResource);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(sysResourceList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/editResourceName")
    @ApiOperation(value = "修改资源名称", notes = "修改资源名称")
    public ResponseResult editResourceName(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                           @Validated @RequestBody EditResourceNameParams editResourceParams) {
        logger.debug("Rest request editResourceName {}", editResourceParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysResource sysResource = new SysResource();
            sysResource.setId(editResourceParams.getResourceId());
            sysResource.setResourceName(editResourceParams.getResourceName());
            sysResourceService.updateSelective(sysResource);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/editResourceIcon")
    @ApiOperation(value = "修改资源图标", notes = "修改资源图标")
    public ResponseResult editResourceIcon(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                           @Validated @RequestBody EditResourceIconParams editResourceParams) {
        logger.debug("Rest request editResourceIcon {}", editResourceParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysResource sysResource = new SysResource();
            sysResource.setId(editResourceParams.getResourceId());
            sysResource.setResourceIcoUrl(editResourceParams.getResourceIcon());
            sysResourceService.updateSelective(sysResource);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/getAllControlResource")
    @ApiOperation(value = "获取所有的非菜单系统资源", notes = "获取所有的非菜单系统资源")
    public ResponseResult<List<SysResource>> getAllControlResource(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Long> userRoleIds = sysUserRoleService.findUserRoleIds(user.getId());
            List list =  sysRoleResourceService.findRoleResourceByRoleIds(userRoleIds);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
