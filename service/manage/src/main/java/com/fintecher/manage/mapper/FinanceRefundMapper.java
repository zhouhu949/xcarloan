package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.entity.FinanceRefund;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.FinanceRefundModel;
import com.fintecher.manage.vo.RefundExpenseModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FinanceRefundMapper extends MyMapper<FinanceRefund>{

   List<BasicRepaySchemeExpense> selectSchemeExpense(Map map);

   BigDecimal selectMoney(Map map);

   List<RefundExpenseModel> selectRefundExpense(Map map);
   BigDecimal queryRefundBackMoney(Map map);
   List<FinanceRefundModel> queryFinancialRefund(Map map);

}
