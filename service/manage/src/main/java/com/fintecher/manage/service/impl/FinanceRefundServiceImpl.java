package com.fintecher.manage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCustomerMapper;
import com.fintecher.manage.mapper.BasicOrderRecordMapper;
import com.fintecher.manage.mapper.FinanceRefundMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.FinanceRefundModel;
import com.fintecher.manage.vo.RefundExpenseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceRefundServiceImpl extends BaseServiceImpl<FinanceRefund> implements FinanceRefundService {
    @Autowired
    FinanceRefundMapper financeRefundMapper;
    @Autowired
    BasicCustomerOrderService orderService;
    @Autowired
    BasicCustomerBankService bankService;
    @Autowired
    FinanceRefundDetialService refundDetialService;
    @Autowired
    BasicOrderRepaySchemeService orderRepaySchemeService;
    @Autowired
    BasicCustomerMapper basicCustomerMapper;
    @Autowired
    BasicOrderRecordMapper basicOrderRecordMapper;
    @Override
    public int addFinanceRefund(Long orderId, SysUser user) {
        BasicCustomerOrder order = orderService.findById(orderId);
        Map map = new HashMap();
        map.put("yes", FinanceRefund.YesOrNo.YES.getValue());
        map.put("orderId", orderId);
        map.put("money", order.getOrderPrice());
        BigDecimal money1 = financeRefundMapper.selectMoney(map);//退款总额
        if (Objects.nonNull(money1)) {
            BasicCustomerBank basicCustomerBank = bankService.selectByCustomerId(order.getCustomerId());
            FinanceRefund refund = new FinanceRefund();
            refund.setOrderId(orderId);
            refund.setOrgId(user.getOrgId());
            refund.setIsReceipt(FinanceRefund.YesOrNo.No.getValue());
            refund.setIsInvoice(FinanceRefund.YesOrNo.No.getValue());
            refund.setOperatorTime(new Date());
            refund.setRefundMoney(money1);
            refund.setRefundDate(new Date());
            this.save(refund);
            List<BasicRepaySchemeExpense> schemeExpenses = financeRefundMapper.selectSchemeExpense(map);
            BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
            BasicOrderRecord basicOrderRecord = new BasicOrderRecord();//订单操作记录
            basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.REPAYMENT.getValue());
            basicOrderRecord.setOperatorTime(new Date());
            basicOrderRecord.setOperator(user.getId());
            basicOrderRecord.setOrderStatus(BasicOrderRecord.OrderLink.REPAYMENT.getValue());
            basicOrderRecord.setOrderId(orderId);
            basicOrderRecord.setRemark(customer.getCustomerName()+"退款，退款金额"+money1+"元");
            basicOrderRecordMapper.insert(basicOrderRecord);
            for (BasicRepaySchemeExpense schemeExpense : schemeExpenses) {
                BigDecimal money = Objects.isNull(schemeExpense.getFixedCost())
                        ? order.getOrderPrice().multiply(schemeExpense.getRepayProportion()) : schemeExpense.getFixedCost();
                FinanceRefundDetial refundDetial = new FinanceRefundDetial();
                refundDetial.setCardId(basicCustomerBank.getId());
                refundDetial.setExpenseId(schemeExpense.getExpenseId());
                refundDetial.setRefundId(refund.getId());
                refundDetial.setRefundDetialMoney(money);
                refundDetialService.save(refundDetial);
                orderRepaySchemeService.updateRepayScheme(orderId, order.getOrderNowPeriods()+1, BasicOrderRepayScheme.RepayStatus.SETTLE.getValue());
            }
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<RefundExpenseModel> selectRefundExpense(Long orderId) {
        BasicCustomerOrder order = orderService.findById(orderId);
        Map map = new HashMap();
        map.put("yes", FinanceRefund.YesOrNo.YES.getValue());
        map.put("orderId", orderId);
        map.put("money", order.getOrderPrice().subtract( queryRefundBackMoney(orderId,order.getOrderPrice())));
        List<RefundExpenseModel> list = financeRefundMapper.selectRefundExpense(map);
        return list;
    }

    @Override
    public BigDecimal queryRefundBackMoney(Long orderId,BigDecimal orderPrice) {
        Map map = new HashMap();
        map.put("orderId",orderId);
        map.put("money",orderPrice);
        map.put("expenseCode",BasicExpense.ExpenseCode.SFK.getValue());
        BigDecimal backMoney = financeRefundMapper.queryRefundBackMoney(map);
        return backMoney;
    }

    @Override
    public List<FinanceRefundModel> queryFinancialRefund(Long orderId, List<String> dataAuth, List<String> exceptDataAuth) {
        Map<String,Object> map = new HashMap<>();
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("orderId", orderId);
        List<FinanceRefundModel> list = financeRefundMapper.queryFinancialRefund(map);
        return list;
    }
}
