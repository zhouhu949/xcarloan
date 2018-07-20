package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/financialQueryController")
@Api(description = "财务相关查询")
public class FinancialQueryController extends BaseController {
    @Resource
    private BasicOrderRepaySchemeService repaySchemeService;
    @Autowired
    private FinanceReceivableService financeReceivableService;
    @Autowired
    private BasicStockCarService basicStockCarService;
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    private FinanceLoanService financeLoanService;
    @Autowired
    private FinanceSettleService financeSettleService;
    @Autowired
    private FinanceRefundService refundService;
    @Autowired
    private BasicOrderRepaySchemeService basicOrderRepaySchemeService;
    @Autowired
    private UserService userService;

    @Autowired
    FinanceRefundService financeRefundService;

    @GetMapping("/queryFinancialInvoice")
    @ApiOperation(value = "财务开票查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<FinancialInvoiceModel>> queryFinancialInvoice(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @ApiIgnore PageParam pageParam, FinancialInvoiceParams financialInvoice) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<FinancialInvoiceModel> financialInvoices = financeReceivableService.selectFinancialInvoice(financialInvoice, dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(financialInvoices));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findRepayOrderList")
    @ApiOperation(value = "查询客户还款中的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<OrderInfoModel>> findRepayOrder(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @ApiIgnore PageParam pageParam, SearchOrderParams searchOrderParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderInfoModel> order = repaySchemeService.queryByStatus(user, searchOrderParams, dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(order));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findRepayOrderUnpaidList")
    @ApiOperation(value = "查询待放款的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<OrderInfoModel>> findRepayOrderUnpaidList(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @ApiIgnore PageParam pageParam, SearchOrderParams searchOrderParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderInfoModel> order = repaySchemeService.queryOrderByStatus(user, searchOrderParams, dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(order));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findOrderRepayScheme/{orderId}")
    @ApiOperation(value = "根据订单Id查询还款详情")
    public ResponseResult findOrderRepayScheme(
            @PathVariable("orderId") @ApiParam(value = "订单Id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Map> map = financeSettleService.selectOrderRepaySchemeInfo(orderId);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicOrderCarList")
    @ApiOperation(value = "查询供应商待放款列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<BasicStockCar> findBasicOrderCarList(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @ApiIgnore PageParam pageParam, OrederId supplier) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicStockCar bsc = new BasicStockCar();
            bsc.setHasSupplierLoan(BasicStockCar.HasSupplierLoan.BASIC_STOCKSTATUS_NO.getValue());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            if (Objects.nonNull(supplier)) {
                bsc.setSupplierId(supplier.getSupplier());
            }
            bsc.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_DCG.getValue());
            List<BasicStockCar> list = basicStockCarService.selectBySupplier(bsc);
            PageInfo<BasicStockCar> nlist = new PageInfo<>(list);
            return new ResponseResult(ResponseResult.Status.SUCCESS, nlist);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBalanceInfo/{orderId}")
    @ApiOperation(value = "提前收回或提前结清查询")
    public ResponseResult<PrepaymentModel> findBalanceInfo(
            @PathVariable("orderId") @ApiParam(value = "订单Id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
            if (Objects.isNull(order)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "订单不存在");
            } else {
                List<PrepaymentModel> list = repaySchemeService.selectDetails(orderId);
                if (list == null) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "订单没还款方案");
                } else {
                    return new ResponseResult(ResponseResult.Status.SUCCESS, repaySchemeService.selectDetails(orderId));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryFinancialRefundOrder")
    @ApiOperation(value = "查询退款订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<OrderInfoModel>> queryFinancialRefundOrder(
            @ApiIgnore PageParam pageParam, SearchOrderParams searchOrderParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderInfoModel> orderList = repaySchemeService.selectRefundOrder(searchOrderParams, dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(orderList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryFinancialRefund/{orderId}")
    @ApiOperation(value = "查询退款详情")
    public ResponseResult queryFinancialRefund(
            @PathVariable("orderId") @ApiParam(value = "订单Id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, refundService.selectRefundExpense(orderId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findGathering")
    @ApiOperation(value = "收款查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", dataType = "Long", paramType = "query",
                    value = "订单Id"),
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<ReceiptOrder>> findGathering(
            @ApiIgnore PageParam pageParam,
            SearchOrderParams searchOrderParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<ReceiptOrder> list1 = repaySchemeService.selectReceiptOrder(searchOrderParams);
            PageInfo<ReceiptOrder> list = new PageInfo<>(list1);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/okReceipt/{orderId}")
    @ApiOperation(value = "收款详情")
    public ResponseResult<GatheringModel> okReceipt(
            @PathVariable(value = "orderId") @ApiParam(value = "订单id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<GatheringModel> list = repaySchemeService.getGatheringModel(orderId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/selectPeriods/{orderId}")
    @ApiOperation(value = "查询订单当前还款期期数")
    public ResponseResult selectPeriods(
            @PathVariable(value = "orderId") @ApiParam(value = "订单id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Integer integer = repaySchemeService.selectCurrentPeriods(orderId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, integer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/selectRepayInfo/{orderId}")
    @ApiOperation(value = "查询当前期的还款详情")
    public ResponseResult<BasicOrderRepayScheme> selectRepayInfo(
            @PathVariable("orderId") @ApiParam(value = "订单id", required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Integer periods = repaySchemeService.selectCurrentPeriods(orderId);
            List<BasicOrderRepayScheme> list = repaySchemeService.selectRepayInfo(orderId, periods);
            Map map = new HashMap();
            map.put("details", list);
            Map queryMap = new HashMap();
            queryMap.put("orderId", orderId);
            queryMap.put("periods", periods);
            BigDecimal bigDecimal = basicOrderRepaySchemeService.selectRepayInfoMoney(queryMap);
            map.put("totalMoney", bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));

            return new ResponseResult(ResponseResult.Status.SUCCESS, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/supplierLoanRecord")
    @ApiOperation(value = "供应商开票查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", dataType = "Long", paramType = "query",
                    value = "供应商Id"),
            @ApiImplicitParam(name = "hasInvoice", dataType = "integer", paramType = "query",
                    value = "是否开票"),
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SupplierLoanRecordModel>> supplierLoanRecord(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            Long supplierId, Integer hasInvoice) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SupplierLoanRecordModel> financeLoanList = financeLoanService.querySupplierLoan(supplierId, hasInvoice, user);
            PageInfo<SupplierLoanRecordModel> list = new PageInfo<>(financeLoanList);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryRepayOrderList")
    @ApiOperation(value = "查询待还款的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<OrderModel>> queryRepayOrderList(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @ApiIgnore PageParam pageParam, SearchOrderParams searchOrderParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderModel> order = repaySchemeService.queryByReplayMoney(searchOrderParams, dataAuth, exceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(order));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryFinancialRefunds/{orderId}")
    @ApiOperation(value = "财务退款记录查询")
    public ResponseResult<FinanceRefundModel> queryFinancialRefunds(
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
            @PathVariable("orderId") @ApiParam(value = "订单id",required = true) Long orderId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> dataAuth = userService.findAllDataAuth(user.getId());
            List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            List<FinanceRefundModel> list = financeRefundService.queryFinancialRefund(orderId,dataAuth, exceptDataAuth);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
}
