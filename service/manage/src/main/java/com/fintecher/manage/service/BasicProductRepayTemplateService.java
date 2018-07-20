package com.fintecher.manage.service;

import com.fintecher.entity.BasicProductRepayTemplate;
import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.manage.vo.OrderModel;
import com.fintecher.manage.vo.ProductTemplateInfo;
import com.fintecher.manage.vo.RepayTemplateModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BasicProductRepayTemplateService extends BaseService<BasicProductRepayTemplate> {


    /**
     * 根据方案id查询方案比例详情
     * @param schemeId
     * @return
     */
    List<BasicRepaySchemeExpense> selectRepaySchemeExpense(Long schemeId);


    /**
     * 添加车型产品还款计划模板
     * @param template
     */
    void addCarScheme(BasicProductRepayTemplate template);


    /**
     * 根据车型产品ID查询还款计划模板详细信息
     * @param productId 车型产品ID
     * @return
     */
    List<ProductTemplateInfo> findBasicTemplateInfo(Long productId);

    /**
     * 根据车型产品id删除还款计划模板
     */
    void deleteByProductId(Long productId);

    /**
     * 获取还款计划表（包含表头信息、表数据信息）
     * @param schemeId 还款方案ID
     * @param amount 本金总额
     * @return
     */
    Map<String, Object> repaymentSchedule(Long schemeId, BigDecimal amount);

    /**
     * 根据产品获取要展示的还款计划
     *
     * @param productId
     * @return
     */
    Map<String, Object> repaymentSchedule(Long productId);

    List<Map> selectTemplate (Long id);

    /**
     * 根据还款方案和金额获取所有期数对应该还费用项
     * @param schemeId
     * @param amount
     * @return
     */
    List<RepayTemplateModel> schemeExpenseInfo(Long schemeId, BigDecimal amount);

}
