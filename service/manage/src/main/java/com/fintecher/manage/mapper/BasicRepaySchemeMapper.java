package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicRepayMenuModel;
import com.fintecher.manage.vo.RepaySchemeExpenseModel;
import com.fintecher.manage.vo.SchemeAndExpenseInfo;
import com.fintecher.manage.vo.SchemeInfoModel;

import java.util.List;
import java.util.Map;

public interface BasicRepaySchemeMapper extends MyMapper<BasicRepayScheme> {

    /**
     * 获取数据权限下的还款方案菜单
     * @param paramsMap
     * @return
     */
    List<BasicRepayMenuModel> findAllBasicRepaySchemeByAuth(Map<String, List<String>> paramsMap);

    /**
     * 获取还款方案的所有还款方案比例对应的费用项Code
     * @param schemeId 还款方案ID
     * @return
     */
    List<String> findRepaySchemeExpenseCode(Long schemeId);

    /**
     * 获取还款方案信息
     * @param schemeId
     * @return
     */
    SchemeInfoModel findSchemeInfo(Long schemeId);

    /**
     * 获取还款方案详细信息，包含费用项信息
     * @return
     */
    SchemeAndExpenseInfo findSchemeAndExpenseInfo(Long schemeId);

    RepaySchemeExpenseModel getEarlySettlementScheme(Map map);
}
