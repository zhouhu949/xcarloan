package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.entity.FinanceSettle;
import com.fintecher.manage.mapper.BasicCustomerOrderMapper;
import com.fintecher.manage.mapper.FinanceSettleMapper;
import com.fintecher.manage.service.BasicOrderRepaySchemeService;
import com.fintecher.manage.service.FinanceSettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 13:48
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceSettleServiceImpl extends BaseServiceImpl<FinanceSettle> implements FinanceSettleService {
    @Autowired
    FinanceSettleMapper financeSettleMapper;
    @Autowired
    BasicOrderRepaySchemeService basicOrderRepaySchemeService;
    @Autowired
    BasicCustomerOrderMapper basicCustomerOrderMapper;
    /**
     * 提前结清
     * @param orderId
     */
    @Override
    public BigDecimal earlySettlement(Long orderId) {
        //订单剩余要还的总余额
        return basicOrderRepaySchemeService.selectBalance(orderId);
    }


    @Override
    public List<Map> selectOrderRepaySchemeInfo(Long orderId) {
        List list = new ArrayList();
        List<HashMap> orderPayList = basicCustomerOrderMapper.queryOrderPayList(orderId);
        orderPayList.forEach(e->{
            int periods = Integer.parseInt(e.get("periods").toString());
            Integer status;
            BigDecimal needPay = new BigDecimal(e.get("needPay").toString());
            BigDecimal repayMoney = new BigDecimal(e.get("repayMoney").toString());
            if(needPay.compareTo(BigDecimal.ZERO)>0 && needPay.compareTo(repayMoney)<0){
                status = BasicOrderRepayScheme.RepayStatus.PART.getValue();
            }else if(needPay.compareTo(BigDecimal.ZERO)==0){
                status = BasicOrderRepayScheme.RepayStatus.SETTLE.getValue();
            }else {
                status = BasicOrderRepayScheme.RepayStatus.WAIT.getValue();
            }
            //不绘制首付款 不绘制尾款
            if(periods!=0 && repayMoney.compareTo(BigDecimal.ZERO)>0){
                Map resMap = new HashMap();
                resMap.put("periods", periods);
                resMap.put("orderId", orderId);
                resMap.put("status", status);

                //每期还款详情
                List feeItem = basicCustomerOrderMapper.queryOrderPayFeeItemList(resMap);
                resMap.put("feeItem", feeItem);
                list.add(resMap);
            }
        });
        return list;
    }
}
