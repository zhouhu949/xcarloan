package com.fintecher.manage.mapper;

import com.fintecher.manage.vo.ApprovalOrderModel;

import java.util.List;
import java.util.Map;

public interface WorkFlowApprovalMapper {

    /**
     * 查询待审核订单
     * @param params
     * @return
     */
    List<ApprovalOrderModel> queryAllWaitApprovalByAuth(Map params);

    /**
     * 查询自己所有未审核订单
     * @param map
     * @return
     */
    List<ApprovalOrderModel> queryWaitApproval(Map map);
}
