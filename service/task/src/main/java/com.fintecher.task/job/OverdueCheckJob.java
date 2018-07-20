package com.fintecher.task.job;

import com.fintecher.task.service.BasicOrderRepaySchemeService;
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
public class OverdueCheckJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(OverdueCheckJob.class);

    @Resource
    private BasicOrderRepaySchemeService basicOrderRepaySchemeService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String date = ZWDateUtil.getDateTime();
        try {
            logger.info("时间：{}，逾期检查任务开始...", date);
            basicOrderRepaySchemeService.overdueCheck();
            logger.info("时间：{}，逾期检查任务结束...", date);
        } catch (Exception e) {
            logger.error("时间：{}，逾期检查任务异常...", date, e);
        }
    }
}
