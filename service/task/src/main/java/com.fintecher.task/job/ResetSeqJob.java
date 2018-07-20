package com.fintecher.task.job;

import com.fintecher.task.service.SysSeqService;
import com.fintecher.util.Constants;
import com.fintecher.util.ZWDateUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@DisallowConcurrentExecution
public class ResetSeqJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(ResetSeqJob.class);

    @Resource
    private SysSeqService sysSeqService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String date = ZWDateUtil.getDateTime();
        try {
            logger.info("时间：{}，晚间重置订单编号序列号开始...", date);
            sysSeqService.resetSeq(Constants.SEQ_NAME_ORDER_NO, Constants.SEQ_CURRENT_ORDER_NO);
            logger.info("时间：{}，晚间重置订单编号序列号结束...", date);
        } catch (Exception e) {
            logger.error("时间：{}，晚间重置订单编号序列号异常...", date, e);
        }
    }
}
