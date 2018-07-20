package com.fintecher.manage.service.impl;

import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicProductRepayTemplateMapper;
import com.fintecher.manage.mapper.CarModelMapper;
import com.fintecher.manage.service.BasicProductRepayTemplateService;
import com.fintecher.manage.service.BasicProductService;
import com.fintecher.manage.service.BasicRepaySchemeExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeService;
import com.fintecher.manage.util.FinanceUtil;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Status;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicProductRepayTemplateServiceImpl extends BaseServiceImpl<BasicProductRepayTemplate> implements BasicProductRepayTemplateService {
    @Resource
    private BasicProductRepayTemplateMapper basicProductRepayTemplateMapper;
    @Resource
    private CarModelMapper basicCarModelMapper;
    @Resource
    private BasicRepaySchemeService basicRepaySchemeService;
    @Resource
    private BasicProductService basicProductService;
    @Autowired
    private BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BasicRepaySchemeExpense> selectRepaySchemeExpense(Long schemeId) {
        return basicProductRepayTemplateMapper.selectRepaySchemeExpense(schemeId);
    }


    /**
     * 添加车型生成模板
     */
    @Override
    public void addCarScheme(BasicProductRepayTemplate template) {
        List<BasicProductRepayTemplate> list = new ArrayList<>();
        BasicProduct basicProduct = basicProductService.findById(template.getProductId());
        BasicCarModel carModel = basicCarModelMapper.selectByPrimaryKey(basicProduct.getConfigId());//车型
        Long schemeId = basicProduct.getSchemeId();
        BigDecimal amount = carModel.getReferencePrice();//还款本金总额
        SchemeAndExpenseInfo repayScheme = basicRepaySchemeService.findSchemeAndExpenseInfo(schemeId);
        Integer periods = repayScheme.getPeriods();
        BigDecimal interestRate = repayScheme.getInterestRate();
        Integer schemeRepayType = repayScheme.getRepayType();
        List<RepaySchemeExpenseModel> repaySchemeExpenseModelList = repayScheme.getSchemeExpenseModelList();

        // 处理有首付款还款项的本金
        RepaySchemeExpenseModel expenseModel = repaySchemeExpenseModelList.stream()
                .filter(e -> Objects.equals(BasicExpense.ExpenseCode.SFK.getValue(), e.getExpenseCode()))
                .findFirst().orElse(null);
        BigDecimal sfk = new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP);
        if (expenseModel != null) {
            BigDecimal proportion = expenseModel.getRepayProportion();
            sfk = FinanceUtil.firstPay(amount, proportion);
            amount = amount.subtract(sfk);
        }

        for (RepaySchemeExpenseModel expense : repaySchemeExpenseModelList) {
            String expenseCode = expense.getExpenseCode();
            Long expenseId = expense.getId();
            Integer isFirst = expense.getIsFirst();
            Integer isLast = expense.getIsLast();
            Integer isRefund = expense.getIsRefund();
            BigDecimal fixedCost = expense.getFixedCost();
            BigDecimal repayProportion = expense.getRepayProportion();
            // 本金 或者 利息
            if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())
                    || Objects.equals(expenseCode, BasicExpense.ExpenseCode.LX.getValue())) {
                // 按期付息还本
                if (Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue())) {
                    // 本金
                    if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())) {
                        BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                        BeanUtils.copyProperties(template, template1);
                        template1.setRepayMoney(amount);
                        template1.setRepaySchemeExpenseId(expenseId);
                        template1.setPeriods(1);
                        list.add(template1);
                    } else {
                        // 利息
                        BigDecimal interest = FinanceUtil.payInterestOnSchedule(amount, periods, interestRate);
                        BasicProductRepayTemplate repayTemplate = new BasicProductRepayTemplate();
                        BeanUtils.copyProperties(template, repayTemplate);
                        repayTemplate.setRepayMoney(interest);
                        repayTemplate.setRepaySchemeExpenseId(expenseId);
                        repayTemplate.setPeriods(1);
                        list.add(repayTemplate);
                    }
                } else if (Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_PRIN.getValue())) {
                    // 等额本金
                    if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())) {
                        BigDecimal principal = FinanceUtil.equalPrincipalPeriod(amount, periods);
                        for (int i = 1; i <= periods; i++) {
                            // 本金
                            BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                            BeanUtils.copyProperties(template, template1);
                            template1.setRepayMoney(principal);
                            template1.setRepaySchemeExpenseId(expenseId);
                            template1.setPeriods(i);
                            list.add(template1);
                        }
                    } else {
                        BigDecimal principal = FinanceUtil.equalPrincipalPeriod(amount, periods);
                        BigDecimal repayments = new BigDecimal(0);
                        // 利息
                        for (int i = 1; i <= periods; i++) {
                            BigDecimal interest = FinanceUtil.equalPrincipalInterest(amount, repayments, interestRate);
                            BasicProductRepayTemplate repayTemplate = new BasicProductRepayTemplate();
                            BeanUtils.copyProperties(template, repayTemplate);
                            repayTemplate.setRepayMoney(interest);
                            repayTemplate.setRepaySchemeExpenseId(expenseId);
                            repayTemplate.setPeriods(i);
                            list.add(repayTemplate);
                            repayments = repayments.add(principal);
                        }
                    }

                } else {
                    // 等额本息
                    if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())) {
                        BigDecimal principle = amount;
                        BigDecimal bigDecimal = FinanceUtil.eachPayment(amount, periods, interestRate);
                        for (int i = 1; i <= periods; i++) {
                            BigDecimal interest = FinanceUtil.averageCapitalPlusInterest(principle, repayScheme.getInterestRate());
                            BigDecimal principle1 = FinanceUtil.principle(bigDecimal, interest);
                            // 本金
                            BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                            BeanUtils.copyProperties(template, template1);
                            template1.setRepayMoney(principle1);
                            template1.setRepaySchemeExpenseId(expenseId);
                            template1.setPeriods(i);
                            list.add(template1);
                            principle = principle.subtract(principle1);

                        }
                    } else {
                        // 利息
                        BigDecimal principle = amount;
                        BigDecimal bigDecimal = FinanceUtil.eachPayment(amount, periods, interestRate);
                        for (int i = 1; i <= periods; i++) {
                            BigDecimal interest = FinanceUtil.averageCapitalPlusInterest(principle, repayScheme.getInterestRate());
                            BigDecimal principle1 = FinanceUtil.principle(bigDecimal, interest);
                            BasicProductRepayTemplate repayTemplate = new BasicProductRepayTemplate(i, interest);
                            repayTemplate.setProductId(template.getProductId());
                            repayTemplate.setRemark(template.getRemark());
                            repayTemplate.setOperator(template.getOperator());
                            repayTemplate.setOperatorTime(new Date());
                            repayTemplate.setRepayMoney(interest);
                            repayTemplate.setRepaySchemeExpenseId(expenseId);
                            repayTemplate.setPeriods(i);
                            list.add(repayTemplate);
                            // 本金
                            principle = principle.subtract(principle1);
                        }
                    }
                }
            } else if (Objects.equals(isFirst, Status.Enable.getValue())) { // 是首付款 必须是一次行收取
                // 费用项是首付款
                if (Objects.equals(BasicExpense.ExpenseCode.SFK.getValue(), expenseCode)) {
                    BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                    BeanUtils.copyProperties(template, template1);
                    template1.setRepayMoney(sfk);
                    template1.setRepaySchemeExpenseId(expenseId);
                    template1.setPeriods(0);
                    list.add(template1);
                } else {
                    // 是按照固定费用收取 是按照比例
                    BigDecimal money = Objects.nonNull(fixedCost) ? fixedCost : amount.multiply(repayProportion);
                    BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                    BeanUtils.copyProperties(template, template1);
                    template1.setRepayMoney(money);
                    template1.setRepaySchemeExpenseId(expenseId);
                    template1.setPeriods(0);
                    list.add(template1);
                    // 是退款
                    if (Objects.equals(isRefund, Status.Enable.getValue())) {
                        BasicProductRepayTemplate template2 = new BasicProductRepayTemplate();
                        int p = Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue()) ? 2 : periods + 1;
                        BeanUtils.copyProperties(template, template2);
                        template2.setRepayMoney(money.negate());
                        template2.setRepaySchemeExpenseId(expenseId);
                        template2.setPeriods(p);
                        list.add(template2);
                    }
                }
            } else if (Objects.equals(isLast, Status.Enable.getValue())) { // 是尾款 必须是一次行收取
                int p = Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue()) ? 1 : periods;
                // 是按照固定费用收取 是按照比例
                BigDecimal money = Objects.nonNull(fixedCost) ? fixedCost : amount.multiply(repayProportion);
                BasicProductRepayTemplate template1 = new BasicProductRepayTemplate();
                BeanUtils.copyProperties(template, template1);
                template1.setRepayMoney(money);
                template1.setRepaySchemeExpenseId(expenseId);
                template1.setPeriods(p);
                list.add(template1);
            } else { // 分期收取的
                // 是按照固定费用收取 是按照比例
                //排除罚息罚金，提前接结清等等费用项
                if (!Objects.equals(expenseCode, BasicExpense.ExpenseCode.FX.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.FJ.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.TQJQSXF.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.JMBJ.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.JMLX.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.DJFX.getValue())) {
                    // 是按照固定费用收取 是按照比例
                    BigDecimal money = Objects.nonNull(fixedCost) ?
                            fixedCost.divide(new BigDecimal(periods), 4, BigDecimal.ROUND_HALF_UP)
                            : amount.multiply(repayProportion).divide(new BigDecimal(periods), 4, BigDecimal.ROUND_HALF_UP);
                    for (int i = 1; i <= periods; i++) {
                        BasicProductRepayTemplate template1 = new BasicProductRepayTemplate(i, money);
                        BeanUtils.copyProperties(template, template1);
                        template1.setRepaySchemeExpenseId(expenseId);
                        list.add(template1);
                    }
                }
            }
        }
        this.saveList(list);
    }

    @Override
    public List<ProductTemplateInfo> findBasicTemplateInfo(Long productId) {
        return basicProductRepayTemplateMapper.findBasicTemplateInfo(productId);
    }

    @Override
    public void deleteByProductId(Long productId) {

        basicProductRepayTemplateMapper.deleteByProductId(productId);
    }

    @Override
    public List<Map> selectTemplate(Long id) {
        List<Map> maps = basicProductRepayTemplateMapper.selectExpense(id);
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", id.toString());
        map.put("keyList", maps);
        return basicProductRepayTemplateMapper.selectTemplate(map);
    }

    @Override
    public Map<String, Object> repaymentSchedule(Long schemeId, BigDecimal amount) {
        List<RepayTemplateModel> scheme = schemeExpenseInfo(schemeId, amount);
        BasicRepayScheme repayScheme = basicRepaySchemeService.findById(schemeId);
        // 根据还款方案查询所有的费用项
        List<RepayExpenseInfoModel> schemeExpenseInfo = basicRepaySchemeExpenseService.findRepaySchemeExpenseInfo(schemeId);
        // 返回的表头信息
        Map<String, String> header = Maps.newLinkedHashMap();
        header.put("periods", "期数");
        // 初始化每个返回的数据
        Map<String, Object> exampleMap = Maps.newHashMap();
        schemeExpenseInfo.forEach(e -> {
            exampleMap.put(e.getExpenseCode(), 0);
            header.put(e.getExpenseCode(), e.getExpenseName());
        });
        // 最终返回页面展示的表数据
        return dataList(scheme, exampleMap, header, repayScheme);
    }


    @Override
    public Map<String, Object> repaymentSchedule(Long productId) {
        List<ProductTemplateInfo> basicTemplateInfo = findBasicTemplateInfo(productId);
        BasicProduct product = basicProductService.findById(productId);
        BasicRepayScheme repayScheme = basicRepaySchemeService.findById(product.getSchemeId());
        // 返回的表头信息
        Map<String, String> header = Maps.newLinkedHashMap();
        header.put("periods", "期数");
        // 初始化每个返回的数据
        Map<String, Object> exampleMap = Maps.newHashMap();
        basicTemplateInfo.forEach(e -> {
            exampleMap.put(e.getExpenseCode(), 0);
            header.put(e.getExpenseCode(), e.getExpenseName());
        });
        List<RepayTemplateModel> repayTemplateModelList = basicTemplateInfo.stream().map(e -> modelMapper.map(e, RepayTemplateModel.class)).collect(Collectors.toList());
        BeanUtils.copyProperties(basicTemplateInfo, repayTemplateModelList);
        return dataList(repayTemplateModelList, exampleMap, header, repayScheme);
    }

    /**
     * 费用项展示数据
     *
     * @param repayTemplateModelList
     * @param exampleMap
     * @return
     */
    private Map<String, Object> dataList(List<RepayTemplateModel> repayTemplateModelList, Map<String, Object> exampleMap, Map<String, String> header, BasicRepayScheme repayScheme) {
        int repayPeriods = repayScheme.getPeriods();
        Integer repayType = repayScheme.getRepayType();
        // 最终返回页面展示的表数据
        List<Map<String, Object>> dataList = Lists.newArrayList();
        for (RepayTemplateModel template : repayTemplateModelList) {
            int periods = template.getPeriods();
            boolean flag = false;
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> map = dataList.get(i);
                int mapPeriods = (int) map.get("periods");
                if (Objects.equals(mapPeriods, periods)) {
                    map.put(template.getExpenseCode(), template.getRepayMoney());
                    dataList.set(i, map);
                    flag = true;
                    break;
                }
            }
            // 该期数不存在
            if (!flag) {
                Map<String, Object> map = Maps.newHashMap(exampleMap);
                map.put("periods", periods);
                map.put(template.getExpenseCode(), template.getRepayMoney());
                dataList.add(map);
            }
        }
        Map<String, Object> result = Maps.newHashMap();
        if (!dataList.isEmpty()) {
            Comparator comparator = ComparableComparator.getInstance();
            ArrayList<Object> sortFields = new ArrayList<>();
            sortFields.add(new BeanComparator<Map<String, Object>>("periods", comparator));
            dataList.sort(new ComparatorChain(sortFields));
            Map<String, Object> stringObjectMap = dataList.get(0);
            if ((int) stringObjectMap.get("periods") == 0) {
                stringObjectMap.put("periods", "首付款");
                dataList.set(0, stringObjectMap);
            }
            Map<String, Object> objectMap = dataList.get(dataList.size() - 1);
            // 如果时按期付息还本
            if (Objects.equals(repayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue())) {
                if ((int) objectMap.get("periods") != 1) {
                    objectMap.put("periods", "退款");
                    dataList.set(dataList.size() - 1, objectMap);
                }
            } else {
                if ((int) objectMap.get("periods") == repayPeriods + 1) {
                    objectMap.put("periods", "退款");
                    dataList.set(dataList.size() - 1, objectMap);
                }
            }
            result.put("header", header);
            result.put("dataList", dataList);
        }
        return result;
    }


    @Override
    public List<RepayTemplateModel> schemeExpenseInfo(Long schemeId, BigDecimal amount) {
        SchemeAndExpenseInfo repayScheme = basicRepaySchemeService.findSchemeAndExpenseInfo(schemeId);
        List<RepayTemplateModel> resultList = Lists.newArrayList();
        Integer periods = repayScheme.getPeriods();
        BigDecimal interestRate = repayScheme.getInterestRate();
        Integer schemeRepayType = repayScheme.getRepayType();
        List<RepaySchemeExpenseModel> repaySchemeExpenseModelList = repayScheme.getSchemeExpenseModelList();
        // 处理有首付款还款项的本金
        RepaySchemeExpenseModel expenseModel = repaySchemeExpenseModelList.stream()
                .filter(e -> Objects.equals(BasicExpense.ExpenseCode.SFK.getValue(), e.getExpenseCode()))
                .findFirst().orElse(null);
        BigDecimal sfk = new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP);
        if (expenseModel != null) {
            BigDecimal proportion = expenseModel.getRepayProportion();
            sfk = FinanceUtil.firstPay(amount, proportion);
            amount = amount.subtract(sfk);
        }
        for (RepaySchemeExpenseModel expense : repaySchemeExpenseModelList) {
            String expenseCode = expense.getExpenseCode();
            String expenseName = expense.getExpenseName();
            Long expenseId = expense.getExpenseId();
            Integer isFirst = expense.getIsFirst();
            Integer isLast = expense.getIsLast();
            Integer isRefund = expense.getIsRefund();
            BigDecimal fixedCost = expense.getFixedCost();
            BigDecimal repayProportion = expense.getRepayProportion();
            // 本金 或者 利息
            if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())
                    || Objects.equals(expenseCode, BasicExpense.ExpenseCode.LX.getValue())) {
                // 按期付息还本
                if (Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue())) {
                    BigDecimal tempAmount = Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())
                            ? amount : FinanceUtil.payInterestOnSchedule(amount, periods, interestRate);
                    resultList.add(new RepayTemplateModel(1, tempAmount, expenseId, expenseName, expenseCode));
                } else if (Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_PRIN.getValue())) {
                    // 等额本金
                    if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())) {
                        BigDecimal principal = FinanceUtil.equalPrincipalPeriod(amount, periods);
                        for (int i = 1; i <= periods; i++) {
                            // 本金
                            resultList.add(new RepayTemplateModel(i, principal, expenseId, expenseName, expenseCode));
                        }
                    } else {
                        BigDecimal principal = FinanceUtil.equalPrincipalPeriod(amount, periods);
                        BigDecimal repayments = new BigDecimal(0);
                        // 利息
                        for (int i = 1; i <= periods; i++) {
                            BigDecimal interest = FinanceUtil.equalPrincipalInterest(amount, repayments, interestRate);
                            resultList.add(new RepayTemplateModel(i, interest, expenseId, expenseName, expenseCode));
                            repayments = repayments.add(principal);
                        }
                    }
                } else {
                    // 等额本息
                        BigDecimal principle = amount;
                        BigDecimal bigDecimal = FinanceUtil.eachPayment(amount, periods, interestRate);
                        for (int i = 1; i <= periods; i++) {
                            BigDecimal interest = FinanceUtil.averageCapitalPlusInterest(principle, repayScheme.getInterestRate());
                            BigDecimal principle1 = FinanceUtil.principle(bigDecimal, interest);
                            if (Objects.equals(expenseCode, BasicExpense.ExpenseCode.BJ.getValue())) {
                                resultList.add(new RepayTemplateModel(i, principle1, expenseId, expenseName, expenseCode));
                            } else {
                                resultList.add(new RepayTemplateModel(i, interest, expenseId, expenseName, expenseCode));
                            }
                            principle = principle.subtract(principle1);
                        }
                }
            } else if (Objects.equals(isFirst, Status.Enable.getValue())) { // 是首付款 必须是一次行收取
                // 费用项是首付款
                if (Objects.equals(BasicExpense.ExpenseCode.SFK.getValue(), expenseCode)) {
                    resultList.add(new RepayTemplateModel(0, sfk, expenseId, expenseName, expenseCode));
                } else {
                    // 是按照固定费用收取 是按照比例
                    BigDecimal money = Objects.nonNull(fixedCost) ? fixedCost : amount.multiply(repayProportion);
                    resultList.add(new RepayTemplateModel(0, money, expenseId, expenseName, expenseCode));
                    // 是退款
                    if (Objects.equals(isRefund, Status.Enable.getValue())) {
                        int p = Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue()) ? 2 : periods + 1;
                        resultList.add(new RepayTemplateModel(p, money.negate(), expenseId, expenseName, expenseCode));
                    }
                }
            } else if (Objects.equals(isLast, Status.Enable.getValue())) { // 是尾款 必须是一次行收取
                int p = Objects.equals(schemeRepayType, BasicRepayScheme.RepayType.PAYMENT_SCHEDULE.getValue()) ? 1 : periods;
                // 是按照固定费用收取 是按照比例
                BigDecimal money = Objects.nonNull(fixedCost) ? fixedCost : amount.multiply(repayProportion);
                resultList.add(new RepayTemplateModel(p, money, expenseId, expenseName, expenseCode));
            } else { // 分期收取的
                //排除罚息罚金，提前接结清等等费用项
                if (!Objects.equals(expenseCode, BasicExpense.ExpenseCode.FX.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.FJ.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.TQJQSXF.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.JMBJ.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.JMLX.getValue()) &&
                        !Objects.equals(expenseCode, BasicExpense.ExpenseCode.DJFX.getValue())) {
                    // 是按照固定费用收取 是按照比例
                    BigDecimal money = Objects.nonNull(fixedCost) ? fixedCost.divide(new BigDecimal(periods), 4, BigDecimal.ROUND_HALF_UP) : amount.multiply(repayProportion).divide(new BigDecimal(periods), 4, BigDecimal.ROUND_HALF_UP);
                    for (int i = 1; i <= periods; i++) {
                        resultList.add(new RepayTemplateModel(i, money, expenseId, expenseName, expenseCode));
                    }
                }

            }
        }
        return resultList;
    }

}
