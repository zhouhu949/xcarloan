package com.fintecher.manage.service.impl;

import com.fintecher.entity.*;
import com.fintecher.manage.mapper.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.FinanceLoanModel;
import com.fintecher.manage.vo.FinanceLoanParams;
import com.fintecher.manage.vo.SupplierLoanRecordModel;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 10:56
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceLoanServiceImpl extends BaseServiceImpl<FinanceLoan> implements FinanceLoanService {
    @Autowired
    FinanceLoanMapper financeLoanMapper;
    @Autowired
    BasicOrderRepaySchemeService basicOrderRepaySchemeService;
    @Autowired
    BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    BasicCustomerOrderMapper basicCustomerOrderMapper;
    @Autowired
    BasicCustomerMapper basicCustomerMapper;
    @Autowired
    BasicCustomerBankService basicCustomerBankService;
    @Autowired
    BasicExpenseMapper basicExpenseMapper;
    @Autowired
    FinanceLoanDetialMapoer financeLoanDetialMappper;
    @Autowired
    BasicOrderCarService basicOrderCarService;
    @Autowired
    BasicCarModelService basicCarModelService;
    @Autowired
    BasicStockCarService basicStockCarService;
    @Autowired
    UserService userService;
    @Autowired
    BasicOrderRecordMapper basicOrderRecordMapper;
    /**
     * 客户放款
     * @param financeLoan
     */
    @Override
    public void addFinanceLoan(FinanceLoan financeLoan,SysUser user) {
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(financeLoan.getOrderId());//订单
        Integer periods = order.getOrderPeriods();
        Integer day = order.getOrderAccountDay();
        Long orderId = order.getId();
        BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
        BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
        order.setOrderNowPeriods(1);
        basicCustomerOrderService.update(order);
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
        basicOrderRecord.setOperator(user.getId());
        basicOrderRecord.setOrderId(order.getId());
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setOrderStatus(order.getOrderStatus());
        basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.LENDING.getValue());
        basicOrderRecordMapper.insert(basicOrderRecord);
        financeLoan.setLoanType(FinanceLoan.LoanType.CUSTOMER_LOAN.getValue());
        financeLoan.setLoanMoney(order.getOrderPrice());
        this.save(financeLoan);//放款记录
        BasicExpense basicExpense = new BasicExpense();
        basicExpense.setExpenseCode(BasicExpense.ExpenseCode.BJ.getValue());
        basicExpense.setOrgId(user.getOrgId());
        BasicExpense basicExpense1 = basicExpenseMapper.selectOne(basicExpense);
        FinanceLoanDetial financeLoanDetial = new FinanceLoanDetial(financeLoan.getId(),basicExpense1.getId(),basicCustomerBank.getId(), order.getOrderPrice());
        financeLoanDetialMappper.insert(financeLoanDetial);//放款记录详情
        Date date = new Date();
        if(BasicCustomerOrder.OrderRepayType.PAYMENT_SCHEDULE.getValue().equals(order.getOrderRepayType())){
            date = ZWDateUtil.getNextMonth(date, day);
            Date nextMonthDay = ZWDateUtil.getNextMonthDay(date, day, periods);
            basicOrderRepaySchemeService.updateRepaymentDate(orderId, 1, nextMonthDay);
        }else {
            for (int i = 1; i <= periods; i++) {
                date = ZWDateUtil.getNextMonth(date, day);
                basicOrderRepaySchemeService.updateRepaymentDate(orderId, i, date);
            }
        }
    }

    /**
     * 供应商放款
     * @param financeLoan
     */
    @Override
    public void supplierFinanceLoan(FinanceLoan financeLoan,Long CarId) {
        BasicStockCar stockCar = basicStockCarService.findById(CarId);//库存车辆
        stockCar.setHasSupplierLoan(BasicOrderCar.HasSupplierLoan.SUPPLIER_LOAN_YES.getValues());
        basicStockCarService.updateSelective(stockCar);
        BasicOrderCar orderCar = new BasicOrderCar();
        orderCar.setStockId(CarId);
        orderCar.setHasSupplierLoan(BasicOrderCar.HasSupplierLoan.SUPPLIER_LOAN_YES.getValues());
        basicOrderCarService.updateSelective(orderCar);
        stockCar.setHasSupplierLoan(BasicOrderCar.HasSupplierLoan.SUPPLIER_LOAN_YES.getValues());
        basicStockCarService.updateSelective(stockCar);
        financeLoan.setLoanType(FinanceLoan.LoanType.SUPPLIER_LOAN.getValue());//供应商放款
        financeLoan.setSupplierId(stockCar.getSupplierId());
        financeLoan.setOrderId(stockCar.getOrderId());
        financeLoan.setLoanMoney(stockCar.getStockPrice());
        this.save(financeLoan);//放款记录
        BasicExpense basicExpense = new BasicExpense();
        basicExpense.setExpenseCode(BasicExpense.ExpenseCode.BJ.getValue());
        basicExpense.setOrgId(stockCar.getOrgId());
        BasicExpense basicExpense1 = basicExpenseMapper.selectOne(basicExpense);
        FinanceLoanDetial financeLoanDetial = new FinanceLoanDetial(financeLoan.getId(),basicExpense1.getId(),null, stockCar.getStockPrice());
        financeLoanDetialMappper.insert(financeLoanDetial);//放款记录详情
    }

    @Override
    public List<SupplierLoanRecordModel> querySupplierLoan(Long supplierId, Integer hasInvoice ,SysUser user) {
        Map map = new HashMap();
        map.put("supplierId",supplierId);
        map.put("hasInvoice",hasInvoice);
        List<String> dataAuth = userService.findAllDataAuth(user.getId());
        List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("loanType", FinanceLoan.LoanType.SUPPLIER_LOAN.getValue());
        return financeLoanMapper.selectSupplierLoan(map);
    }


}
