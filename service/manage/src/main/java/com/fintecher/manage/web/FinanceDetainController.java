package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.FinanceStorage;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerOrderService;
import com.fintecher.manage.service.FinanceStorageService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.*;
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

/**
 * @author ZhangYaJun
 * @Title: FinanceDetainController
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 14:47
 */

@RestController
@RequestMapping("/financeDetainController")
@Api(description = "押品管理")
public class FinanceDetainController extends BaseController {


    @Autowired
    private FinanceStorageService financeStorageService;
    @Autowired
    private UserService userService;
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;

    @GetMapping("/getFinanceStorageList")
    @ApiOperation(value = "出入库记录列表", notes = "出入库记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<FinanceStorageListParams>> getFinanceStorageList(@ApiIgnore PageParam pageParam,
                                                                                    @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                                    @RequestParam(required = false) String orderNo,
                                                                                    @RequestParam(required = false) String customerName,
                                                                                    @RequestParam(required = false) Integer mortgageStatus) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> allDataAuth = userService.findAllDataAuth(user.getId());
            List<String> allExceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<FinanceStorageListParams> financeStorageList = financeStorageService.getFinanceStorageList(user, orderNo, customerName, mortgageStatus,
                    allDataAuth, allExceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(financeStorageList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    @PostMapping("/financePledgeInStorage")
    @ApiOperation(value = "质押入库", notes = "质押入库")
    public ResponseResult financePledgeInStorage(@Validated @RequestBody FinanceInStoragePledgeParams financeInStorageParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceStorage fs = financeStorageService.selectAssessmentStatus(financeInStorageParams.getId());
            if (Objects.isNull(fs.getAssessmentId())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "车辆未评估");
            }
            Integer integer = financeStorageService.updatePoleStatus(financeInStorageParams, user);
            if (integer.intValue() > 0) {
                QueryMortgageTypeParams queryMortgageTypeParams = financeStorageService.queryMortgageType(financeInStorageParams.getId());
                financeStorageService.addPledge(financeInStorageParams, queryMortgageTypeParams, user);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "入库成功");
            }
            return new ResponseResult(ResponseResult.Status.FAILURE, "修改失败");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }

    }


    @PostMapping("/financeMortgageInStorage")
    @ApiOperation(value = "抵押入库", notes = "抵押入库")
    public ResponseResult financeMortgageInStorage(@Validated @RequestBody FinanceInStorageMortgageParams financeInStorageMortgageParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceStorage fs = financeStorageService.selectAssessmentStatus(financeInStorageMortgageParams.getId());
            if (Objects.isNull(fs.getAssessmentId())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "车辆未评估");
            }
            Integer integer = financeStorageService.updateMortStatus(financeInStorageMortgageParams, user);
            if (integer.intValue() > 0) {
                QueryMortgageTypeParams queryMortgageTypeParams = financeStorageService.queryMortgageType(financeInStorageMortgageParams.getId());
                financeStorageService.addMortgage(financeInStorageMortgageParams, queryMortgageTypeParams, user);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "入库成功");
            }
            return new ResponseResult(ResponseResult.Status.FAILURE, "修改失败");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }

    }


    @PostMapping("/financeMortgageOutStorage")
    @ApiOperation(value = "抵押出库", notes = "抵押出库")
    public ResponseResult financeMortgageOutStorage(@Validated @RequestBody FinanceOutStorageMortgageParams financeOutStorageMortgageParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Integer orderStatus = basicCustomerOrderService.selectOrderStatus(financeOutStorageMortgageParams.getOrderId());
            if (BasicCustomerOrder.OrderStatus.FINANCE_SETTLEMENT.getValue().equals(orderStatus) ||
                    BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue().equals(orderStatus))
            {
                financeStorageService.updateOutMortStatus(financeOutStorageMortgageParams, user);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "出库成功");
            } else
            {
                return new ResponseResult(ResponseResult.Status.FAILURE, "贷款订单未结束");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }


    @PostMapping("/financePledgeOutStorage")
    @ApiOperation(value = "质押出库", notes = "质押出库")
    public ResponseResult financePledgeOutStorage(@Validated @RequestBody FinanceOutStorageMortgageParams financeOutStorageMortgageParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Integer orderStatus = basicCustomerOrderService.selectOrderStatus(financeOutStorageMortgageParams.getOrderId());
            if (BasicCustomerOrder.OrderStatus.FINANCE_SETTLEMENT.getValue().equals(orderStatus) ||
                    BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue().equals(orderStatus))
            {
                financeStorageService.updateOutPleStatus(financeOutStorageMortgageParams, user);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "出库成功");
            } else
            {
                return new ResponseResult(ResponseResult.Status.FAILURE, "贷款订单未结束");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }


    @GetMapping("/getDetainPoleRecord/{id}")
    @ApiOperation(value = "查看质押押品记录", notes = "查看质押押品记录")
    public ResponseResult<GetDetainPoleRecord> getDetainPoleRecord(@PathVariable("id") @ApiParam(value = "押品记录ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            GetDetainPoleRecord getDetainPoleRecord = financeStorageService.getDetainPoleRecord(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, getDetainPoleRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }


    @GetMapping("/getDetainMortRecord/{id}")
    @ApiOperation(value = "查看抵押押品记录", notes = "查看抵押押品记录")
    public ResponseResult<GetDetainMortRecord> getDetainMortRecord(@PathVariable("id") @ApiParam(value = "押品记录ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            GetDetainMortRecord getDetainMortRecord = financeStorageService.getDetainMortRecord(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, getDetainMortRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }


    @GetMapping("/getDetainDetails/{id}")
    @ApiOperation(value = "查看押品详情", notes = "查看押品详情")
    public ResponseResult<GetDetainDetails> getDetainDetails(@PathVariable("id") @ApiParam(value = "押品记录ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {

                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            GetDetainDetails getDetainDetails = financeStorageService.getDetainDetails(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, getDetainDetails);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }

}
