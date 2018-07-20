package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.ClientRepaymentParams;
import com.fintecher.manage.vo.EarlyRecycleParams;
import com.fintecher.manage.vo.SupplierFinanceTicketModel;
import com.fintecher.util.Constants;
import com.fintecher.util.Status;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/financialManagementController")
@Api(description = "财务管理")
public class FinancialManagementController extends BaseController {
    @Resource
    BasicOrderRepaySchemeService repaySchemeService;
    @Autowired
    FinanceStorageService financeStorageService;
    @Autowired
    FinanceReceivableService financeReceivableService;
    @Autowired
    BasicOrderCarService basicOrderCarService;
    @Autowired
    FinanceLoanService financeLoanService;
    @Autowired
    BasicOrderRecordService basicOrderRecordService;
    @Autowired
    FinanceTakebackService financeTakebackService;
    @Autowired
    FinanceSettleService financeSettleService;
    @Autowired
    FinanceRefundService refundService;
    @Autowired
    BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    BasicCustomerBankService basicCustomerBankService;
    @Autowired
    BasicStockCarService basicStockCarService;
    @Autowired
    BasicCustomerService basicCustomerService;
    @Autowired
    BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Autowired
    BasicRepaySchemeService basicRepaySchemeService;
    @PostMapping("/saveCustomerRepayment")
    @ApiOperation(value = "客户还款")
    public ResponseResult saveCustomerRepayment(@Validated @RequestBody ClientRepaymentParams clientRepaymentModel,
                                                @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BigDecimal repayMoney = (repaySchemeService.selectByRepay(clientRepaymentModel.getOrderId(), clientRepaymentModel.getPeriods())).setScale(2,BigDecimal.ROUND_HALF_UP);
            int i = repayMoney.compareTo(clientRepaymentModel.getMoney());
            BasicCustomerOrder order = basicCustomerOrderService.findById(clientRepaymentModel.getOrderId());
            if(order.getOrderNowPeriods().equals(0)){
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有付首付款，请付首付款再还款");
            }else if (i < 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "还款金额不能大于本期应还金额");
            } else if(BasicCustomerOrder.OrderStatus.UNPAID.getValue().equals(order.getOrderStatus())||
                    BasicCustomerOrder.OrderStatus.NOT_MENTIONED_CAR.getValue().equals(order.getOrderStatus())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该订单未放款或客户未提车");
            }
            else {
                repaySchemeService.editOrderRepaySchemeRepay(clientRepaymentModel.getOrderId(), clientRepaymentModel.getPeriods(), clientRepaymentModel.getMoney(), user);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "还款成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @PostMapping("/financialInvoice/{id}")
    @ApiOperation(value = "确定开票")
    public ResponseResult financialInvoice(
            @PathVariable("id") @ApiParam(value = "id",required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceReceivable financeReceivable = new FinanceReceivable();
            financeReceivable.setId(id);
            financeReceivable.setIsInvoice(Status.Enable.getValue());
            financeReceivableService.updateSelective(financeReceivable);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "开票成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/customerOrderLoan/{orderId}")
    @ApiOperation(value = "客户放款")
    public ResponseResult customerOrderLoan(
            @PathVariable("orderId") @ApiParam(value = "orderId",required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            //订单
            BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
            //银行卡
            BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(order.getCustomerId());
           if(Objects.nonNull(basicCustomerBank)){
            FinanceLoan financeLoan = new FinanceLoan();
            financeLoan.setOrderId(orderId);
            financeLoan.setOrgId(user.getOrgId());
            financeLoan.setLoanDate(new Date());
            financeLoan.setOperatorTime(new Date());
               List<FinanceStorage> list = financeStorageService.selectStatusOnIN(orderId);
               if(!list.isEmpty()){
                   return new ResponseResult<>(ResponseResult.Status.FAILURE, "该客户的押品没有入库");
               }else {
                   financeLoanService.addFinanceLoan(financeLoan,user);
                   return new ResponseResult<>(ResponseResult.Status.SUCCESS, "放款成功");
               }
           }else{
               return new ResponseResult<>(ResponseResult.Status.FAILURE, "客户没有已开户的银行卡");
           }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/supplierOrderLoan/{carId}")
    @ApiOperation(value = "供应商放款")
    public ResponseResult supplierOrderLoan(
            @PathVariable("carId") @ApiParam(value = "订单车型id",required = true) Long carId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceLoan financeLoan = new FinanceLoan();
            financeLoan.setOrgId(user.getOrgId());
            financeLoan.setOperator(user.getId());
            financeLoan.setLoanDate(new Date());
            financeLoan.setOperatorTime(new Date());
            BasicStockCar stockCar = basicStockCarService.findById(carId);
            if(Objects.nonNull(stockCar) && Objects.nonNull(stockCar.getSupplierId())) {
                financeLoanService.supplierFinanceLoan(financeLoan, carId);
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, "放款成功");
            }else {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "该订单车型未采购，无法放款");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @PostMapping("/earlyRepayMoney/{orderId}")
    @ApiOperation(value = "提前结清")
    public ResponseResult earlyRepayMoney(
            @PathVariable("orderId") @ApiParam(value = "orderId",required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            //订单
            BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
            //用户
            BasicCustomer customer = basicCustomerService.findById(order.getCustomerId());
            //银行卡
            BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());
            //方案
            BasicRepayScheme scheme = basicRepaySchemeService.findById(order.getSchemeId());
            //订单还款详情
            BasicRepaySchemeExpense repaySchemeExpense = basicRepaySchemeExpenseService.findRepaySchemeExpense(scheme.getId());
           if(Objects.isNull(repaySchemeExpense)){
               return new ResponseResult<>(ResponseResult.Status.FAILURE, "该订单方案里没有提前结清的方案");
           }else {
               if (Objects.isNull(basicCustomerBank)) {
                   return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户没有开户的银行卡");
               }
               repaySchemeService.updateRepayMoney(orderId, user);
               return new ResponseResult<>(ResponseResult.Status.SUCCESS, "成功");
           }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @PostMapping("/earlyRecycle")
    @ApiOperation(value = "提前收回")
    public ResponseResult earlyRecycle(
            @Validated @RequestBody EarlyRecycleParams earlyRecycleParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            //订单
            BasicCustomerOrder order = basicCustomerOrderService.findById(earlyRecycleParams.getOrderId());
            //银行卡
            BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(order.getCustomerId());
            if(Objects.nonNull(basicCustomerBank)) {
                financeTakebackService.earlyRecovery(earlyRecycleParams, user);
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, "成功");
            }else { return new ResponseResult<>(ResponseResult.Status.FAILURE, "用户没有已开户的银行卡");}

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/financialRefund/{orderId}")
    @ApiOperation(value = "退款")
    public ResponseResult financialRefund(
            @PathVariable("orderId") @ApiParam(value = "orderId",required = true) Long orderId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
            BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(order.getCustomerId());
            if(Objects.isNull(basicCustomerBank)){
                return new ResponseResult(ResponseResult.Status.FAILURE, " 用户没有，已开户的银行卡");
            }else {
            int i = refundService.addFinanceRefund(orderId, user);
            if (i > 0) {
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, "退款成功");
            } else {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "已退款");
            } }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
        @PostMapping("/financialAffirm/{orderId}")
        @ApiOperation(value = "收款")
        public ResponseResult financialAffirm(
                @PathVariable("orderId") @ApiParam(value = "orderId",required = true) Long orderId,
                @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
            try {
                SysUser user = getUserByAuth(authorization);
                if (Objects.isNull(user)) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
                }
                BasicCustomerOrder order = basicCustomerOrderService.findById(orderId);
                BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(order.getCustomerId());
                if(Objects.isNull(basicCustomerBank)){
                    return new ResponseResult(ResponseResult.Status.FAILURE, " 用户没有已开户的银行卡");
                }else {
                    repaySchemeService.receipt(user,orderId);
                    return new ResponseResult<>(ResponseResult.Status.SUCCESS, "收款成功");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
            }
    }
    @PostMapping("/financialAffirmInvoice")
    @ApiOperation(value = "供应商开票")
    public ResponseResult financialAffirmInvoice(@RequestBody  SupplierFinanceTicketModel ticketModel,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceLoan financeLoan = new FinanceLoan();
            financeLoan.setId(ticketModel.getId());
            financeLoan.setFileUrl(ticketModel.getUrl());
            financeLoan.setHasInvoice( FinanceLoan.LoanType.SUPPLIER_YES.getValue());
            financeLoanService.updateSelective(financeLoan);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
