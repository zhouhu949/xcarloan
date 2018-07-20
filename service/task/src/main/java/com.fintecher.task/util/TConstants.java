package com.fintecher.task.util;

/**
 * 定时任务使用的常量
 */
public class TConstants {

    /**
     * 重置序列号定时任务初始化常量
     */
    public static final String RESET_SEQ_JOB_NAME = "resetSeqJob";
    public static final String RESET_SEQ_TRIGGER_NAME = "resetSeqTrigger";
    public static final String RESET_SEQ_CRON = "0 0 0 * * ?";

    /**
     * 逾期检查更新定时任务初始化常量
     */
    public static final String OVERDUE_CHECK_JOB_NAME = "overdueCheckJob";
    public static final String OVERDUE_CHECK_TRIGGER_NAME = "overdueCheckTrigger";
    public static final String OVERDUE_CHECK_CRON = "0 0 0 * * ?";

}
