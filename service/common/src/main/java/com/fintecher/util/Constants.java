package com.fintecher.util;

import com.fintecher.entity.BasicExpense;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Author: sunyanping
 * @Description:
 * @Date 13:32 2017/7/17
 */
public final class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 8;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    /**
     * 超级管理员id
     */
    public static final long ADMINISTRATOR_USER_ID = 1;
    /**
     * 超级管理员角色id
     */
    public static final long ADMINISTRATOR_ROLE_ID = 1;
    /**
     * 顶级部门id
     */
    public static final long ADMINISTRATOR_DEPARTMENT_ID = 1;
    /**
     * 用户密码过时天数系统参数Code
     */
    public static final String PASSWORD_OBSOLETE_DAYS_CODE = "PasswordObsoleteDays";
    /**
     * 用户密码默认过时天数
     */
    public static final int PASSWORD_DEFAULT_DAYS = 30;

    /**
     * 用户默认密码系统参数Code
     */
    public static final String PERSONAL_DEFAULT_PASSWORD_CODE = "PersonalDefaultPassword";
    /**
     * 用户默认密码
     */
    public static final String PERSONAL_DEFAULT_PASSWORD = "888888";

    /**
     * 系统环境
     */
    public static final String PROJECT_DEFAULT_ENV_CODE = "ProjectDefaultEnv";

    /**
     * 序列值：订单编号序列
     * 序列名称：order_no
     * 初始值：0
     */
    public static final String SEQ_NAME_ORDER_NO = "order_no";
    public static final int SEQ_LENGTH_ORDER_NO = 5;
    public static final int SEQ_CURRENT_ORDER_NO = 0;

    /**
     * 融资租赁必须要的费用项CODE: 押金、GPS费用、管理费、本金、利息、提前结清手续费、罚息、罚金、首付款
     * 抵押贷款必须要的费用项CODE: GPS费用、管理费、本金、利息、提前结清手续费、罚息、罚金
     */
    public final static Set<String> FINANCING_EXPENSE_CODE;
    public final static Set<String> MORTGAGE_EXPENSE_CODE;
    static {
        FINANCING_EXPENSE_CODE = Sets.newHashSet(
                BasicExpense.ExpenseCode.YJ.getValue(),
                BasicExpense.ExpenseCode.GPSFY.getValue(),
                BasicExpense.ExpenseCode.GLF.getValue(),
                BasicExpense.ExpenseCode.BJ.getValue(),
                BasicExpense.ExpenseCode.LX.getValue(),
                BasicExpense.ExpenseCode.TQJQSXF.getValue(),
                BasicExpense.ExpenseCode.FX.getValue(),
                BasicExpense.ExpenseCode.FJ.getValue(),
                BasicExpense.ExpenseCode.SFK.getValue());

        MORTGAGE_EXPENSE_CODE = Sets.newHashSet(BasicExpense.ExpenseCode.YJ.getValue(),
                BasicExpense.ExpenseCode.GPSFY.getValue(),
                BasicExpense.ExpenseCode.GLF.getValue(),
                BasicExpense.ExpenseCode.BJ.getValue(),
                BasicExpense.ExpenseCode.LX.getValue(),
                BasicExpense.ExpenseCode.TQJQSXF.getValue(),
                BasicExpense.ExpenseCode.FX.getValue(),
                BasicExpense.ExpenseCode.FJ.getValue());
    }

    /**
     * 系统环境
     */
    public enum ProjectEnv{
        SAAS(10037),
        PROJECT(10038);
        private Integer value;

        ProjectEnv(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


}
