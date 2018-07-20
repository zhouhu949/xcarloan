package com.fintecher.manage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.WorkFlowApprovalMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.ApprovalOrderModel;
import com.fintecher.manage.vo.OrderApprovalParams;
import com.fintecher.manage.vo.SearchWaitApproveParams;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
public class WorkFlowApprovalServiceImpl implements WorkFlowApprovalService {

    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    private WorkFlowApprovalMapper workFlowApprovalMapper;
    @Autowired
    private BasicOrderRecordService basicOrderRecordService;
    @Autowired
    private BasicCustomerBlacklistRecordService basicCustomerBlacklistRecordService;
    @Autowired
    private BasicCustomerService basicCustomerService;
    @Autowired
    private BasicOrderRepaySchemeService basicOrderRepaySchemeService;


    @Override
    public void submitApproval(Long orderId, Long userId) throws Exception {
        BasicCustomerOrder customerOrder = basicCustomerOrderService.findById(orderId);
        String workFlowKey = customerOrder.getWorkFlowKey();
        JSONObject jsonObject = workFlowService.startProcess(workFlowKey);
        // 流程ID
        String processInstanceId = (String) jsonObject.get("processInstanceId");
        String name = (String) jsonObject.get("name");
        BasicCustomerOrder order = new BasicCustomerOrder();
        order.setId(customerOrder.getId());
        order.setWorkFlowId(processInstanceId);
        order.setWorkFlowLinkName(name);
        order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING.getValue());
        basicCustomerOrderService.updateSelective(order);
        // 保存记录
        BasicOrderRecord record = new BasicOrderRecord();
        record.setOrderLink(BasicOrderRecord.OrderLink.APPLICATION.getValue());
        record.setLinkTableName("basic_customer_order");
        record.setOrderStatus(customerOrder.getOrderStatus());
        record.setRemark("订单提交审批");
        record.setRelateId(orderId);
        record.setOrderId(orderId);
        record.setOperatorTime(new Date());
        record.setOperator(userId);
        basicOrderRecordService.save(record);
    }

    @Override
    public void approval(OrderApprovalParams params, Long userId) throws Exception {
        BasicCustomerOrder customerOrder = basicCustomerOrderService.findById(params.getOrderId());
        Integer type = params.getType();
        BasicCustomerOrder order = new BasicCustomerOrder();
        order.setId(customerOrder.getId());
        order.setOperator(userId);
        order.setOperatorTime(new Date());
        // 通过 拒绝 黑名单 灰名单 白名单 退回
        approveType(type, order, userId, customerOrder);
    }

    /**
     * 通过 拒绝 黑名单 灰名单 白名单 退回不同操作
     * @param type
     * @param order
     * @param userId
     * @param sourceOrder
     * @throws Exception
     */
    private void approveType(Integer type, BasicCustomerOrder order, Long userId, BasicCustomerOrder sourceOrder) throws Exception {
        Long customerId = sourceOrder.getCustomerId();
        String remark;
        switch (OrderApprovalParams.Type.operateValue(type)) {
            case ZERO: // 通过
                nextTask(order, sourceOrder);
                remark = "订单审核通过";
                break;
//            case ONE: // 拒绝
//                order.setOrderStatus(BasicCustomerOrder.OrderStatus.REFUSE.getValue());
//                break;
            case TWO: // 黑名单
                workFlowService.endProcess(sourceOrder.getWorkFlowId());
                order.setOrderStatus(BasicCustomerOrder.OrderStatus.REFUSE.getValue());
                updateCustomer(customerId, userId, BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HMD.getValue());
                saveBlacklist(customerId, userId, BasicCustomerBlacklistRecord.BlacklistType.BLACK.getValue());
                remark = "订单审核加入黑名单";
                break;
            case THREE: // 灰名单
                updateCustomer(customerId, userId, BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HND.getValue());
                saveBlacklist(customerId, userId, BasicCustomerBlacklistRecord.BlacklistType.GRAY.getValue());
                remark = "订单审核加入灰名单";
                break;
            case FOUR: // 白名单
                nextTask(order, sourceOrder);
                updateCustomer(customerId, userId, BasicCustomer.CustomerStatus.CUSTOMER_STATUS_BMD.getValue());
                saveBlacklist(customerId, userId, BasicCustomerBlacklistRecord.BlacklistType.WHITE.getValue());
                remark = "订单审核加入白名单";
                break;
            case FIVE: // 退回
                workFlowService.endProcess(sourceOrder.getWorkFlowId());
                order.setOrderStatus(BasicCustomerOrder.OrderStatus.UNFILLED_DATA.getValue());
                remark = "订单审核退回";
                break;
            default:
                return;
        }
        basicCustomerOrderService.updateSelective(order);
        saveOrderRecord(userId, sourceOrder, remark);
    }

    /**
     * 审批通过下一步
     *
     * @param order
     * @param sourceOrder
     * @throws Exception
     */
    private void nextTask(BasicCustomerOrder order, BasicCustomerOrder sourceOrder) throws Exception {
        JSONObject jsonObject = workFlowService.nextProcess(sourceOrder.getWorkFlowId());
        String name = (String) jsonObject.get("name");
        order.setWorkFlowLinkName(name);
        if (Boolean.parseBoolean(jsonObject.get("verProcessIsEnd").toString())) {
            // 有首付款 则是待收款 无首付款则是 还款中
            Example example = new Example(BasicOrderRepayScheme.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("periods", 0);
            criteria.andEqualTo("orderId", sourceOrder.getId());
            int count = basicOrderRepaySchemeService.selectCountByExample(example);
            if (Objects.equals(sourceOrder.getOrderType(), BasicRepayScheme.SchemeType.FINANCING.getValue())) {
                if (count > 0) {
                    order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_PAYMENT.getValue());
                } else {
                    order.setOrderStatus(BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
                }
            } else {
                if (count > 0) {
                    order.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_PAYMENT.getValue());
                } else {
                    order.setOrderStatus(BasicCustomerOrder.OrderStatus.UNPAID.getValue());
                }
            }
        }
    }

    /**
     * 更新客户状态
     *
     * @param customerId
     * @param userId
     * @param customerStatus
     */
    private void updateCustomer(Long customerId, Long userId, Integer customerStatus) {
        BasicCustomer customer = new BasicCustomer();
        customer.setCustomerStatus(customerStatus);
        customer.setId(customerId);
        customer.setOperator(userId);
        customer.setOperatorTime(new Date());
        basicCustomerService.updateSelective(customer);
    }

    /**
     * 保存黑白灰名单
     *
     * @param customerId
     * @param userId
     * @param blacklistType
     */
    private void saveBlacklist(Long customerId, Long userId, Integer blacklistType) {
        Example example = new Example(BasicCustomerBlacklistRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("customerId", customerId);
        criteria.andIsNull("endTime");
        criteria.andEqualTo("blacklistType", blacklistType);
        // 若程序没有错误这里只会出现一条 循环更新不影响
        List<BasicCustomerBlacklistRecord> records = basicCustomerBlacklistRecordService.selectByExample(example);
        for (BasicCustomerBlacklistRecord record : records) {
            record.setOperatorTime(new Date());
            record.setOperator(userId);
            record.setEndTime(new Date());
            basicCustomerBlacklistRecordService.updateSelective(record);
        }
        BasicCustomerBlacklistRecord record = new BasicCustomerBlacklistRecord();
        record.setCustomerId(customerId);
        record.setBlacklistType(blacklistType);
        record.setOperator(userId);
        record.setBeginTime(new Date());
        record.setOperatorTime(new Date());
        basicCustomerBlacklistRecordService.save(record);
    }


    @Override
    public List<ApprovalOrderModel> queryAllWaitApprovalByAuth(List<String> dataAuth, List<String> exceptDataAuth, SearchWaitApproveParams searchWaitApproveParams) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("dataAuth", dataAuth);
        paramsMap.put("exceptDataAuth", exceptDataAuth);
        paramsMap.put("params", searchWaitApproveParams);
        return workFlowApprovalMapper.queryAllWaitApprovalByAuth(paramsMap);
    }

    @Override
    public List<ApprovalOrderModel> queryWaitApproval(SysUser user, SearchWaitApproveParams searchWaitApproveParams) throws Exception {
        JSONObject jsonObject = workFlowService.queryTasks(user.getId().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(jsonObject.get("data"));
        List<Map> mapList = JSONArray.parseArray(value, Map.class);
        Map map = objectMapper.convertValue(searchWaitApproveParams, Map.class);
        List<Object> processInstanceIds = mapList.stream().map(e -> e.get("processInstanceId")).collect(Collectors.toList());
        if (processInstanceIds.isEmpty()) {
            return Lists.newArrayList();
        }
        map.put("processInstanceIds", processInstanceIds);
        return workFlowApprovalMapper.queryWaitApproval(map);
    }

    /**
     * 订单操作记录
     *
     * @param userId
     * @param order
     */
    private void saveOrderRecord(Long userId, BasicCustomerOrder order, String remark) {
        BasicOrderRecord record = new BasicOrderRecord();
        record.setOrderLink(BasicOrderRecord.OrderLink.APPROVAL.getValue());
        record.setLinkTableName("basic_customer_order");
        record.setOrderStatus(order.getOrderStatus());
        record.setRelateId(order.getId());
        record.setOrderId(order.getId());
        record.setOperator(userId);
        record.setOperatorTime(new Date());
        record.setRemark(remark);
        basicOrderRecordService.save(record);
    }
}
