package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicProduct;
import com.fintecher.manage.mapper.BasicProductMapper;
import com.fintecher.manage.service.BasicProductService;
import com.fintecher.manage.vo.BasicProductModel;
import com.fintecher.manage.vo.ProductParamsModel;
import com.fintecher.manage.vo.ProductSchemeModel;
import com.fintecher.manage.vo.RepaySchemeExpenseModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicProductServiceImpl extends BaseServiceImpl<BasicProduct> implements BasicProductService {
    @Resource
    BasicProductMapper basicProductMapper;

    @Override
    public int deleteCheck(Long id) {
        Integer i =   basicProductMapper.deleteCheck(id);
        if(i==null)
            i=0;
      return i;
    }

    @Override
    public List<ProductParamsModel> selectBasicProduct() {
        List<ProductParamsModel> list =  basicProductMapper.selectBasicProduct();
        return list;
    }

    @Override
    public List<RepaySchemeExpenseModel> selectByCarId(Long carId) {

        return basicProductMapper.selectByCarId(carId);
    }

    @Override
    public List<BasicProductModel> selectBasicProductList(Long carId) {
        return basicProductMapper.selectBasicProductList(carId);
    }

    @Override
    public List<ProductSchemeModel> queryReleaseProductByCar(Long carId, String schemeName) {
        return basicProductMapper.queryReleaseProductByCar(carId, schemeName);
    }
}
