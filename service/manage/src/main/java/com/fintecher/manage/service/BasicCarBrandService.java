package com.fintecher.manage.service;

import com.fintecher.entity.BasicCarBrand;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public interface BasicCarBrandService extends BaseService<BasicCarBrand> {

   List<BasicCarBrand> findAllCarBrand(Long  orgId);

}
