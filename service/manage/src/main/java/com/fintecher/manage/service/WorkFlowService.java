package com.fintecher.manage.service;

import com.alibaba.fastjson.JSONObject;

public interface WorkFlowService {

    /**
     * 开启流程
     *
     * @param processKey 流程Key
     * @return
     * @throws Exception
     */
    JSONObject startProcess(String processKey) throws Exception;

    /**
     * 流程下一步
     *
     * @param processInstanceId 流程实例ID
     * @return
     * @throws Exception
     */
    JSONObject nextProcess(String processInstanceId) throws Exception;

    /**
     * 查询任务
     *
     * @param assignee 相当于用户ID
     * @return
     * @throws Exception
     */
    JSONObject queryTasks(String assignee) throws Exception;

    /**
     * 结束流程
     *
     * @param processInstanceId 流程实例ID
     * @return
     * @throws Exception
     */
    JSONObject endProcess(String processInstanceId) throws Exception;
}
