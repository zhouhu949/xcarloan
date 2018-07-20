package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCarmodelParam;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.AddCarModelParamAndModelParams;
import com.fintecher.manage.vo.BasicCarmodelParamValue;
import com.fintecher.util.Status;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/6/13 0013.
 */
public interface BasicCarModelParamMapper extends MyMapper<BasicCarmodelParam> {

   AddCarModelParamAndModelParams getCarParams(@Param("id") Long id);

   Long queryParamId(@Param("id") Long id);

   BasicCarmodelParamValue getCarParamById(@Param("id") Long id);

   int selectCountDeleteModelCar(@Param("id") Long id);
}
