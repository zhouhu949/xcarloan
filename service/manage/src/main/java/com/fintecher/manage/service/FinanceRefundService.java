package com.fintecher.manage.service;

import com.fintecher.entity.FinanceRefund;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.FinanceRefundModel;
import com.fintecher.manage.vo.RefundExpenseModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FinanceRefundService extends BaseService<FinanceRefund> {
    /**
     * 添加退款记录
     */
    int addFinanceRefund(Long orderId, SysUser user);
    /**
     * 查询退款金额
     * @param orderId
     * @return
     */
    List<RefundExpenseModel> selectRefundExpense(Long orderId);

    BigDecimal queryRefundBackMoney(Long orderId,BigDecimal orderPrice);

    List<FinanceRefundModel> queryFinancialRefund(Long orderId, List<String> dataAuth, List<String> exceptDataAuth);

}
