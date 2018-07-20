package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicProduct;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicProductModel;
import com.fintecher.manage.vo.ProductParamsModel;
import com.fintecher.manage.vo.ProductSchemeModel;
import com.fintecher.manage.vo.RepaySchemeExpenseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicProductMapper extends MyMapper<BasicProduct> {

    Integer deleteCheck(Long id);

    List<ProductParamsModel> selectBasicProduct();

    List<RepaySchemeExpenseModel> findRepaySchemeExpense(@Param("schemeId") Long schemeId);

    List<RepaySchemeExpenseModel> selectByCarId(Long carId);

    List<BasicProductModel> selectBasicProductList(Long carId);

    List<ProductSchemeModel> queryReleaseProductByCar(@Param("carId") Long carId, @Param("schemeName") String schemeName);
}
