package com.fintecher.manage.service;

import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.manage.vo.BasicSchemeExpenseParams;
import com.fintecher.manage.vo.RepayExpenseInfoModel;
import com.fintecher.manage.vo.RepaySchemeInfoModel;

import java.util.List;

public interface BasicRepaySchemeExpenseService extends BaseService<BasicRepaySchemeExpense>{

    RepaySchemeInfoModel findRepaySchemeInfo(Long id);

    /**
     * 获取还款方案比例详情
     * @param schemeId 还款方案ID
     * @return
     */
    List<RepayExpenseInfoModel> findRepaySchemeExpenseInfo(Long schemeId);

    BasicRepaySchemeExpense findRepaySchemeExpense(Long schemeId);

    /**
     * 新增/修改还款方案比例详情参数校验
     *
     * @param basicExpenseParams
     */
    void validateParams(BasicSchemeExpenseParams basicExpenseParams);


}
