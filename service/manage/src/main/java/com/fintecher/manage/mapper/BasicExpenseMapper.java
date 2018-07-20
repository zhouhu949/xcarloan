package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicExpense;
import com.fintecher.manage.util.MyMapper;

import java.util.List;
import java.util.Map;


public interface BasicExpenseMapper extends MyMapper<BasicExpense> {
    //添加
    void save(BasicExpense basicExpense);


    //根据费用项ID删除
    void deletePrimaryKey(BasicExpense basicExpense);

    //根据费用项ID
    void updatePrimaryKey(BasicExpense basicExpense);

    /**
     *删除验证
     * @param basicExpense
     * @return
     */
    Integer check(BasicExpense basicExpense);

    //复制模板
    void copyTemplate(BasicExpense basicExpense);

    /**
     * 修改验证
     * @param basicExpense
     * @return int
     */
    int updateCheck(BasicExpense basicExpense);

    /**
     * 添加验证
     * @param basicExpense
     * @return int
     */
    int addCheck(BasicExpense basicExpense);

    /**
     * 查询数据权限下的费用项
     * @param params
     * @return
     */
    List<BasicExpense> findBasicExpenseByAuth(Map<String, List<String>> params);

   List<BasicExpense> selectOffsetExpense(Long orgId);

   void insertOverdueFine(Map map);

}
