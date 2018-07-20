package com.fintecher.manage.service;

import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.util.List;

public interface BasicRepaySchemeService extends BaseService<BasicRepayScheme> {

    /**
     * 删除还款方案
     * @param id
     */
    void deleteBasicRepaySchemeById(Long id);

    /**
     * 增加还款方案
     * @param user
     * @param addRepayScheme
     */
    void addBasicRepayScheme(SysUser user, RepaySchemeParams addRepayScheme);

    /**
     * 获取数据权限下的还款方案菜单列表
     * @return
     */
    List<BasicRepayMenuModel> findAllBasicRepaySchemeByAuth(List<String> allDataAuth, List<String> allExceptDataAuth);

    /**
     * 获取所有还款方案所有费用项编码
     * @param schemeId 还款方案ID
     * @return
     */
    List<String> findRepaySchemeExpenseCode(Long schemeId);

    /**
     * 获取还款方案
     * @param schemeId 还款方案ID
     * @return
     */
    SchemeInfoModel findSchemeInfo(Long schemeId);

    /**
     * 获取还款方案详细信息，包含费用项信息
     * @param schemeId
     * @return
     */
    SchemeAndExpenseInfo findSchemeAndExpenseInfo(Long schemeId);


    RepaySchemeExpenseModel getEarlySettlementScheme(Long scheme, String expenseCode);
}
