package com.fintecher.manage.service;

import com.fintecher.entity.FinanceSettle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 13:47
 * @Description:
 */
public interface FinanceSettleService extends BaseService<FinanceSettle> {
    BigDecimal earlySettlement(Long orderId);

    /**
     * 查询订单还款详情
     * @return orderId 订单Id
     */
    List<Map> selectOrderRepaySchemeInfo(Long orderId);
}
