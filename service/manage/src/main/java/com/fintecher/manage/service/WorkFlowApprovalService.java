package com.fintecher.manage.service;

import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.ApprovalOrderModel;
import com.fintecher.manage.vo.OrderApprovalParams;
import com.fintecher.manage.vo.SearchWaitApproveParams;

import java.util.List;

public interface WorkFlowApprovalService {

    /**
     * 订单提交审核
     * @param orderId 订单ID
     */
    void submitApproval(Long orderId, Long userId) throws Exception;

    /**
     * 订单审核通过
     * @throws Exception
     */
    void approval(OrderApprovalParams params, Long userId) throws Exception;

    /**
     * 查询所有的待审核订单
     * @return
     */
    List<ApprovalOrderModel> queryAllWaitApprovalByAuth(List<String> dataAuth, List<String> exceptDataAuth, SearchWaitApproveParams searchWaitApproveParams);

    /**
     * 查询所有当前登陆用户的待审核订单
     * @param user
     * @param searchWaitApproveParams
     * @return
     */
    List<ApprovalOrderModel> queryWaitApproval(SysUser user, SearchWaitApproveParams searchWaitApproveParams) throws Exception;


}
