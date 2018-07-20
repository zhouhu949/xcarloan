package com.fintecher.manage.web.rest;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.manage.service.OrganizationService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.WFOrgModel;
import com.fintecher.manage.vo.WFOrgParams;
import com.fintecher.manage.vo.WFUserModel;
import com.fintecher.manage.vo.WFUserParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wf/workFlowResource")
@Api(description = "为工作流提供系统接口")
public class WorkFlowResource {

    private final Logger logger = LoggerFactory.getLogger(WorkFlowResource.class);

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/queryAllUser")
    @ApiOperation(value = "工作流查询所有的用户", notes = "工作流查询所有的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "limit", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true)
    })
    public ResponseResult<PageInfo<WFUserModel>> queryAllUser(WFUserParams wfUserParams) {
        logger.info("WF request queryAllUser {}", wfUserParams);
        try {
            PageHelper.startPage(wfUserParams.getPage(), wfUserParams.getLimit());
            List<WFUserModel> wfUserModels = userService.queryAllUser(wfUserParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(wfUserModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping(value = "/queryAllOrg")
    @ApiOperation(value = "工作流查询所有的部门", notes = "工作流查询所有的部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "limit", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true)
    })
    public ResponseResult<PageInfo<WFOrgModel>> queryAllOrg(WFOrgParams wfOrgParams) {
        logger.info("WF request queryAllOrg");
        try {
            PageHelper.startPage(wfOrgParams.getPage(), wfOrgParams.getLimit());
            List<WFOrgModel> wfOrgModels = organizationService.queryAllOrg(wfOrgParams.getOrgName(), wfOrgParams.getOrgStatus());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(wfOrgModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
