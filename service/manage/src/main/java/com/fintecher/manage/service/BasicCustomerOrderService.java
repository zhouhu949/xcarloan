package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.CreateFinancingOrderParams;
import com.fintecher.manage.vo.CreateMortgageOrderParams;
import com.fintecher.manage.vo.OrderInfoModel;
import com.fintecher.manage.vo.SearchOrderParams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BasicCustomerOrderService extends BaseService<BasicCustomerOrder> {

    /**
     * 创建融资租赁订单
     * @param user
     * @param createOrderParams
     */
    void createFinancingOrder(SysUser user, CreateFinancingOrderParams createOrderParams);

    /**
     * 创建抵押贷款订单
     * @param user
     * @param createOrderParams
     */
    void createMortgageOrder(SysUser user, CreateMortgageOrderParams createOrderParams);

    List<OrderInfoModel> query(SearchOrderParams searchOrderParams, List<String> allDataAuth, List<String> allExceptDataAuth);

    Map<String, Object> findFinancingRepayDetail(Long productId);

    Map<String, Object> findMortgageRepayDetail(Long schemeId, BigDecimal amount);
    List<OrderInfoModel>findCustomerOrderFile(SysUser user, SearchOrderParams searchOrderParams);

    Integer selectOrderStatus(Long orderId);
}
