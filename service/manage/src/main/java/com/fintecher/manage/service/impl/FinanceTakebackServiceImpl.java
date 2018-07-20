package com.fintecher.manage.service.impl;

import com.fintecher.entity.*;
import com.fintecher.manage.mapper.*;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.EarlyRecycleParams;
import com.fintecher.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 15:37
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceTakebackServiceImpl extends BaseServiceImpl<FinanceTakeback> implements FinanceTakebackService {
    @Resource
    BasicOrderRepaySchemeMapper basicOrderRepaySchemeMapper;
    @Resource
    BasicCustomerOrderMapper basicCustomerOrderMapper;
    @Resource
    BasicCustomerMapper basicCustomerMapper;
    @Autowired
    BasicCustomerBankService basicCustomerBankService;
    @Autowired
    FinanceReceivableService financeReceivableService;
    @Autowired
    BasicRepaySchemeService basicRepaySchemeService;
    @Autowired
    BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Autowired
    FinanceSettleService financeSettleService;
    @Autowired
    BasicExpenseService basicExpenseService;
    @Autowired
    BasicCustomerBlacklistRecordService blacklistRecordService;
    @Autowired
    FinanceReceivableDetialMapper financeReceivableDetialMapper;
    @Autowired
    BasicOrderRecordMapper basicOrderRecordMapper;
    @Override
    public void earlyRecovery(EarlyRecycleParams earlyRecycleParams, SysUser user) {
        Date date = new Date();
        BasicCustomerOrder order = basicCustomerOrderMapper.selectByPrimaryKey(earlyRecycleParams.getOrderId());//订单
        BasicCustomer customer = basicCustomerMapper.selectByPrimaryKey(order.getCustomerId());//用户
        BasicCustomerBank basicCustomerBank = basicCustomerBankService.selectByCustomerId(customer.getId());//银行卡
        Map map = new HashMap();
        map.put("orderId",order.getId());
        map.put("periods",order.getOrderNowPeriods());
        Long orgId = user.getOrgId();
        Long carId = basicCustomerBank.getId();
        Integer receivableType = FinanceReceivable.ReceivableType.EARLYWITHDRAWAL.getValue();
        BigDecimal settlement = financeSettleService.earlySettlement(earlyRecycleParams.getOrderId());
        int no = Status.Disable.getValue();
        Long userId = user.getId();
        FinanceReceivable financeReceivable = new FinanceReceivable(orgId,carId, earlyRecycleParams.getOrderId(), receivableType , settlement, date, no,no, userId, date);
        financeReceivableService.save(financeReceivable);//添加收款记录
        Long id = financeReceivable.getId();
        List<BasicOrderRepayScheme> basicOrderRepaySchemes = basicOrderRepaySchemeMapper.selectRepayInfo(map);
        BigDecimal money = earlyRecycleParams.getMoney();
        for (BasicOrderRepayScheme orderRepayScheme: basicOrderRepaySchemes) {
            BigDecimal repayMoney = orderRepayScheme.getRepayMoney();
            BigDecimal isRepayMoney = orderRepayScheme.getIsRepayMoney();
            BigDecimal subtract = repayMoney.subtract(isRepayMoney);
            orderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.RETURN.getValue());
             int i = money.compareTo(subtract);
             if(i>=0){
                 orderRepayScheme.setIsRepayMoney(subtract);
             }else {
                 orderRepayScheme.setIsRepayMoney(money);
                 money = new BigDecimal(0);
             }
            FinanceReceivableDetial receivableDetial = new FinanceReceivableDetial();
            receivableDetial.setOrderRepayId(orderRepayScheme.getId());
            receivableDetial.setReceivableDetialMoney(orderRepayScheme.getIsRepayMoney());
            receivableDetial.setReceivableId(id);
            basicOrderRepaySchemeMapper.updateRecover(orderRepayScheme);//订单还款详情
            financeReceivableDetialMapper.insert(receivableDetial);//收款记录详情
        }
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.FINANCE_TAKE_BACK.getValue());
        order.setOperator(user.getId());
        order.setOperatorTime(date);
        basicCustomerOrderMapper.updateByPrimaryKeySelective(order);//订单
        FinanceTakeback financeTakeback = new FinanceTakeback();//提前收回记录
        financeTakeback.setReceivableId(financeReceivable.getId());
        financeTakeback.setOrderId(order.getId());
        financeTakeback.setOrgId(user.getOrgId());
        financeTakeback.setTakebackMoney(settlement);
        financeTakeback.setOrderId(userId);
        financeTakeback.setOperatorTime(date);
        financeTakeback.setTakebackDate(date);
        this.save(financeTakeback);
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
        basicOrderRecord.setRemark(customer.getCustomerName()+"提前收回");
        basicOrderRecord.setOrderId(order.getId());
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setOperator(user.getId());
        basicOrderRecord.setOrderStatus(BasicOrderRecord.OrderLink.RECOVER.getValue());
        basicOrderRecord.setLinkTableName("finance_takeback");
        basicOrderRecordMapper.insert(basicOrderRecord);
        BasicCustomerBlacklistRecord blacklistRecord = new BasicCustomerBlacklistRecord(order.getCustomerId(),
               new Date(),null, BasicCustomerBlacklistRecord.BlacklistType.BLACK.getValue(),
                null,user.getId(),new Date());
        blacklistRecordService.save(blacklistRecord);
        if(order.getOrderType().intValue()==BasicCustomerOrder.OrderType.MORTGAGE.getValue().intValue()) {
            //抵押贷则会将用户加入黑名单
            customer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HMD.getValue());
            basicCustomerMapper.updateByPrimaryKey(customer);
        }
    }
}
