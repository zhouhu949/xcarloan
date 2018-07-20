package com.fintecher.manage.service;

import com.fintecher.entity.BasicCarModel;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
public interface BasicCarModelService extends   BaseService<BasicCarModel>{

    /**
     * 根据车型ID获取车辆品牌、车系等信息
     * @param modelId
     * @return
     */
    CarModelInfo findCarModel(Long modelId);


   AddCarModelParamAndModelParams getCarParams(Long id);

   void saveModel(BasicCarModelParams carModelParams, SysUser user);

   void updateModel(EditCarModelParams editCarModelParams, SysUser user);

    List<CarInfoModel> getCarModelListByOrg(Long orgId, SearchCarParams searchCarParams);

   void deleteParamsById(Long id);
}
