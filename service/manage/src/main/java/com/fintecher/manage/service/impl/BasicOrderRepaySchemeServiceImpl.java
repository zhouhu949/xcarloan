package com.fintecher.manage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Transactional(rollbackOn = Exception.class)
@Service
public class BasicOrderRepaySchemeServiceImpl extends BaseServiceImpl<BasicOrderRepayScheme> implements BasicOrderRepaySchemeService {
    @Autowired
    private BasicOrderRepaySchemeMapper basicOrderRepaySchemeMapper;
    @Autowired
    private BasicCustomerOrderMapper basicCustomerOrderMapper;
    @Autowired
    private BasicCustomerMapper basicCustomerMapper;
    @Autowired
    private BasicCustomerBankService basicCustomerBankService;
    @Autowired
    private FinanceReceivableService financeReceivableService;
    @Autowired
    private BasicRepaySchemeMapper basicRepaySchemeMapper;
    @Autowired
    private BasicRepaySchemeService basicRepaySchemeService;
    @Autowired
    private BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Autowired
    private BasicOrderRecordMapper basicOrderRecordMapper;
    @Autowired
    private FinanceReceivableDetialMapper financeReceivableDetialMapper;
    @Autowired
    private FinanceSettleMapper financeSettleMapper;
    @Override
    public void editOrderRepaySchemeRepay(Long orderId, Integer periods, BigDecimal money, SysUser user) {
        BasicOrderRepayScheme orderRepayScheme = new BasicOrderRepayScheme();
        orderRepayScheme.setOrderId(orderId);
        orderRepayScheme.setPeriods(periods);
        List<BasicOrderRepayScheme> basicOrderRepaySchemes = basicOrderRepaySchemeMapper.selectByOrder(orderRepayScheme);
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(orderId);//订单
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("periods", periods);
        BigDecimal bigDecimal = basicOrderRepaySchemeMapper.selectRepayInfoMoney(map);
        bigDecimal = Objects.isNull(bigDecimal) ? new BigDecimal(0) : bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        BasicRepayScheme scheme = basicRepaySchemeMapper.selectByPrimaryKey(order.getSchemeId());
        BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
        BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());//银行卡
        FinanceReceivable financeReceivable = new FinanceReceivable(user.getOrgId(),
                basicCustomerBank.getId(), orderId, FinanceReceivable.ReceivableType.REPATMONEY.getValue(),
                money, new Date(), Status.Disable.getValue(), Status.Disable.getValue(),
                user.getId(), new Date());//收款记录
        financeReceivableService.save(financeReceivable);//添加收款记录
        BasicRepayScheme basicRepayScheme = basicRepaySchemeMapper.selectByPrimaryKey(order.getSchemeId());//还款方案实体
        BigDecimal moneyCount = this.selectByRepay(orderId, periods);
        int n = moneyCount.compareTo(money);
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();//订单操作记录
        basicOrderRecord.setOrderId(orderId);
        if ((periods.equals(scheme.getPeriods()) ||
                BasicRepaySchemeExpense.RepayType.ONCE.getValue().equals(basicRepayScheme.getRepayType())) && n == 0) {//是最后一笔还款订单状态改为待结案
            order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue());
            basicOrderRecord.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue());//订单操作记录里的订单状态
            basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.REPAYMENT.getValue());//订单环节
        } else {
            basicOrderRecord.setOrderStatus(BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());//订单操作记录里的订单状态
            basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.REPAYMENT.getValue());//订单环节
        }
        basicOrderRecord.setOrderId(user.getOrgId());
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setRemark("客户"+customer.getCustomerName()+"第"+order.getOrderNowPeriods()+"期还款,还款金额:"+money+"元");
        basicOrderRecordMapper.insert(basicOrderRecord);//添加订单操作记录
        Integer orderNowPeriods = order.getOrderNowPeriods();
        if (bigDecimal.compareTo(money) == 0) { //本期还清
            for (BasicOrderRepayScheme basicOrderRepayScheme : basicOrderRepaySchemes) {
                basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.SETTLE.getValue());
                basicOrderRepayScheme.setIsRepayMoney(basicOrderRepayScheme.getRepayMoney());//更新客户已还金额
                basicOrderRepaySchemeMapper.updateByPrimaryKeySelective(basicOrderRepayScheme);//订单还款详情
                FinanceReceivableDetial financeReceivableDetial = new FinanceReceivableDetial();
                financeReceivableDetial.setOrderRepayId(basicOrderRepayScheme.getId());
                financeReceivableDetial.setReceivableId(financeReceivable.getId());
                financeReceivableDetial.setReceivableDetialMoney(basicOrderRepayScheme.getRepayMoney());
                financeReceivableDetialMapper.insert(financeReceivableDetial);
            }
        } else { //本期部分还清
            BigDecimal balance = money;//客户本期付款总额
            for (BasicOrderRepayScheme basicOrderRepayScheme : basicOrderRepaySchemes) {
                if (Objects.nonNull(basicOrderRepayScheme)) {
                    BigDecimal repayMoney = basicOrderRepayScheme.getRepayMoney();//客户本期应还总金额
                    repayMoney = Objects.isNull(repayMoney) ? new BigDecimal(0) : repayMoney;
                    BigDecimal isRepayMoney = basicOrderRepayScheme.getIsRepayMoney();//客户已还金额
                    isRepayMoney = Objects.isNull(isRepayMoney) ? new BigDecimal(0) : isRepayMoney;
                    BigDecimal subtract = repayMoney.subtract(isRepayMoney); //客户本期剩余应还金额
                    int i = balance.compareTo(subtract);
                    if (i < 0) { //部分还款
                        basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.PART.getValue());
                        basicOrderRepayScheme.setIsRepayMoney(isRepayMoney.add(balance));//更新客户已还金额
                        basicOrderRepaySchemeMapper.updateByPrimaryKey(basicOrderRepayScheme);
                        FinanceReceivableDetial financeReceivableDetial = new FinanceReceivableDetial();//收款记录详情
                        financeReceivableDetial.setOrderRepayId(basicOrderRepayScheme.getId());
                        financeReceivableDetial.setReceivableId(financeReceivable.getId());
                        financeReceivableDetial.setReceivableDetialMoney(balance);
                        financeReceivableDetialMapper.insert(financeReceivableDetial);//添加收款记录详情
                        break;
                    } else if (i >= 0) {//还清
                        basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.SETTLE.getValue());
                        basicOrderRepayScheme.setIsRepayMoney(repayMoney);//更新客户已还金额
                        basicOrderRepaySchemeMapper.updateByPrimaryKeySelective(basicOrderRepayScheme);//订单还款详情
                        FinanceReceivableDetial financeReceivableDetial = new FinanceReceivableDetial();
                        financeReceivableDetial.setOrderRepayId(basicOrderRepayScheme.getId());
                        financeReceivableDetial.setReceivableId(financeReceivable.getId());
                        financeReceivableDetial.setReceivableDetialMoney(repayMoney);
                        financeReceivableDetialMapper.insert(financeReceivableDetial);
                        balance = balance.subtract(subtract);//剩余总额
                    }
                }
            }
        }
        if (orderNowPeriods.equals(order.getOrderPeriods())) {//订单到了最后一期
            order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue());
        } else if (BasicCustomerOrder.OrderRepayType.PAYMENT_SCHEDULE.getValue().equals(order.getOrderRepayType())
           &&bigDecimal.compareTo(money) == 0) {//订单还款方式是一次性还清且付清
            order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue());
        } else if(bigDecimal.compareTo(money) == 0 && !orderNowPeriods.equals(order.getOrderPeriods())){//订单还清当前期
            order.setOrderNowPeriods(orderNowPeriods + 1);
        }
        basicCustomerOrderMapper.updateByPrimaryKey(order);
    }

    @Override
    public BigDecimal selectByRepay(Long orderId, Integer periods) {
        BasicOrderRepayScheme basicOrderRepayScheme = new BasicOrderRepayScheme();
        basicOrderRepayScheme.setOrderId(orderId);
        basicOrderRepayScheme.setPeriods(periods);
        return basicOrderRepaySchemeMapper.selectByRepay(basicOrderRepayScheme);
    }

    @Override
    public BigDecimal selectBalance(Long orderId) {
        return basicOrderRepaySchemeMapper.selectBalance(orderId);
    }

    @Override
    public List<PrepaymentModel> selectDetails(Long orderId) {
        List<PrepaymentModel> prepaymentModels = new ArrayList<>();
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(orderId);//订单
        BasicRepayScheme scheme = basicRepaySchemeService.findById(order.getSchemeId());//方案
        if (Objects.isNull(scheme)) {
            return prepaymentModels;
        } else {
            prepaymentModels = basicOrderRepaySchemeMapper.selectDetails(orderId);
            PrepaymentModel prepaymentModel = new PrepaymentModel();
            BasicRepaySchemeExpense repaySchemeExpense = basicRepaySchemeExpenseService.findRepaySchemeExpense(scheme.getId());//订单还款详情
            if (Objects.nonNull(repaySchemeExpense)) {
                BigDecimal repayMoney = this.selectRepayMoney(orderId);
                BigDecimal fixedCost = repaySchemeExpense.getFixedCost();
                prepaymentModel.setExpenseName("提前还清手续费");
                if (Objects.isNull(fixedCost)) {
                    prepaymentModel.setRepayMoney(repayMoney.multiply(repaySchemeExpense.getRepayProportion()));
                    prepaymentModel.setMoney(repayMoney.multiply(repaySchemeExpense.getRepayProportion()));
                } else {
                    prepaymentModel.setRepayMoney(fixedCost);
                    prepaymentModel.setMoney(fixedCost);
                }
                prepaymentModels.add(prepaymentModel);//添加提前结清手续费
            }
            BigDecimal repayMoney = Objects.isNull(prepaymentModel.getRepayMoney()) ? new BigDecimal(0) : prepaymentModel.getRepayMoney();
            PrepaymentModel model = basicOrderRepaySchemeMapper.selectCountMoney(orderId);//合计
            BigDecimal repayMoney1 = model.getRepayMoney();
            BigDecimal money = Objects.isNull(model.getMoney()) ? new BigDecimal(0) : model.getMoney();
            model.setExpenseName("合计");
            model.setRepayMoney(repayMoney1.add(repayMoney));
            model.setMoney(money.add(repayMoney));
            prepaymentModels.add(model);
        }
        return prepaymentModels;
    }

    @Override
    public BigDecimal selectRepayMoney(Long orderId) {
        return basicOrderRepaySchemeMapper.selectRepayMoney(orderId);
    }

    @Override
    public void updateRepayMoney(Long orderId, SysUser user) {
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(orderId);//订单
        BigDecimal needRepayMoney = basicOrderRepaySchemeMapper.selectNeedRepayMoney(order.getId());
        BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
        BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());//银行卡
        BasicOrderRepayScheme basicOrderRepayScheme = new BasicOrderRepayScheme();
        basicOrderRepayScheme.setOrderId(orderId);
        basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.BEFORE.getValue());
        basicOrderRepaySchemeMapper.updateRepayMoney(basicOrderRepayScheme);//订单还款详情
        String expenseCode = BasicExpense.ExpenseCode.TQJQSXF.getValue();
        RepaySchemeExpenseModel scheme = basicRepaySchemeService.getEarlySettlementScheme(order.getSchemeId(), expenseCode);//方案
        BigDecimal repayMoney = this.selectRepayMoney(orderId);
        BigDecimal fixedCost = scheme.getFixedCost();
        BigDecimal commission;
        if (Objects.isNull(fixedCost)) {
            commission = repayMoney.multiply(scheme.getRepayProportion());
        } else {
            commission = fixedCost;
        }
        BasicOrderRepayScheme repayScheme = new BasicOrderRepayScheme(scheme.getExpenseId(),
                orderId, order.getOrderNowPeriods(), commission, commission,
                BasicOrderRepayScheme.RepayStatus.BEFORE.getValue(), scheme.getExpenseName(), scheme.getExpenseCode(), user.getId(), new Date());//添加提前收取手续费
        repayScheme.setExpenseCode(scheme.getExpenseCode());
        repayScheme.setExpenseName(scheme.getExpenseName());
        repayScheme.setPeriods(order.getOrderNowPeriods());
        basicOrderRepaySchemeMapper.insert(repayScheme);
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.FINANCE_SETTLEMENT.getValue());
        order.setOperator(user.getId());
        order.setOperatorTime(new Date());
        basicCustomerOrderMapper.updateByPrimaryKeySelective(order);//订单
        FinanceReceivable financeReceivable = new FinanceReceivable(user.getOrgId(),
                basicCustomerBank.getId(), orderId, FinanceReceivable.ReceivableType.REPATMONEY.getValue(),
                commission, new Date(), Status.Disable.getValue(), Status.Disable.getValue(),
                user.getId(), new Date());
        financeReceivableService.save(financeReceivable);//添加收款记录
        FinanceSettle financeSettle = new FinanceSettle();//提前收回记录
        financeSettle.setOrderId(orderId);
        financeSettle.setOrgId(order.getOrgId());
        financeSettle.setOrderId(order.getId());
        financeSettle.setSettleMoney(needRepayMoney);
        financeSettle.setReceivableId(financeReceivable.getId());
        financeSettle.setSettleDate(new Date());
        financeSettle.setOperator(user.getId());
        financeSettle.setOperatorTime(new Date());
        financeSettle.setRemark(customer.getCustomerName()+"提前结清记录");
        financeSettleMapper.insert(financeSettle);
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();//订单操作记录
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setOperator(user.getId());
        basicOrderRecord.setOrderStatus(BasicOrderRecord.OrderLink.REPAYMENT.getValue());
        basicOrderRecord.setOrderId(orderId);
        basicOrderRecord.setRemark(customer.getCustomerName()+"提前结清");
        basicOrderRecordMapper.insert(basicOrderRecord);
    }

    @Override
    public List<GatheringModel> getGatheringModel(Long orderId) {
        return basicOrderRepaySchemeMapper.getGatheringModel(orderId);
    }

    @Override
    public List<ReceiptOrder> selectReceiptOrder(SearchOrderParams orderParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(orderParams, Map.class);
        return basicOrderRepaySchemeMapper.selectReceiptOrder(map);
    }

    @Override
    public List<OrderInfoModel> queryByStatus(SysUser user, SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(orderParams, Map.class);
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("orgId", user.getOrgId());
        map.put("orderStatus", BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
        return basicOrderRepaySchemeMapper.queryByStatus(map);
    }

    @Override
    public List<OrderInfoModel> queryOrderByStatus(SysUser user, SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(orderParams, Map.class);
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("orgId", user.getOrgId());
        map.put("orderStatus", BasicCustomerOrder.OrderStatus.UNPAID.getValue());
        map.put("orderType", BasicCustomerOrder.OrderType.MORTGAGE.getValue());
        return basicOrderRepaySchemeMapper.queryByStatus(map);
    }

    @Override
    public List<OrderInfoModel> selectRefundOrder(SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(orderParams, Map.class);
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        return basicOrderRepaySchemeMapper.selectRefundOrder(map);
    }

    @Override
    public Integer selectCurrentPeriods(Long orderId) {

        return basicOrderRepaySchemeMapper.selectCurrentPeriods(orderId);
    }

    @Override
    public List<BasicOrderRepayScheme> selectRepayInfo(Long orderId, Integer periods) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("periods", periods);
        return basicOrderRepaySchemeMapper.selectRepayInfo(map);
    }

    @Override
    public void receipt(SysUser user, Long orderId) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("periods", 0);
        map.put("", BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
        basicOrderRepaySchemeMapper.updateRepayScheme(map);//首付款项收款将该期费用项改为已已结清
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(orderId);//订单
        BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
        BigDecimal money = basicOrderRepaySchemeMapper.getCountMoney(orderId);
        BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());//银行卡
        FinanceReceivable financeReceivable = new FinanceReceivable();
        financeReceivable.setOperator(user.getId());
        financeReceivable.setCardId(basicCustomerBank.getId());
        financeReceivable.setOrgId(user.getOrgId());
        financeReceivable.setOrderId(orderId);
        financeReceivable.setReceivableType(FinanceReceivable.ReceivableType.REPATMONEY.getValue());
        financeReceivable.setReceivableDetialMoney(money);
        financeReceivable.setOperatorTime(new Date());
        financeReceivable.setIsReceipt(Status.Disable.getValue());
        financeReceivable.setIsInvoice(Status.Disable.getValue());
        financeReceivable.setReceivableDate(new Date());
        financeReceivableService.save(financeReceivable);//添加收款记录
        Long receivableId = financeReceivable.getId();
        BasicOrderRepayScheme orderRepayScheme = new BasicOrderRepayScheme();
        orderRepayScheme.setPeriods(0);
        orderRepayScheme.setOrderId(orderId);
        List<FinanceReceivableDetial> detials = new ArrayList<>();
        List<BasicOrderRepayScheme> schemes = basicOrderRepaySchemeMapper.select(orderRepayScheme);
        for (BasicOrderRepayScheme scheme : schemes) {
            FinanceReceivableDetial detial = new FinanceReceivableDetial();
            detial.setOrderRepayId(scheme.getId());
            detial.setReceivableId(receivableId);
            detial.setReceivableDetialMoney(scheme.getIsRepayMoney());
            detials.add(detial);
        }
        financeReceivableDetialMapper.insertList(detials);
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();//订单操作记录
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setOperator(user.getId());
        basicOrderRecord.setOrderStatus(BasicOrderRecord.OrderLink.REPAYMENT.getValue());
        basicOrderRecord.setOrderId(orderId);
        basicOrderRecord.setRemark("收取"+customer.getCustomerName()+"首付款");
        basicOrderRecordMapper.insert(basicOrderRecord);
        if(BasicCustomerOrder.OrderType.MORTGAGE.getValue().equals(order.getOrderType())){
            order.setOrderStatus(BasicCustomerOrder.OrderStatus.UNPAID.getValue());
        }else {
            order.setOrderStatus(BasicCustomerOrder.OrderStatus.NOT_MENTIONED_CAR.getValue());
        }
        order.setOrderNowPeriods(1);
        basicCustomerOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void updateRepayScheme(Long orderId, Integer periods, Integer status) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("periods", periods);
        map.put("status", status);
        basicOrderRepaySchemeMapper.updateRepayScheme(map);
    }

    @Override
    public List<OrderModel> queryByReplayMoney(SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(orderParams, Map.class);
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("orderStatus", BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
        return basicOrderRepaySchemeMapper.queryByReplayMoney(map);
    }

    @Override
    public BigDecimal selectRepayInfoMoney(Map map) {
        return basicOrderRepaySchemeMapper.selectRepayInfoMoney(map);
    }

    @Override
    public void updateRepaymentDate(Long orderId, Integer periods, Date date) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("repaymentDate", date);
        map.put("periods", periods);
        basicOrderRepaySchemeMapper.updateRepaymentDate(map);
    }


}
