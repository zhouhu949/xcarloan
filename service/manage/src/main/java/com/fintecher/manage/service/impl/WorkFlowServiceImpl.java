package com.fintecher.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fintecher.manage.service.WorkFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional(rollbackOn = Exception.class)
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    private Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);

    @Autowired
    private RestTemplate remoteRestTemplate;

    /**
     * 开启流程接口
     */
    @Value("${workFlow.api.start}")
    private String startUrl;
    /**
     * 流程下一步接口
     */
    @Value("${workFlow.api.next}")
    private String nextUrl;
    /**
     * 流程结束接口
     */
    @Value("${workFlow.api.end}")
    private String endUrl;
    /**
     * 查询任务接口
     */
    @Value("${workFlow.api.queryTasks}")
    private String queryTasksUrl;

    @Override
    public JSONObject startProcess(String processKey) throws Exception {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("processKey", processKey);
        // 下面这两个参数暂时没有，但是接口必传，在这里传空对象
        paramMap.add("variables", new JSONObject().toJSONString());
        paramMap.add("param", new JSONObject().toJSONString());
        ResponseEntity<JSONObject> responseEntity = remoteRestTemplate.postForEntity(startUrl, paramMap, JSONObject.class);
        if (!responseEntity.hasBody()) {
            throw new Exception();
        }
        JSONObject entityBody = responseEntity.getBody();
        Integer code = (Integer) entityBody.get("code");
        if (!Objects.equals(code, 0)) {
            logger.error("开启流程错误, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
            throw new Exception();
        }
        logger.info("开启流程, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
        return entityBody;
    }

    @Override
    public JSONObject nextProcess(String processInstanceId) throws Exception {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("processInstanceId", processInstanceId);
        // 下面这两个参数暂时没有，但是接口必传，在这里传空对象
        paramMap.add("variables", new JSONObject().toJSONString());
        ResponseEntity<JSONObject> responseEntity = remoteRestTemplate.postForEntity(nextUrl, paramMap, JSONObject.class);
        if (!responseEntity.hasBody()) {
            throw new Exception();
        }
        JSONObject entityBody = responseEntity.getBody();
        Integer code = (Integer) entityBody.get("code");
        if (!Objects.equals(code, 0)) {
            logger.error("流程转交下一步错误, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
            throw new Exception();
        }
        logger.info("流程转交下一步, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
        return entityBody;
    }

    @Override
    public JSONObject queryTasks(String assignee) throws Exception {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("assignee", assignee);
        ResponseEntity<JSONObject> responseEntity = remoteRestTemplate.postForEntity(queryTasksUrl, paramMap, JSONObject.class);
        if (!responseEntity.hasBody()) {
            throw new Exception();
        }
        JSONObject entityBody = responseEntity.getBody();
        Integer code = (Integer) entityBody.get("code");
        if (!Objects.equals(code, 0)) {
            logger.error("查询审核任务, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
            throw new Exception();
        }
        logger.info("查询审核任务, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
        return entityBody;
    }

    @Override
    public JSONObject endProcess(String processInstanceId) throws Exception {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("processInstanceId", processInstanceId);
        ResponseEntity<JSONObject> responseEntity = remoteRestTemplate.postForEntity(endUrl, paramMap, JSONObject.class);
        if (!responseEntity.hasBody()) {
            throw new Exception();
        }
        JSONObject entityBody = responseEntity.getBody();
        Integer code = (Integer) entityBody.get("code");
        if (!Objects.equals(code, 0)) {
            logger.error("结束流程失败, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
            throw new Exception();
        }
        logger.info("结束流程成功, 请求参数:{}, 返回数据:{}", paramMap, responseEntity.getBody());
        return entityBody;
    }
}
