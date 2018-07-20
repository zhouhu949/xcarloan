package com.fintecher.task.mapper;

import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.task.util.MyMapper;
import com.fintecher.task.vo.BasicOrderRepaySchemeModel;
import com.fintecher.task.vo.BasicRepaySchemeExpenseModel;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;

public interface BasicOrderRepaySchemeMapper extends MyMapper<BasicOrderRepayScheme> {

    void updateOverdueCheck(String repaymentDate);

    void deleteOverdue();

    BasicRepaySchemeExpenseModel selectOverdueExpense(Map map);

    List<BasicOrderRepaySchemeModel> selectOverdueInfo(Map map);

    BigDecimal selectPrincipal(Map map);
}
