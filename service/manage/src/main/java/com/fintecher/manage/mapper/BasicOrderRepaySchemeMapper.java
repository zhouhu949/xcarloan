package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.*;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BasicOrderRepaySchemeMapper extends MyMapper<BasicOrderRepayScheme>{
    /**
     * 订单id、期数,费用项code,查询本期订单详情
     * @param basicOrderRepayScheme
     * @return
     */
    BasicOrderRepayScheme selectByOrderId(BasicOrderRepayScheme basicOrderRepayScheme);

    List<BasicOrderRepayScheme> selectByOrder(BasicOrderRepayScheme basicOrderRepayScheme);

    /**
     * 查询某一期订单未还清剩余总额
     * @param basicOrderRepayScheme
     * @return
     */
    BigDecimal selectByRepay(BasicOrderRepayScheme basicOrderRepayScheme);

    /**
     * 查询订单还未还清的总余额
     * @param orderId
     * @return
     */
    BigDecimal  selectBalance(Long orderId);

    /**
     * 提前结清查询
     * @param orderId
     * @return
     */
   List<PrepaymentModel> selectDetails(Long orderId);
    /**
     * 查询还款总额
     * @param orderId
     * @return
     */
    BigDecimal selectRepayMoney(Long orderId);

    /**
     * 合计
     * @param orderId
     * @return
     */
    PrepaymentModel selectCountMoney(Long orderId);

    void updateRepayMoney(BasicOrderRepayScheme basicOrderRepayScheme);

    void updateRecover(BasicOrderRepayScheme basicOrderRepayScheme);

   Integer selectPeriods(Long orderId);

    /**
     * 查询首付款是否收款
     * @param orderId
     * @return
     */
    List<GatheringModel> getGatheringModel(Long orderId);

    /**
     * 首付款收款订单查询
     * @param map
     * @return
     */
    List<ReceiptOrder> selectReceiptOrder(Map map);

    /**
     * 订单的模糊查询（主要是订单状态）
     * @param map
     * @return
     */
    List<OrderInfoModel> queryByStatus(Map map);

    /**
     *
     * @return
     */
   List<OrderInfoModel> selectRefundOrder(Map map);

   Integer selectCurrentPeriods(Long orderId);

   List<BasicOrderRepayScheme> selectRepayInfo(Map map);

   BigDecimal selectRepayInfoMoney(Map map);

   void updateRepayScheme(Map map);

   BigDecimal getCountMoney(Long orderId);

    List<OrderModel> queryByReplayMoney(Map map);

   List<OrderRepaySchemeModel> selectOrderReplayInfo(Map map);

   List<BasicOrderRepayScheme> selectOrderRepaySchemeByOrderId( Long orderId);

   void updateOverdueCheck(String repaymentDate);

   void updateRepaymentDate(Map map);

   void deleteOverdue();

    List<BasicOrderRepayScheme> selectOrderRepayInfo(Map map);

    /**
     * 查询剩余未还总额
     * @param orderId
     * @return
     */
    BigDecimal selectNeedRepayMoney(Long orderId);

}
