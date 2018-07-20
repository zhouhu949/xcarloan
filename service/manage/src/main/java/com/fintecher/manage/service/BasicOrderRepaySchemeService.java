package com.fintecher.manage.service;

import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BasicOrderRepaySchemeService extends BaseService<BasicOrderRepayScheme> {


    /**+
     * 根据订单id，以及客户款本期总还款金额修改信息
     * @param orderId 订单id
     * @param money 客户本期交的还款金额
     */
    void editOrderRepaySchemeRepay(Long orderId,Integer periods, BigDecimal money,SysUser user);

    /**
     * 传入订单Id，期数
     * 查询客户剩余应还总额
     * @return
     */
    BigDecimal selectByRepay(Long orderId ,Integer periods);

    BigDecimal selectBalance(Long orderId);

    /**
     * 提前结清查询
     * @param orderId
     * @return
     */
    List<PrepaymentModel> selectDetails(Long orderId);

    BigDecimal selectRepayMoney(Long orderId);

    void updateRepayMoney(Long orderId, SysUser user);

    List<GatheringModel> getGatheringModel(Long orderId);

    /**
     * 首付款收款订单查询
     * @return
     */
    List<ReceiptOrder> selectReceiptOrder(SearchOrderParams orderParams);

    /**
     * 订单的模糊查询
     * @param orderParams
     * @return
     */
    List<OrderInfoModel> queryByStatus(SysUser user, SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth);

    List<OrderInfoModel> queryOrderByStatus(SysUser user, SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth);

    List<OrderInfoModel> selectRefundOrder(SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth);

    Integer selectCurrentPeriods(Long orderId);

    /**
     * 查询当前期数的还款详情
     * @param orderId
     * @param periods
     * @return
     */
    List<BasicOrderRepayScheme> selectRepayInfo(Long orderId,Integer periods);

    void receipt(SysUser user, Long orderId);

    void updateRepayScheme(Long orderId,Integer periods,Integer status);


    List<OrderModel> queryByReplayMoney(SearchOrderParams orderParams, List<String> dataAuth, List<String> exceptDataAuth);

    BigDecimal selectRepayInfoMoney(Map map);

    void updateRepaymentDate(Long orderId , Integer periods, Date date);
}
