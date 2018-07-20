package com.fintecher.manage.mapper;

import com.fintecher.entity.FinanceSettle;
import com.fintecher.manage.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 13:46
 * @Description:
 */
public interface FinanceSettleMapper extends MyMapper<FinanceSettle> {
    /**
     * 查询订单所有的费用项
     * @return
     */
    List<Map> selectExpense(Long orderId);

    List<Map> selectOrderRepaySchemeInfo(Map map);

}
