package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.RepayExpenseInfoModel;
import com.fintecher.manage.vo.RepaySchemeInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BasicRepaySchemeExpenseMapper extends MyMapper<BasicRepaySchemeExpense> {

    RepaySchemeInfoModel findRepaySchemeInfo(@Param("id") Long id);

    /**
     * 获取还款方案比例详情
     * @param schemeId 还款方案
     * @return
     */
    List<RepayExpenseInfoModel> findRepaySchemeExpenseInfo(Long schemeId);

    /**
     * 查询还款详情为提前结清的信息
     * @param map
     * @return
     */
    BasicRepaySchemeExpense selectExpense(Map map);
}
