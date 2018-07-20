package com.fintecher.task.service;

import com.fintecher.task.job.OverdueCheckJob;
import com.fintecher.task.job.ResetSeqJob;
import com.fintecher.task.util.TConstants;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

@Service
public class TaskInit {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    /**
     * 初始化充值序列号
     *
     * @throws SchedulerException
     * @throws ParseException
     */
    void initResetSeqTask() throws SchedulerException, ParseException {
        initTask(ResetSeqJob.class, TConstants.RESET_SEQ_JOB_NAME, TConstants.RESET_SEQ_TRIGGER_NAME, TConstants.RESET_SEQ_CRON, 0);
    }

    /**
     * 初始化逾期检查更新任务
     *
     * @throws SchedulerException
     * @throws ParseException
     */
    void initOverdueCheckTask() throws SchedulerException, ParseException {
        initTask(OverdueCheckJob.class, TConstants.OVERDUE_CHECK_JOB_NAME, TConstants.OVERDUE_CHECK_TRIGGER_NAME, TConstants.OVERDUE_CHECK_CRON, 10);
    }


    /**
     * 初始化任务.注意：业务和此处不相符的自行做初始化处理
     *
     * @param clazz          任务执行类
     * @param jobName        任务的唯一标识
     * @param triggerName    触发器的唯一标识
     * @param cronExpression cron表达式
     * @param priority       触发器触发的优先级（值越小，优先级越高）
     * @throws SchedulerException
     * @throws ParseException
     */
    private void initTask(Class<? extends Job> clazz, String jobName, String triggerName, String cronExpression, int priority) throws SchedulerException, ParseException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName);
        if (!scheduler.checkExists(jobKey)) {
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).build();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronScheduleNonvalidatedExpression(cronExpression);
            CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(triggerKey).withSchedule(cronScheduleBuilder).withPriority(priority).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
