package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.manage.mapper.BasicRepaySchemeExpenseMapper;
import com.fintecher.manage.service.BasicExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeService;
import com.fintecher.manage.vo.BasicSchemeExpenseParams;
import com.fintecher.manage.vo.RepayExpenseInfoModel;
import com.fintecher.manage.vo.RepaySchemeInfoModel;
import com.fintecher.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicRepaySchemeExpenseServiceImpl extends BaseServiceImpl<BasicRepaySchemeExpense> implements BasicRepaySchemeExpenseService {
    @Resource
    private BasicRepaySchemeExpenseMapper basicRepaySchemeExpenseMapper;
    @Autowired
    private BasicRepaySchemeService basicRepaySchemeService;
    @Autowired
    private BasicExpenseService basicExpenseService;

    @Override
    public RepaySchemeInfoModel findRepaySchemeInfo(Long id) {
        return basicRepaySchemeExpenseMapper.findRepaySchemeInfo(id);
    }

    @Override
    public List<RepayExpenseInfoModel> findRepaySchemeExpenseInfo(Long schemeId) {
        return basicRepaySchemeExpenseMapper.findRepaySchemeExpenseInfo(schemeId);
    }

    @Override
    public BasicRepaySchemeExpense findRepaySchemeExpense(Long schemeId) {
        Map map = new HashMap<>();
        map.put("id", schemeId);
        map.put("expenseCode", BasicExpense.ExpenseCode.TQJQSXF.getValue());
        return basicRepaySchemeExpenseMapper.selectExpense(map);
    }

    @Override
    public void validateParams(BasicSchemeExpenseParams basicExpenseParams) {
        Long schemeId = basicExpenseParams.getSchemeId();
        BasicRepayScheme basicRepayScheme = basicRepaySchemeService.findById(schemeId);
        checkArgument(!Objects.equals(basicRepayScheme.getSchemeStatus(), BasicRepayScheme.SchemeStatus.DISABLE.getValue()), "还款方案已发布不允许更改");
        Long expenseId = basicExpenseParams.getExpenseId();
        Example example = new Example(BasicRepaySchemeExpense.class);
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(basicExpenseParams.getId())) {
            criteria.andNotEqualTo("id", basicExpenseParams.getId());
        }
        criteria.andEqualTo("expenseId", expenseId);
        criteria.andEqualTo("schemeId", schemeId);
        checkArgument(this.selectCountByExample(example) <= 0, "费用项已经存在");
        Integer isFirst = basicExpenseParams.getIsFirst();
        Integer isRefund = basicExpenseParams.getIsRefund();
        Integer isLast = basicExpenseParams.getIsLast();
        BigDecimal fixedCost = basicExpenseParams.getFixedCost();
        BigDecimal repayProportion = basicExpenseParams.getRepayProportion();
        Integer repayType = basicExpenseParams.getRepayType();
        if (Objects.equals(isFirst, Status.Enable.getValue())) {
            checkArgument(Objects.equals(repayType, BasicRepaySchemeExpense.RepayType.ONCE.getValue()), "是首付款必须是一次性收取");
            checkArgument(!Objects.equals(isLast, Status.Enable.getValue()), "是首付款必须不是尾款");
        } else if (Objects.equals(isLast, Status.Enable.getValue())) {
            checkArgument(Objects.equals(repayType, BasicRepaySchemeExpense.RepayType.ONCE.getValue()), "是尾款必须是一次性收取");
            checkArgument(!Objects.equals(isFirst, Status.Enable.getValue()), "是尾款必须不是首付款");
            checkArgument(!Objects.equals(isRefund, Status.Enable.getValue()), "是尾款必须不能退款");
        } else {
            checkArgument(!Objects.equals(repayType, BasicRepaySchemeExpense.RepayType.ONCE.getValue()), "既不是首付款又不是尾款必须是分期付款");
        }
        // 罚息或者罚金必须是按比例
        BasicExpense basicExpense = basicExpenseService.findById(expenseId);
        if (Objects.equals(basicExpense.getExpenseCode(), BasicExpense.ExpenseCode.FJ.getValue())
                || Objects.equals(basicExpense.getExpenseCode(), BasicExpense.ExpenseCode.FX.getValue())) {
            checkArgument(Objects.nonNull(repayProportion), "罚金或者罚息必须是收取总额比例");
        }

        if (Objects.equals(basicExpense.getExpenseCode(), BasicExpense.ExpenseCode.SFK.getValue())) {
            checkArgument(Objects.nonNull(repayProportion), "首付款必须是收取总额比例");
            checkArgument(Objects.equals(isFirst, Status.Enable.getValue()), "首付款项必须是首付款");
        }

        checkArgument(!((Objects.isNull(fixedCost) && Objects.isNull(repayProportion))
                || (Objects.nonNull(fixedCost) && Objects.nonNull(repayProportion))), "收取总额比例或者固定费用至少且只能选择填写一种");
    }
}
