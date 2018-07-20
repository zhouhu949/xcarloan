package com.fintecher.task.service.impl;

import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.task.mapper.BasicOrderRepaySchemeMapper;
import com.fintecher.task.service.BasicOrderRepaySchemeService;
import com.fintecher.task.vo.BasicOrderRepaySchemeModel;
import com.fintecher.task.vo.BasicRepaySchemeExpenseModel;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackOn = Exception.class)
@Service
public class BasicOrderRepaySchemeServiceImpl extends BaseServiceImpl<BasicOrderRepayScheme> implements BasicOrderRepaySchemeService {

    @Autowired
    private BasicOrderRepaySchemeMapper basicOrderRepaySchemeMapper;

    @Override
    public void overdueCheck() {
        String repaymentDate = ZWDateUtil.getDate();
        basicOrderRepaySchemeMapper.updateOverdueCheck(repaymentDate);//逾期未还款将状态修改为逾期
        //删除之前未还的罚息和罚金
        basicOrderRepaySchemeMapper.deleteOverdue();
        //查询逾期的订单（一个订单一期逾期一条数据）
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        map.put("repaymentDate",repaymentDate);
        List<BasicOrderRepaySchemeModel> models = basicOrderRepaySchemeMapper.selectOverdueInfo(map);
        for (BasicOrderRepaySchemeModel repaySchemeModel: models) {
            Map<String, Object> mp = new HashMap<>();
            mp.put("schemeId",repaySchemeModel.getSchemeId());
            mp.put("expenseCode", BasicRepaySchemeExpenseModel.ExpenseCode.FJ.getValue());
            //查询订单对应的罚金比例
            BasicRepaySchemeExpenseModel model = basicOrderRepaySchemeMapper.selectOverdueExpense(mp);
            BigDecimal repayProportion = model.getRepayProportion(); //利率
            //逾期天数
            Integer day = ZWDateUtil.getBetween(repaySchemeModel.getRepaymentDate(), date, ChronoUnit.DAYS);
            //逾期未还金额
            BigDecimal repayMoney = repaySchemeModel.getRepayMoney();
            //罚金 = 本期未还金额总额*利率*比例
            BigDecimal needRepayMoney =
                    (repayMoney.multiply(repayProportion).multiply(new BigDecimal(day))).setScale(2, BigDecimal.ROUND_HALF_UP);
            BasicOrderRepayScheme basicOrderRepayScheme = new BasicOrderRepayScheme();
            basicOrderRepayScheme.setOrderId(repaySchemeModel.getOrderId());
            basicOrderRepayScheme.setRepayMoney(needRepayMoney);
            basicOrderRepayScheme.setExpenseId(model.getExpenseId());
            basicOrderRepayScheme.setExpenseName(model.getExpenseName());
            basicOrderRepayScheme.setExpenseCode(model.getExpenseCode());
            basicOrderRepayScheme.setPeriods(repaySchemeModel.getPeriods());
            basicOrderRepayScheme.setOperator(1L);
            basicOrderRepayScheme.setRepaymentDate(date);
            basicOrderRepayScheme.setRemark("逾期罚金");
            basicOrderRepaySchemeMapper.insert(basicOrderRepayScheme);
            mp.put("expenseCode", BasicRepaySchemeExpenseModel.ExpenseCode.FX.getValue());
            BasicRepaySchemeExpenseModel model2 = basicOrderRepaySchemeMapper.selectOverdueExpense(mp);
            mp.put("orderId",repaySchemeModel.getOrderId());
            mp.put("periods",repaySchemeModel.getPeriods());
            //查询订单对应的罚息比例
            BigDecimal principal = basicOrderRepaySchemeMapper.selectPrincipal(mp);
            //罚息 = 本期本金额*利率*比例
            BigDecimal bigDecimal = (principal.multiply(repayProportion).multiply(new BigDecimal(day))).setScale(2, BigDecimal.ROUND_HALF_UP);
            basicOrderRepayScheme.setId(null);
            basicOrderRepayScheme.setRepayMoney(bigDecimal);
            basicOrderRepayScheme.setExpenseId(model2.getExpenseId());
            basicOrderRepayScheme.setExpenseName(model2.getExpenseName());
            basicOrderRepayScheme.setExpenseName(model2.getExpenseCode());
        }
    }
}
