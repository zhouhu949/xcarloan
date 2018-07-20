package com.fintecher.manage.service;

import com.fintecher.entity.BasicCarmodelParam;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCarModelParamParams;
import com.fintecher.manage.vo.BasicCarmodelParamValue;


/**
 * Created by Administrator on 2018/6/13 0013.
 */
public interface BasicCarModelParamService  extends   BaseService<BasicCarmodelParam> {

   void saveModelParam(BasicCarModelParamParams carModelParamParams, SysUser user);

   BasicCarmodelParamValue getCarParamById(Long id);


   int selectCountDeleteModelCar(Long id);
}
