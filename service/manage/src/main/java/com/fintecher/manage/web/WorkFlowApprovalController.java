package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.FinanceStorage;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerOrderService;
import com.fintecher.manage.service.FinanceStorageService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.service.WorkFlowApprovalService;
import com.fintecher.manage.vo.ApprovalOrderModel;
import com.fintecher.manage.vo.OrderApprovalParams;
import com.fintecher.manage.vo.PageParam;
import com.fintecher.manage.vo.SearchWaitApproveParams;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workFlowApprovalController")
@Api(description = "工作流审批操作")
public class WorkFlowApprovalController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkFlowApprovalService workFlowApprovalService;
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    private FinanceStorageService financeStorageService;

    @PostMapping("/submitApproval/{orderId}")
    @ApiOperation(value = "订单提交审核", notes = "订单提交审核")
    public ResponseResult submitApproval(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                         @PathVariable("orderId") @ApiParam("订单ID") Long orderId) {
        logger.debug("Rest request submitApproval {}", orderId);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
            if (Objects.equals(order.getOrderType(), BasicCustomerOrder.OrderType.MORTGAGE.getValue())) {
                FinanceStorage financeStorage = new FinanceStorage();
                financeStorage.setOrderId(orderId);
                List<FinanceStorage> storageList = financeStorageService.findListByWhere(financeStorage);
                if (storageList.stream().anyMatch(e -> Objects.isNull(e.getAssessmentId()))) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "请先对抵押的所有车辆进行评估");
                }
            }
            workFlowApprovalService.submitApproval(orderId, user.getId());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "提交成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/approval")
    @ApiOperation(value = "订单审核", notes = "订单审核")
    public ResponseResult approval(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                   @RequestBody @Validated OrderApprovalParams approvalParams) {
        logger.debug("Rest request approval {}", approvalParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            workFlowApprovalService.approval(approvalParams, user.getId());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "审核成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryAllWaitApprovalByAuth")
    @ApiOperation(value = "分页查询所有数据权限下待审核的订单", notes = "分页查询所有数据权限下待审核的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<ApprovalOrderModel>> queryAllWaitApprovalByAuth(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                                   @ApiIgnore PageParam pageParam,
                                                                                   SearchWaitApproveParams searchWaitApproveParams) {
        logger.debug("Rest request queryAllWaitApprovalByAuth {}", searchWaitApproveParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<ApprovalOrderModel> orderModels = workFlowApprovalService.queryAllWaitApprovalByAuth(dataAuth, exceptDataAuth, searchWaitApproveParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(orderModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryWaitApproval")
    @ApiOperation(value = "分页查询所有待当前登录人审核的订单", notes = "分页查询所有待当前登录人审核的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<ApprovalOrderModel>> queryWaitApproval(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                          @ApiIgnore PageParam pageParam,
                                                                          SearchWaitApproveParams searchWaitApproveParams) {
        logger.debug("Rest request queryWaitApproval {}", searchWaitApproveParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<ApprovalOrderModel> approvalOrderModels = workFlowApprovalService.queryWaitApproval(user, searchWaitApproveParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(approvalOrderModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
