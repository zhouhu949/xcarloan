package com.fintecher.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCustomerOrderMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.Status;
import com.fintecher.util.ZWDateUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCustomerOrderServiceImpl extends BaseServiceImpl<BasicCustomerOrder> implements BasicCustomerOrderService {

    @Autowired
    private SysSeqService sysSeqService;
    @Autowired
    private BasicCustomerOrderMapper basicCustomerOrderMapper;
    @Autowired
    private BasicCarModelService basicCarModelService;
    @Autowired
    private BasicRepaySchemeService basicRepaySchemeService;
    @Autowired
    private BasicCustomerCarService basicCustomerCarService;
    @Autowired
    private BasicStockCarService basicStockCarService;
    @Autowired
    private BasicOrderCarService basicOrderCarService;
    @Autowired
    private BasicCustomerIntentionService basicCustomerIntentionService;
    @Autowired
    private BasicOrderRecordService basicOrderRecordService;
    @Autowired
    private BasicProductService basicProductService;
    @Autowired
    private BasicProductRepayTemplateService basicProductRepayTemplateService;
    @Autowired
    private BasicOrderRepaySchemeService basicOrderRepaySchemeService;
    @Autowired
    private BasicCustomerService basicCustomerService;
    @Autowired
    private FinanceStorageService financeStorageService;
    @Autowired
    private BasicCustomerFollowService basicCustomerFollowService;

    @Override
    public Map<String, Object> findFinancingRepayDetail(Long productId) {
        return basicProductRepayTemplateService.repaymentSchedule(productId);
    }

    @Override
    public Map<String, Object> findMortgageRepayDetail(Long schemeId, BigDecimal amount) {
        return basicProductRepayTemplateService.repaymentSchedule(schemeId, amount);
    }

    @Override
    public void createFinancingOrder(SysUser user, CreateFinancingOrderParams createOrderParams) {
//        Long schemeId = createOrderParams.getSchemeId();
        Long productId = createOrderParams.getProductId();
        BasicProduct product = basicProductService.findById(productId);
        Long schemeId = product.getSchemeId();
        Long customerId = createOrderParams.getCustomerId();
        BasicRepayScheme repayScheme = basicRepaySchemeService.findById(schemeId);
        BasicCustomerOrder order = new BasicCustomerOrder();
        order.setOperator(user.getId());
        order.setOperatorTime(new Date());
        order.setOrderNo(orderNo());
        order.setOrgId(user.getOrgId());
        order.setDeptId(user.getDeptId());
        order.setCustomerId(customerId);
        order.setOrderRepayType(repayScheme.getRepayType());
        order.setOrderAccountDay(repayScheme.getAccountDay());
        order.setOrderCreditDays(repayScheme.getCreditDays());
        order.setOrderCycleType(repayScheme.getCycleType());
        order.setOrderInterestRate(repayScheme.getInterestRate());
        order.setOrderMortgageType(repayScheme.getMortgageType());
        order.setOrderOverdueDays(repayScheme.getOverdueDays());
        order.setOrderPeriods(repayScheme.getPeriods());
        order.setWorkFlowKey(repayScheme.getWorkFlowKey());
        Integer type = BasicRepayScheme.SchemeType.FINANCING.getValue();
        order.setOrderType(type);
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.UNFILLED_DATA.getValue());
        order.setSchemeId(schemeId);

        Long modelId = createOrderParams.getModelId();
        CarModelInfo carModel = basicCarModelService.findCarModel(modelId);
        order.setOrderPrice(carModel.getReferencePrice());
        List<ProductTemplateInfo> basicTemplateInfo = basicProductRepayTemplateService.findBasicTemplateInfo(productId);
        order.setOrderNowPeriods(basicTemplateInfo.stream().anyMatch(e -> Objects.equals(e.getPeriods(), 0)) ? 0 : 1);
        // 增加订单
        this.save(order);
        // 增加订单车型
        saveBasicOrderCar(user, carModel, order.getId());
        // 订单还款详情
        saveOrderRepaySchemeFanancing(user.getId(), order.getId(), basicTemplateInfo);
        // 添加订单操作记录
        saveBasicOrderRecord(user.getId(), order.getId());
        // 更新意向记录和跟进记录
        updateBasicCustomerIntention(user.getId(), customerId, order.getId(), type);
        // 更新客户状态
        updateCustomer(customerId);
    }

    @Override
    public void createMortgageOrder(SysUser user, CreateMortgageOrderParams createOrderParams) {
        Long schemeId = createOrderParams.getSchemeId();
        Long customerId = createOrderParams.getCustomerId();
        BasicRepayScheme repayScheme = basicRepaySchemeService.findById(schemeId);
        BasicCustomerOrder order = new BasicCustomerOrder();
        order.setOperator(user.getId());
        order.setOperatorTime(new Date());
        order.setOrderNo(orderNo());
        order.setOrgId(user.getOrgId());
        order.setDeptId(user.getDeptId());
        order.setCustomerId(customerId);
        order.setOrderRepayType(repayScheme.getRepayType());
        order.setOrderAccountDay(repayScheme.getAccountDay());
        order.setOrderCreditDays(repayScheme.getCreditDays());
        order.setOrderCycleType(repayScheme.getCycleType());
        order.setOrderInterestRate(repayScheme.getInterestRate());
        order.setOrderMortgageType(repayScheme.getMortgageType());
        order.setOrderOverdueDays(repayScheme.getOverdueDays());
        order.setOrderPeriods(repayScheme.getPeriods());
        order.setWorkFlowKey(repayScheme.getWorkFlowKey());
        Integer type = BasicRepayScheme.SchemeType.MORTGAGE.getValue();
        order.setOrderType(type);
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.UNFILLED_DATA.getValue());
        order.setSchemeId(schemeId);
        order.setOrderPrice(createOrderParams.getAmount());

        List<RepayTemplateModel> templateModels = basicProductRepayTemplateService.schemeExpenseInfo(schemeId, createOrderParams.getAmount());
        order.setOrderNowPeriods(templateModels.stream().anyMatch(e -> Objects.equals(e.getPeriods(), 0)) ? 0 : 1);
        // 增加订单
        this.save(order);
        // 更新客户车产状态
        Set<Long> carIds = createOrderParams.getCarIds();
        // 添加押品出入库记录
        saveFinanceStorage(user, carIds, order.getId());
        // 更新客户车产
        updateBasicCustomerCar(user.getId(), carIds, order.getId());
        // 创建订单还款计划详情

        saveOrderRepaySchemeMortgage(user.getId(), order.getId(), templateModels);
        // 添加订单操作记录
        saveBasicOrderRecord(user.getId(), order.getId());
        // 更新意向记录和跟进记录
        updateBasicCustomerIntention(user.getId(), customerId, order.getId(), type);
        // 更新客户状态
        updateCustomer(customerId);
    }

    @Override
    public List<OrderInfoModel> query(SearchOrderParams searchOrderParams, List<String> allDataAuth, List<String> allExceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(searchOrderParams, Map.class);
        map.put("dataAuth", allDataAuth);
        map.put("exceptDataAuth", allExceptDataAuth);
        return basicCustomerOrderMapper.query(map);
    }

    @Override
    public List<OrderInfoModel>findCustomerOrderFile(SysUser user, SearchOrderParams searchOrderParams){
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(searchOrderParams, Map.class);
        map.put("orgId", user.getOrgId());
        return basicCustomerOrderMapper.findCustomerOrderFile(map);
    }

    @Override
    public Integer selectOrderStatus(Long orderId) {

        return this.findById(orderId).getOrderStatus();
    }

    /**
     * 订单编号生成规则
     *
     * @return
     */
    private String orderNo() {
        String nextSeq = sysSeqService.nextSeq(Constants.SEQ_NAME_ORDER_NO, Constants.SEQ_LENGTH_ORDER_NO);
        String ymdDate = ZWDateUtil.getYMDDate();
        return String.format("%s%s", ymdDate, nextSeq);
    }

    /**
     * 增加订单车辆
     *
     * @param user   登陆人
     * @param carModel 车型信息
     * @param orderId  订单ID
     */
    private void saveBasicOrderCar(SysUser user, CarModelInfo carModel, Long orderId) {
        BasicOrderCar basicOrderCar = new BasicOrderCar();
        basicOrderCar.setOperator(user.getId());
        basicOrderCar.setOperatorTime(new Date());
        basicOrderCar.setOrderCarColor(carModel.getModelColors());
        basicOrderCar.setModelId(carModel.getModelId());
        basicOrderCar.setOrderId(orderId);
        List<Map> carModelConfig = carModel.getCarmodelParams();
        String carParamJsonString = JSON.toJSONString(carModelConfig);
        String orderCarDesc = String.format("%s-%s-%s", carModel.getBrandName(), carModel.getSeriesName(), carModel.getModelName());
        basicOrderCar.setOrderCarDesc(orderCarDesc);
        basicOrderCar.setOrderCarName(carModel.getModelName());
//        List<Map> carIntroduceList = carModel.getCarIntroduceList();
//        String carIntroduceJsonString = JSON.toJSONString(carIntroduceList);
        basicOrderCar.setOrderCarParamDesc(carParamJsonString);

        Example example = new Example(BasicStockCar.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("modelId", carModel.getModelId());
        criteria.andIsNull("orderId");
        List<BasicStockCar> basicStockCars = basicStockCarService.selectByExample(example);
        if (basicStockCars.isEmpty()) {
            // 增加车库车辆
            BasicStockCar basicStockCar = saveBasicStockCar(user, orderId, carModel.getModelId());
            // 订单车辆
            basicOrderCar.setHasSupplierLoan(Status.Enable.getValue());
            basicOrderCar.setIsSupplier(Status.Enable.getValue());
            basicOrderCar.setStockId(basicStockCar.getId());
            basicOrderCarService.save(basicOrderCar);
        } else {
            BasicStockCar basicStockCar = basicStockCars.get(0);
            // 增加订单车辆
            basicOrderCar.setHasSupplierLoan(Status.Disable.getValue());
            basicOrderCar.setIsSupplier(Status.Disable.getValue());
            basicOrderCar.setStockId(basicStockCar.getId());
            basicOrderCar.setSupplierId(basicOrderCar.getSupplierId());
            basicOrderCarService.save(basicOrderCar);
            // 更新车库车辆
            updateBasicStockCar(user.getId(), orderId, basicOrderCar.getId());
        }
    }

    /**
     * 新增车库车辆
     *
     * @param user
     * @param orderId
     * @param modelId
     */
    private BasicStockCar saveBasicStockCar(SysUser user, Long orderId, Long modelId) {
        BasicStockCar basicStockCar = new BasicStockCar();
        basicStockCar.setOrgId(user.getOrgId());
        basicStockCar.setHasSupplierLoan(BasicStockCar.HasSupplierLoan.BASIC_STOCKSTATUS_NO.getValue());
        basicStockCar.setModelId(modelId);
        basicStockCar.setOrderId(orderId);
        basicStockCar.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_DCG.getValue());
        basicStockCar.setOperator(user.getId());
        basicStockCar.setOperatorTime(new Date());
        basicStockCarService.save(basicStockCar);
        return basicStockCar;
    }

    /**
     * 更新车库车辆
     *
     * @param userId          登陆人ID
     * @param orderId         订单ID
     * @param basicOrderCarId 订单车辆ID
     */
    private void updateBasicStockCar(Long userId, Long orderId, Long basicOrderCarId) {
        BasicStockCar basicStockCar1 = new BasicStockCar();
        basicStockCar1.setId(basicOrderCarId);
        basicStockCar1.setOperator(userId);
        basicStockCar1.setOperatorTime(new Date());
        basicStockCar1.setOrderId(orderId);
        basicStockCar1.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue());
        basicStockCarService.updateSelective(basicStockCar1);
    }

    /**
     * 添加押品出入库记录
     *
     * @param user    登陆人
     * @param carIds  车产ID
     * @param orderId 订单ID
     */
    private void saveFinanceStorage(SysUser user, Set<Long> carIds, Long orderId) {
        List<FinanceStorage> financeStorageList = Lists.newArrayList();
        for (Long carId : carIds) {
            FinanceStorage financeStorage = new FinanceStorage();
            financeStorage.setCarId(carId);
            financeStorage.setMortgageStatus(FinanceStorage.MortgageStatus.WAIT.getValue());
            financeStorage.setOrderId(orderId);
            financeStorage.setOrgId(user.getOrgId());
            financeStorage.setRemark("创建订单时添加记录");
            financeStorage.setOperator(user.getId());
            financeStorage.setOperatorTime(new Date());
            financeStorageList.add(financeStorage);
        }
        financeStorageService.saveList(financeStorageList);
    }

    /**
     * 更新客户车产
     *
     * @param userId  登陆人ID
     * @param carIds  车产ID
     * @param orderId 订单ID
     */
    private void updateBasicCustomerCar(Long userId, Set<Long> carIds, Long orderId) {
        Example example = new Example(BasicCustomerCar.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", carIds);
        List<BasicCustomerCar> customerCars = basicCustomerCarService.selectByExample(example);
        for (BasicCustomerCar car : customerCars) {
            car.setOperator(userId);
            car.setOperatorTime(new Date());
            car.setOrderId(orderId);
            basicCustomerCarService.update(car);
        }
    }

    /**
     * 添加订单操作记录
     *
     * @param userId  登陆人ID
     * @param orderId 订单ID
     */
    private void saveBasicOrderRecord(Long userId, Long orderId) {
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
        basicOrderRecord.setOperator(userId);
        basicOrderRecord.setOperatorTime(new Date());
        basicOrderRecord.setLinkTableName("basic_customer_order");
        basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.APPLICATION.getValue());
        basicOrderRecord.setOrderStatus(BasicCustomerOrder.OrderStatus.UNFILLED_DATA.getValue());
        basicOrderRecord.setRelateId(orderId);
        basicOrderRecord.setRemark("创建订单");
        basicOrderRecord.setOrderId(orderId);
        basicOrderRecordService.save(basicOrderRecord);
    }

    /**
     * 更新意向记录和跟进记录
     *
     * @param userId     登陆人ID
     * @param customerId 客户ID
     * @param orderId    订单ID
     */
    private void updateBasicCustomerIntention(Long userId, Long customerId, Long orderId, Integer type) {
        Example example = new Example(BasicCustomerIntention.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("customerId", customerId);
        criteria.andEqualTo("intentionType", type);
        criteria.andNotEqualTo("intentionStatus", BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGQ.getValue());
        criteria.andNotEqualTo("intentionStatus", BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YWC.getValue());
        criteria.andNotEqualTo("intentionStatus", BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YXD.getValue());
        List<BasicCustomerIntention> basicCustomerIntentions = basicCustomerIntentionService.selectByExample(example);
        if (basicCustomerIntentions.isEmpty()) {
            BasicCustomerIntention intention = new BasicCustomerIntention();
            intention.setIntentionStatus(BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YXD.getValue());
            intention.setOrderId(orderId);
            intention.setCustomerId(customerId);
            intention.setIntentionType(type);
            intention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.TRUE.getValue());
            intention.setOperator(userId);
            intention.setOperatorTime(new Date());
            basicCustomerIntentionService.save(intention);
            // 添加跟进记录
            BasicCustomerFollow basicCustomerFollow = new BasicCustomerFollow();
            basicCustomerFollow.setFollowDate(new Date());
            basicCustomerFollow.setOperator(userId);
            basicCustomerFollow.setUserId(userId);
            basicCustomerFollow.setIntentionId(intention.getId());
            basicCustomerFollow.setFollowResult(BasicCustomerFollow.FollowResult.BASIC_FOLLOWRESULT_GJZG.getValue());
            basicCustomerFollow.setFollowType(BasicCustomerFollow.FollowType.BASIC_FOLLOWTYPE_FACETALL.getValue());
            basicCustomerFollow.setIsLastIntention(BasicCustomerFollow.IsLastIntention.TRUE.getValue());
            basicCustomerFollowService.save(basicCustomerFollow);
        } else {
            basicCustomerIntentions.forEach(e -> {
                BasicCustomerIntention intention = new BasicCustomerIntention();
                intention.setId(e.getId());
                intention.setIntentionStatus(BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YXD.getValue());
                intention.setOrderId(orderId);
                intention.setOperator(userId);
                intention.setOperatorTime(new Date());
                basicCustomerIntentionService.updateSelective(intention);

                // 若对应的有跟进记录 则更新为不是最后一条 然后添加一条新的 这里的代码待优化
                BasicCustomerFollow follow = new BasicCustomerFollow();
                follow.setIntentionId(intention.getId());
                List<BasicCustomerFollow> listByWhere = basicCustomerFollowService.findListByWhere(follow);
                listByWhere.forEach(f -> {
                    BasicCustomerFollow follow1 = new BasicCustomerFollow();
                    follow1.setIsLastIntention(BasicCustomerFollow.IsLastIntention.FALSE.getValue());
                    basicCustomerFollowService.updateSelective(follow1);
                });

                // 添加跟进记录
                BasicCustomerFollow basicCustomerFollow = new BasicCustomerFollow();
                basicCustomerFollow.setFollowDate(new Date());
                basicCustomerFollow.setOperator(userId);
                basicCustomerFollow.setUserId(userId);
                basicCustomerFollow.setIntentionId(intention.getId());
                basicCustomerFollow.setFollowResult(BasicCustomerFollow.FollowResult.BASIC_FOLLOWRESULT_GJZG.getValue());
                basicCustomerFollow.setFollowType(BasicCustomerFollow.FollowType.BASIC_FOLLOWTYPE_FACETALL.getValue());
                basicCustomerFollow.setIsLastIntention(BasicCustomerFollow.IsLastIntention.TRUE.getValue());
                basicCustomerFollowService.save(basicCustomerFollow);
            });
        }
    }

    /**
     * 更新客户状态
     *
     * @param customerId
     */
    private void updateCustomer(Long customerId) {
        BasicCustomer customer = new BasicCustomer();
        customer.setId(customerId);
        customer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_ZSKH.getValue());
        basicCustomerService.updateSelective(customer);
    }

    /**
     * 融资租赁添加订单还款详情
     * @param userId
     * @param orderId
     * @param templateList
     */
    private void saveOrderRepaySchemeFanancing(Long userId, Long orderId, List<ProductTemplateInfo> templateList) {
        List<BasicOrderRepayScheme> repaySchemes = Lists.newArrayList();
        for (ProductTemplateInfo template : templateList) {
            BasicOrderRepayScheme basicOrderRepayScheme = new BasicOrderRepayScheme();
            BeanUtils.copyProperties(template, basicOrderRepayScheme);
            basicOrderRepayScheme.setRepayTemplateId(template.getId());
            basicOrderRepayScheme.setOperator(userId);
            basicOrderRepayScheme.setOperatorTime(new Date());
            basicOrderRepayScheme.setOrderId(orderId);
            basicOrderRepayScheme.setRemark("创建订单时生成的订单还款详情");
            basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.WAIT.getValue());
            repaySchemes.add(basicOrderRepayScheme);
        }
        basicOrderRepaySchemeService.saveList(repaySchemes);
    }

    /**
     * 抵押贷款生成的还款详情
     *
     * @param userId   登陆用户ID
     * @param orderId  订单ID
     * @param templateModels 还款详情
     */
    private void saveOrderRepaySchemeMortgage(Long userId, Long orderId, List<RepayTemplateModel> templateModels) {
        List<BasicOrderRepayScheme> repaySchemes = Lists.newArrayList();
        for (RepayTemplateModel info : templateModels) {
            BasicOrderRepayScheme basicOrderRepayScheme = new BasicOrderRepayScheme();
            BeanUtils.copyProperties(info, basicOrderRepayScheme);
            basicOrderRepayScheme.setOperator(userId);
            basicOrderRepayScheme.setOperatorTime(new Date());
            basicOrderRepayScheme.setOrderId(orderId);
            basicOrderRepayScheme.setRemark("创建订单时生成的订单还款详情");
            basicOrderRepayScheme.setRepayStatus(BasicOrderRepayScheme.RepayStatus.WAIT.getValue());
            repaySchemes.add(basicOrderRepayScheme);
        }
        basicOrderRepaySchemeService.saveList(repaySchemes);
    }
}
