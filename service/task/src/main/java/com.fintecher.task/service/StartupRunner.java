package com.fintecher.task.service;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 项目启动完成时需要初始化的资源
 */
@Component
public class StartupRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private TaskInit taskInit;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("项目启动完成，开始初始化...");
        initTask();
        logger.info("初始化完成...");
    }

    /**
     * 项目启动加载的定时相关资源
     *
     * @throws ParseException
     * @throws SchedulerException
     */
    private void initTask() throws ParseException, SchedulerException {
        taskInit.initResetSeqTask();
        taskInit.initOverdueCheckTask();
    }


}
