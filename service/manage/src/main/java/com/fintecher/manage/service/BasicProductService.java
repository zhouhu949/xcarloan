package com.fintecher.manage.service;

import com.fintecher.entity.BasicProduct;
import com.fintecher.manage.vo.BasicProductModel;
import com.fintecher.manage.vo.ProductParamsModel;
import com.fintecher.manage.vo.ProductSchemeModel;
import com.fintecher.manage.vo.RepaySchemeExpenseModel;

import java.util.List;

public interface BasicProductService extends BaseService<BasicProduct>{
    int deleteCheck(Long id);

    List<ProductParamsModel> selectBasicProduct();

    List<RepaySchemeExpenseModel> selectByCarId(Long carId);

    List<BasicProductModel> selectBasicProductList(Long carId);

    List<ProductSchemeModel> queryReleaseProductByCar(Long carId, String schemeName);
}
