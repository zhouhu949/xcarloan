package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicProductRepayTemplate;
import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.ProductTemplateInfo;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface BasicProductRepayTemplateMapper extends MyMapper<BasicProductRepayTemplate> {
    /**
     * 根据方案id查询方案比例详情
     * @param schemeId
     * @return
     */
    List<BasicRepaySchemeExpense> selectRepaySchemeExpense(Long schemeId);

    List<BasicProductRepayTemplate> selectByProductId(Long schemeId);

    List<ProductTemplateInfo> findBasicTemplateInfo(@Param("productId") Long productId);

    /**
     * 根据车型产品id删除还款计划模板
     * @param productId
     */
    void deleteByProductId(Long productId);

   List<Map> findByProductId(@Param("productId") Long productId);

    /**
     * 根据车型产品id查询该产品方案的所有费用项
     * @param productId
     * @return
     */

    List<Map> selectExpense(@Param("productId") Long productId);

    /**
     * 行转列查询
     * @return
     */
    List<Map> selectTemplate(Map map);

}
