package com.fintecher.manage.service;

import com.fintecher.entity.BasicExpense;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface BasicExpenseService extends BaseService<BasicExpense>{

    void insert(BasicExpense basicExpense);

    List<BasicExpense> findAll();

    void updatePrimaryKey(BasicExpense basicExpense);

    void delete(BasicExpense basicExpense);

    Integer check(BasicExpense basicExpense);

    void copyTemplate(BasicExpense basicExpense);

    /**
     *添加验证
     * @param basicExpense
     * @return
     */
    int addCheck(BasicExpense basicExpense);
    /**
     *修改验证
     * @param basicExpense
     * @return
     */
    int updateCheck(BasicExpense basicExpense);

    /**
     * 获取数据权限下的费用项
     * @param dataAuth
     * @param exceptDataAuth
     * @return
     */
    List<BasicExpense> findBasicExpenseByAuth(List<String> dataAuth, List<String> exceptDataAuth);

    void insertOverdueFine(Integer repayStatus, Long operator, Date operatorTime, String expenseCode);

}
