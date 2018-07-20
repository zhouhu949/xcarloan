package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicRepaySchemeMapper;
import com.fintecher.manage.service.BasicExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.Status;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicRepaySchemeServiceImpl extends BaseServiceImpl<BasicRepayScheme> implements BasicRepaySchemeService {

    @Resource
    private BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Resource
    private BasicRepaySchemeMapper basicRepaySchemeMapper;
    @Resource
    private BasicExpenseService basicExpenseService;

    @Override
    public void deleteBasicRepaySchemeById(Long id) {
        // 删除掉关联还款方案的还款方案比例详情
        BasicRepaySchemeExpense basicRepaySchemeExpense = new BasicRepaySchemeExpense();
        basicRepaySchemeExpense.setSchemeId(id);
        basicRepaySchemeExpenseService.deleteByWhere(basicRepaySchemeExpense);
        // 删除还款方案
        this.deleteById(id);
    }

    @Override
    public void addBasicRepayScheme(SysUser user, RepaySchemeParams addRepayScheme) {
        // 添加还款方案
        BasicRepayScheme basicRepayScheme = new BasicRepayScheme();
        BeanUtils.copyProperties(addRepayScheme, basicRepayScheme);
        BigDecimal interestRate = addRepayScheme.getInterestRate();
        if (Objects.nonNull(interestRate)) {
            basicRepayScheme.setInterestRate(interestRate.divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP));
        }
        basicRepayScheme.setOrgId(user.getOrgId());
        basicRepayScheme.setOperator(user.getId());
        basicRepayScheme.setOperatorTime(new Date());
        basicRepayScheme.setSchemeStatus(BasicRepayScheme.SchemeStatus.ENABLE.getValue());
        if (Objects.isNull(addRepayScheme.getId())) {
            // 新增还款方案详情
            this.save(basicRepayScheme);
            Long id = basicRepayScheme.getId();
            // 默认添加必须需要的相关还款方案比例详情
            Example example = new Example(BasicExpense.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orgId", user.getOrgId());
            if (Objects.equals(addRepayScheme.getSchemeType(), BasicRepayScheme.SchemeType.FINANCING.getValue())) {
                criteria.andIn("expenseCode", Constants.FINANCING_EXPENSE_CODE);
            } else {
                criteria.andIn("expenseCode", Constants.MORTGAGE_EXPENSE_CODE);
            }
            List<BasicExpense> expenseList = basicExpenseService.selectByExample(example);
            List<BasicRepaySchemeExpense> basicRepaySchemeExpenseList = Lists.newArrayList();
            for (BasicExpense params : expenseList) {
                BasicRepaySchemeExpense expense = new BasicRepaySchemeExpense();
                BeanUtils.copyProperties(params, expense);
                expense.setExpenseId(params.getId());
                expense.setOperatorTime(new Date());
                expense.setOperator(user.getId());
                expense.setSchemeId(id);
                basicRepaySchemeExpenseValue(params, expense, addRepayScheme);
                expense.setRemark("默认添加的还款比例详情");
                basicRepaySchemeExpenseList.add(expense);
            }
            basicRepaySchemeExpenseService.saveList(basicRepaySchemeExpenseList);
        } else {
            // 修改还款方案详情 不改变还款方案比例详情
            this.updateSelective(basicRepayScheme);
        }
    }

    @Override
    public List<BasicRepayMenuModel> findAllBasicRepaySchemeByAuth(List<String> allDataAuth, List<String> allExceptDataAuth) {
        Map<String, List<String>> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", allDataAuth);
        paramsMap.put("exceptDataAuth", allExceptDataAuth);
        return basicRepaySchemeMapper.findAllBasicRepaySchemeByAuth(paramsMap);
    }

    @Override
    public List<String> findRepaySchemeExpenseCode(Long schemeId) {
        return basicRepaySchemeMapper.findRepaySchemeExpenseCode(schemeId);
    }

    @Override
    public SchemeInfoModel findSchemeInfo(Long schemeId) {
        return basicRepaySchemeMapper.findSchemeInfo(schemeId);
    }

    /**
     * 默认费用项设置
     * @param basicExpense
     * @param expense
     * @param addRepayScheme
     */
    private void basicRepaySchemeExpenseValue(BasicExpense basicExpense, BasicRepaySchemeExpense expense, RepaySchemeParams addRepayScheme) {
        String expenseCode = basicExpense.getExpenseCode();
        BasicExpense.ExpenseCode value = BasicExpense.ExpenseCode.value(expenseCode);
        if (value == null) {
            return;
        }
        switch (value) {
            case BJ: // 本金
                if (addRepayScheme.getPeriods() > 1) {
                    expense.setRepayType(BasicRepaySchemeExpense.RepayType.MULTIPLE.getValue());
                } else {
                    expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                }
                expense.setFixedCost(new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP)); // 本金不按这个算
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Disable.getValue());
                return;
            case LX: // 利息
                if (addRepayScheme.getPeriods() > 1) {
                    expense.setRepayType(BasicRepaySchemeExpense.RepayType.MULTIPLE.getValue());
                } else {
                    expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                }
                BigDecimal interestRate = addRepayScheme.getInterestRate();
                if (Objects.nonNull(interestRate)) {
                    expense.setRepayProportion(interestRate.divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP));
                } else {
                    expense.setRepayProportion(new BigDecimal(0).setScale(8, BigDecimal.ROUND_HALF_UP)); // 利息不按这个算
                }
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Disable.getValue());
                return;
            case FX: // 罚息
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.MULTIPLE.getValue());
                expense.setRepayProportion(new BigDecimal(0).setScale(8, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Disable.getValue());
                return;
            case GLF: // 管理费
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setFixedCost(new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Enable.getValue());
                return;
            case GPSFY: // GPS费用
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setFixedCost(new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Enable.getValue());
                return;
            case YJ: // 押金
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setFixedCost(new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Enable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Enable.getValue());
                return;
            case TQJQSXF: // 提前结清手续费
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setFixedCost(new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Enable.getValue());
                expense.setIsFirst(Status.Disable.getValue());
                return;
            case FJ: // 罚金
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setRepayProportion(new BigDecimal(0).setScale(8, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Disable.getValue());
                return;
            case SFK: // 首付款
                expense.setRepayType(BasicRepaySchemeExpense.RepayType.ONCE.getValue());
                expense.setRepayProportion(new BigDecimal(0).setScale(8, BigDecimal.ROUND_HALF_UP));
                expense.setIsRefund(Status.Disable.getValue());
                expense.setIsLast(Status.Disable.getValue());
                expense.setIsFirst(Status.Enable.getValue());
            default:
        }
    }

    @Override
    public SchemeAndExpenseInfo findSchemeAndExpenseInfo(Long schemeId) {
        return basicRepaySchemeMapper.findSchemeAndExpenseInfo(schemeId);
    }
    @Override
    public RepaySchemeExpenseModel getEarlySettlementScheme(Long scheme, String expenseCode){
            Map map =new HashMap();
            map.put("schemeId",scheme);
            map.put("expenseCode",expenseCode);
        return basicRepaySchemeMapper.getEarlySettlementScheme(map);
    }
}
